package com.github.skac112.gve.sparql.results

import upickle.default.{macroRW, ReadWriter => RW}

object Binding {
  implicit val rw: RW[Binding] = macroRW
}

case class Binding(outProp: BindingBody, obj: BindingBody, subject: BindingBody, inProp: BindingBody) {
}
