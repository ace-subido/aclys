package com.orchestronix.finagle.http.quickstart

import java.io._
import java.net.InetSocketAddress
import java.util.Properties

import org.slf4j.Logger

import com.google.inject._
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http._
import com.twitter.util.StorageUnit

import net.codingwell.scalaguice.InjectorExtensions._

object Main {
  def main(args: Array[String]) {
    val conf = new Properties
    conf.load(new FileInputStream("config.properties"))

    val guice = new ScalaInjector(Guice.createInjector(new GuiceModule(conf)))

    // Configure routing
    val muxer = (guice.instance[HttpMuxer])
      .withHandler("hello/", guice.instance[HelloService])

    val log = guice.instance[Logger]
    val auth = guice.instance[AuthFilter]
    val config = guice.instance[Config]

    log.info("Starting server")

    val server = ServerBuilder()
      .codec(Http().maxRequestSize(new StorageUnit(Int.MaxValue)))
      .bindTo(new InetSocketAddress(config.server.port))
      .name("finagle-http-quickstart")
      .build(auth andThen muxer)
  }
}