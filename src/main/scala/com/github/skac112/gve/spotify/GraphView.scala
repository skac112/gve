package com.github.skac112.gve.spotify

import cats.Monad
import com.github.skac112.gve.spotify.elements.connections.Connection
import com.github.skac112.gve.spotify.elements.entities.{AlbumObject, ArtistObject, Entity, PlaylistObject, TrackObject}
import com.github.skac112.euler._
import scala.concurrent._
import cats.implicits._
import cats.instances.future._
import com.github.skac112.gve.SpotifyAuthResp
import com.github.skac112.gve.elements.entities.Artist
import org.scalajs.dom.ext.Ajax
import upickle.default.read
import com.github.skac112.gve.spotify.elements.connections._

import scala.concurrent.ExecutionContext.Implicits.global

object GraphView {
  sealed trait SpotifyGlobalId {
    def stringId: String
  }

  case class ArtistId(override val stringId: String) extends SpotifyGlobalId
  case class AlbumId(override val stringId: String) extends SpotifyGlobalId
  case class TrackId(override val stringId: String) extends SpotifyGlobalId
  case class PlaylistId(override val stringId: String) extends SpotifyGlobalId
}

import GraphView._

case class GraphView(clientId: String, clientSecret: String)
  extends com.github.skac112.euler.GraphView[Entity, Connection, Future] {
  import com.github.skac112.euler.GraphView._
  type NodeId = SpotifyGlobalId
  type EdgeId = Connection
//  case class Edge(srcNodeId: SpotifyGlobalId, dstNodeId: SpotifyGlobalId, relation: String)

  override implicit def m: Monad[Future] = Monad[Future]
  lazy val tokenSupplier = AuthTokenSupplier(clientId, clientSecret)
  def authToken = tokenSupplier.token
  lazy val baseUrl = "https://api.spotify.com/v1"

  def requestHeaders(authToken: String) = Map("Authorization" -> s"Bearer $authToken")
  def artistEndpointUrlFun(artistId: String) = s"$baseUrl/artists/$artistId"
  def albumEndpointUrlFun(albumId: String) = s"$baseUrl/albums/$albumId"
  def trackEndpointUrlFun(trackId: String) = s"$baseUrl/tracks/$trackId"
  def playlistEndpointUrlFun(playlistId: String) = s"$baseUrl/playlists/$playlistId"

  override def node(nodeDes: ThisNodeDesignator): Future[Option[ThisNodeInfo]] = { nodeDes match {
    case data: Entity => data match {
      case artist: ArtistObject => Future { Some(NodeInfo[NodeId, Entity](ArtistId(artist.id), artist)) }
    }
    case id: SpotifyGlobalId => id match {
      case ArtistId(string_id) => for {
        artist <- readArtist(string_id)
      } yield Some(new NodeInfo[NodeId, Entity](id, artist))
      case AlbumId(string_id) => for {
        album <- readAlbum(string_id)
      } yield Some(new NodeInfo[NodeId, Entity](id, album))
      case TrackId(string_id) => for {
        track <- readTrack(string_id)
      } yield Some(new NodeInfo[NodeId, Entity](id, track))
      case PlaylistId(string_id) => for {
        playlist <- readPlaylist(string_id)
      } yield Some(new NodeInfo[NodeId, Entity](id, playlist))
    }
  }}

  /**
   * Not very useful implementation as for now, because it only wraps submitted data.
   * @param ed
   * @return
   */
  override def edge(ed: ThisEdgeDesignator): Future[Option[ThisEdgeInfo]] = ed match
    case connection: Connection => Future {
      Some(EdgeInfo[Connection, Connection, SpotifyGlobalId](connection, connection, 
        connection.srcNodeId, connection.dstNodeId)) }

  override def edges(nd: ThisNodeDesignator, direction: Int): Future[Set[ThisEdgeInfo]] = ???

  private def requestSingle[T](stringId: String, endpointUrlFun: String => String)(implicit reader: upickle.default.Reader[T]): Future[T] = for {
    auth_token <- authToken
    resp <- Ajax.get(endpointUrlFun(stringId), null, 10000, requestHeaders(auth_token), false) map { case xhr =>
      val resp_text = xhr.responseText
      read[T](resp_text)
    }
  } yield resp



  private def readArtist(artistId: String): Future[ArtistObject] = requestSingle[ArtistObject](artistId, artistEndpointUrlFun _)
  private def readAlbum(albumId: String): Future[AlbumObject] = requestSingle[AlbumObject](albumId, albumEndpointUrlFun _)
  private def readTrack(trackId: String): Future[TrackObject] = requestSingle[TrackObject](trackId, trackEndpointUrlFun _)
  private def readPlaylist(trackId: String): Future[PlaylistObject] = requestSingle[PlaylistObject](trackId, trackEndpointUrlFun _)


}
