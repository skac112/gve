package com.github.skac112.gve.spotify.datastructs

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object ImageObject {
  implicit val rw: RW[ImageObject] = macroRW
}

case class ImageObject(
                      url: String,
                      height: Int,
                      width: Int)
