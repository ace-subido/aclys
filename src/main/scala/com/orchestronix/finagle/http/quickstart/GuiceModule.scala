package com.orchestronix.finagle.http.quickstart

import java.util.Properties

import org.slf4j._

import com.google.inject._
import com.google.inject.name._
import com.twitter.finagle.http._

import net.codingwell.scalaguice.ScalaModule

class GuiceModule(conf: Properties) extends AbstractModule with ScalaModule {
  override def configure {
    bind[Logger].toInstance(LoggerFactory.getLogger(""))
    bind[Config].toInstance(new Config(conf))
    
    bind[Auth].to[AuthImpl].in[Singleton]
    bind[AuthFilter].to[AuthFilterImpl].in[Singleton]
    bind[HelloService].to[HelloServiceImpl].in[Singleton]
  }
}