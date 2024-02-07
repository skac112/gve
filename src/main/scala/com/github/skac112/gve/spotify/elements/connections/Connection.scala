package com.github.skac112.gve.spotify.elements.connections

import com.github.skac112.gve.spotify.GraphView.SpotifyGlobalId

trait Connection extends com.github.skac112.gve.elements.connections.Connection  {
  def srcNodeId: SpotifyGlobalId
  def dstNodeId: SpotifyGlobalId
  def relation: String
}
