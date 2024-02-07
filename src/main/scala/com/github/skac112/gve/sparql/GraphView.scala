package com.github.skac112.gve.sparql
import skac.euler._
import org.scalajs.dom.ext.Ajax
import upickle.default.read

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import results._
import cats._
import cats.implicits._

class GraphView[Node, Edge](params: GraphViewParams) extends skac.euler.GraphView[Node, Edge, Future] {
  override def m = Monad[Future]

  override def node[SND >: Node](nodeDes: NodeDesignator): Future[Option[NodeInfo[SND]]] = {
    val query = nodeQuery(nodeDes)
    val url = makeUrl(query, params)
    for {
      resp <- execQuery(url)
      node_data = nodeDataFromRaw(resp)
    } yield Some(new NodeInfo(nodeDes, node_data))
  }

  private def nodeQuery(nodeDes: NodeDesignator): String = nodeDes match {
    case nid: NodeIDDesignator => {
      val node_url = nid.asInstanceOf[String]
      s"SELECT ?pred ?obj WHERE {<$node_url> ?pred >obj}"
    }
    case _ => throw new Exception("Invalid node designator type (must be IDNodeDesignator")
  }

  private def makeUrl(query: String, params: GraphViewParams): String = {
    val params_map = params.urlParams + ("query" -> query)

    val url_str = params_map.foldLeft(s"${params.endpoint}?"){ (acc, kv) => {
      val param_enc_val = js.Dynamic.global.encodeURIComponent(kv._2).asInstanceOf[String]
      s"$acc&${kv._1}=$param_enc_val"
    }}

    url_str.replace(' ', '+')
  }

  private def nodeDataFromRaw(rawData: ResultsObj): Node = ???
//
  override def edge[SED >: Edge](edgeDes: EdgeDesignator): Future[Option[EdgeInfo[SED]]] = ???
//
  override def edges[SED >: Edge](nd: NodeDesignator, direction: Int): Future[Set[EdgeInfo[SED]]] = ???
//
  private def execQuery(url: String): Future[ResultsObj] = for {
    ajax_resp <- Ajax.get(url)
    sparql_resp = read[ResultsObj](ajax_resp.responseText)
  } yield sparql_resp
}
