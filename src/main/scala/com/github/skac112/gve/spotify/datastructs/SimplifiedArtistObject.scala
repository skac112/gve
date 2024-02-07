package com.github.skac112.gve.spotify.datastructs

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object SimplifiedArtistObject {
  implicit val rw: RW[SimplifiedArtistObject] = macroRW
}

case class SimplifiedArtistObject(id: String)
