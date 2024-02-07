package com.github.skac112.gve.spotify.elements.entities

import com.github.skac112.gve.spotify.datastructs.{ExternalUrlObject, FollowersObject, ImageObject}
import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object ArtistObject {
  implicit val rw: RW[ArtistObject] = macroRW
}

case class ArtistObject(
                   external_urls: ExternalUrlObject,
                   followers: FollowersObject,
                   genres: Seq[String],
                   href: String,
                   id: String,
                   images: Seq[ImageObject],
                   override val name: String,
                   popularity: Int,
                   uri: String
                 ) extends Entity with com.github.skac112.gve.elements.entities.MusicBand
