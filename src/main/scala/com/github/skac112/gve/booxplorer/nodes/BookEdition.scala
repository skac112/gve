package com.github.skac112.gve.booxplorer.nodes

import com.github.skac112.gve.booxplorer.Node

trait BookEdition extends Node {
  def editionYearO: Option[Int]
}
