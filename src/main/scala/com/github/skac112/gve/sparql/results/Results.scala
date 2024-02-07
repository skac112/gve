package com.github.skac112.gve.sparql.results

import upickle.default.{macroRW, ReadWriter => RW}

object Results {
  implicit val rw: RW[Results] = macroRW
}

case class Results(distinct: Boolean, ordered: Boolean, bindings: Seq[Binding]) {


}
