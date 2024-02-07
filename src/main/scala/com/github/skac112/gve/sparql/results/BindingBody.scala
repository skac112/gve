package com.github.skac112.gve.sparql.results

import upickle.default.{macroRW, ReadWriter => RW}

object BindingBody {
  implicit val rw: RW[BindingBody] = macroRW
}

case class BindingBody(`type`: String, value: String) {

}
