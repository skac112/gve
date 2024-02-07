package com.github.skac112.gve.booxplorer.nodes

case class Ebook(
                  override val editionYearO: Option[Int],
                  fileSize: Option[Int]) extends BookEdition {

}
