package com.orchestronix.finagle.http.quickstart

import org.jboss.netty.handler.codec.http._
import com.twitter.finagle._

trait HttpFilter extends SimpleFilter[HttpRequest, HttpResponse] with HttpResponseFactory