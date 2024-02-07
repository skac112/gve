package com.github.skac112.gve.booxplorer.nodes

import com.github.skac112.gve.booxplorer.booxplorer.{ImgHandle, Isbn}

case class PaperBook(override val editionYearO: Option[Int],
                     isbnO: Option[Isbn],
                     coverImgO: Option[ImgHandle],
                     coverTypeO: Option[Int],
                     pagesO: Option[Int]) extends BookEdition {

}
