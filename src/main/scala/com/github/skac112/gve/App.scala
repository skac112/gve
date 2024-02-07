package com.github.skac112.gve

import com.github.skac112.gve.spotify.GraphView.ArtistId
import com.github.skac112.gve.spotify.datastructs.{ExternalUrlObject, FollowersObject, ImageObject}
import com.github.skac112.gve.spotify.elements.entities.{ArtistObject, Entity}
import com.github.skac112.gve.spotify.{AuthTokenSupplier, GraphView}
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.raw.FormData
import com.github.skac112.euler._

import scala.concurrent.ExecutionContext.Implicits.global
import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

import java.util.Base64

object SparqlHead {
  implicit val rw: RW[SparqlHead] = macroRW
}

case class SparqlHead(link: Seq[String], vars: Seq[String])

case class SparqlResults(distinct: Boolean, ordered: Boolean, bindings: Seq[SparqlBinding])

object SparqlResults {
  implicit val rw: RW[SparqlResults] = macroRW
}

case class SparqlBinding(Concept: BindingBody)

object SparqlBinding {
  implicit val rw: RW[SparqlBinding] = macroRW
}

case class BindingBody(`type`: String, value: String)

object BindingBody {
  implicit val rw: RW[BindingBody] = macroRW
}

case class SparqlResp(head: SparqlHead, results: SparqlResults)

object SparqlResp {
  implicit val rw: RW[SparqlResp] = macroRW
}

case class SpotifyAuthResp(access_token: String, token_type: String, expires_in: Int)

object SpotifyAuthResp {
  implicit val rw: RW[SpotifyAuthResp] = macroRW
}

object Main extends App {
  def artistUrl(artistId: String) = s"https://api.spotify.com/v1/artists/{$artistId}"
  def relatedArtistsUrl(artistId: String) = s"https://api.spotify.com/v1/artists/{$artistId}/related-artists"
  val nirvana_id = "7dIxU1XgxBIa3KJAWzaFAC"
  val client_id = "e13424843835433699a5faa32272da58"
  val client_secret = "bb1957d21c1c4c2ebb48d6819aa92786"
//  val base64_enc = Base64.getEncoder
//  val auth_url = "https://accounts.spotify.com/api/token"
//  val nirvana_id = "6olE6TJLqED3rqDCT0FyPh"
//  val artist_endpoint = "https://api.spotify.com/v1/artists/"
//  val auth_value = s"Basic ${base64_enc.encodeToString(s"$client_id:$client_secret".getBytes)}"
//
//
//  val headers = Map("Authorization" -> auth_value,
//    "Content-Type" -> "application/x-www-form-urlencoded")

  var data = "grant_type=client_credentials"


//  Ajax.post(auth_url, data, 10000, headers, true).foreach { case xhr =>
//    val resp_text = xhr.responseText
//    val res = read[SpotifyAuthResp](resp_text)
//    val token = res.access_token
//
//    println(res)
//  }

//  val token_supplier = AuthTokenSupplier(client_id, client_secret)
//  val token = token_supplier.token
//  token foreach {token => println(token)}
//  val token2 = token_supplier.token
//  token2 foreach {token => println(token)}
//  val token3 = token_supplier.token
//  token3 foreach {token => println(token)}

  val gv = GraphView(client_id, client_secret)
  val artist = gv.node(ArtistId(nirvana_id).id)
//  artist foreach { (artist: Option[NodeInfo[Entity]]) => println(artist.get.Data) }
  artist foreach { (res: Option[NodeInfo[Entity]]) => {
    res.get.Data match {
      case ArtistObject(external_urls, followers, genres, href, id, images, name, popularity, uri: String) => {
        images.foreach(println)
      }
    }
  } }

  //  val url = "https://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+distinct+%3FConcept+where+%7B%5B%5D+a+%3FConcept%7D+LIMIT+100&format=application%2Fsparql-results%2Bjson&CXML_redir_for_subjs=121&CXML_redir_for_hrefs=&timeout=30000&debug=on&run=+Run+Query+"
//
//  Ajax.get(url).foreach { case xhr =>
//    val resp_text = xhr.responseText
//    val res = read[SparqlResp](resp_text)
//    println(res)
//  }
}
