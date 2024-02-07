package com.github.skac112.gve.booxplorer.nodes

import com.github.skac112.gve.booxplorer.Node

trait Person extends Node {
  def firstNameO: Option[String]
  def lastNameO: Option[String]
  def birthYearO: Option[Int]
  def deathYearO: Option[Int]
  def isAliveO: Option[Boolean]
}
