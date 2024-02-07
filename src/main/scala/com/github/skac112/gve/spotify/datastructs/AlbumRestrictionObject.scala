package com.github.skac112.gve.spotify.datastructs

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object AlbumRestrictionObject {
  implicit val rw: RW[AlbumRestrictionObject] = macroRW
}

case class AlbumRestrictionObject(reason: String)
