package com.orchestronix.finagle.http.quickstart

import org.jboss.netty.handler.codec.http._
import org.json4s.JsonDSL._
import com.twitter.util._
import com.twitter.finagle.http._
import com.twitter.finagle.http.path._
import Method._
import Status._
import org.slf4j._
import com.google.inject._

class HelloServiceImpl @Inject()(log: Logger) extends HelloService {
  def baseUrl = Root / "hello"

  def apply(arg: HttpRequest) = Future {
    val request = Request(arg)

    (request.method, Path(request.path)) match {
      case Get -> baseUrl / "hi" => hi(request)
      case Post -> baseUrl / "echo" => echo(request)
      case _ => stdResp(NotFound)
    }
  }

  // Says hi!
  // GET /hello/hi
  def hi(request: Request): HttpResponse = {
    log.info("Somebody said Hi!")
    stdResp(Ok, ("message" -> "Hi!"))
  }

  // Echoes the given parameter 'msg'
  // Sends back a 401 status if the msg parameter is not given
  // POST /hello/echo
  def echo(request: Request): HttpResponse = (for {
    msg <- request.params.get("msg")
  } yield msg) match {
    case Some(msg) => stdResp(Ok, ("message" -> msg))
    case _ => errorResp(BadRequest, "msg was not given")
  }
}