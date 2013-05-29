package com.orchestronix.finagle.http.quickstart

import scala.collection.JavaConversions._
import java.util.Properties

class Config(p: Properties) {
  object server {
    def port = p("server.port").toInt
  }
}