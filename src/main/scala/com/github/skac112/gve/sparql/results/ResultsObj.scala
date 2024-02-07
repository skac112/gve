package com.github.skac112.gve.sparql.results

import upickle.default.{macroRW, ReadWriter => RW}

object ResultsObj {
  implicit val rw: RW[ResultsObj] = macroRW
}

case class ResultsObj(head: Head, results: Results) {

}
