package com.github.skac112.gve.sparql.results

import upickle.default.{macroRW, ReadWriter => RW}

object Head {
  implicit val rw: RW[Head] = macroRW
}

case class Head(vars: Seq[String], link: Seq[String]) {

}
