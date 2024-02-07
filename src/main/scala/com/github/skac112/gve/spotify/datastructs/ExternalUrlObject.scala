package com.github.skac112.gve.spotify.datastructs

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object ExternalUrlObject {
  implicit val rw: RW[ExternalUrlObject] = macroRW
}

case class ExternalUrlObject(spotify: String) {

}
