package com.github.skac112.gve.spotify.datastructs

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object FollowersObject {
  implicit val rw: RW[FollowersObject] = macroRW
}

case class FollowersObject(href: String, total: Int)
