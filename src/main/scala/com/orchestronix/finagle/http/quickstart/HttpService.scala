package com.orchestronix.finagle.http.quickstart

import org.jboss.netty.handler.codec.http._
import com.twitter.finagle.Service
import com.twitter.finagle.http._

trait HttpService extends Service[HttpRequest, HttpResponse] with HttpResponseFactory {
  def baseUrl: path./
}