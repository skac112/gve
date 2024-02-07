package com.github.skac112.gve.spotify.elements.entities

import com.github.skac112.gve.elements.entities.MusicAlbum
import com.github.skac112.gve.spotify.datastructs.{AlbumRestrictionObject, ExternalUrlObject, ImageObject, SimplifiedArtistObject}
import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object AlbumObject {
  implicit val rw: RW[AlbumObject] = macroRW
}

/**
 * Class is based on Spotify Web API SimplifiedAlbumObject (not AlbumObject). This is a type of data returned by
 * request of albums of an artist.
 */
case class AlbumObject(album_group: String,
                       album_type: String,
                       artists: Seq[SimplifiedArtistObject],
                       available_markets: Seq[String],
                       external_urls: Seq[ExternalUrlObject],
                       href: String,
                       id: String,
                       images: Seq[ImageObject],
                       name: String,
                       release_date: String,
                       release_date_precision: String,
                       restrictions: AlbumRestrictionObject,
                       total_tracks: Int,
                       uri: String) extends Entity with MusicAlbum {

}
