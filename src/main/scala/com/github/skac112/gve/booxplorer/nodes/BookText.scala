package com.github.skac112.gve.booxplorer.nodes

import com.github.skac112.gve.booxplorer.Node

case class BookText(
                 title: String,
                 description: Option[String],
                 firstReleaseYearO: Option[Int]) extends Node {

}
