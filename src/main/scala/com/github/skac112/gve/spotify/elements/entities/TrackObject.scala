package com.github.skac112.gve.spotify.elements.entities

import com.github.skac112.gve.elements.entities.MusicAlbumTrack
import upickle._
import upickle.default._
import upickle.default.{macroRW, ReadWriter => RW}

object TrackObject {
  implicit val rw: RW[TrackObject] = macroRW
}

case class TrackObject(
                      override val album: AlbumObject
                      ) extends Entity with MusicAlbumTrack
{
}
