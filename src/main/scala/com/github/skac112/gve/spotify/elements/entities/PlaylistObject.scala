package com.github.skac112.gve.spotify.elements.entities

import com.github.skac112.gve.spotify.datastructs.{ExternalUrlObject, FollowersObject, ImageObject}

import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object PlaylistObject {
  implicit val rw: RW[PlaylistObject] = macroRW
}

case class PlaylistObject(

                 ) extends Entity
