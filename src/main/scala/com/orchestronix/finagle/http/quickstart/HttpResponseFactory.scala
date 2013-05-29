package com.orchestronix.finagle.http.quickstart

import org.jboss.netty.handler.codec.http.HttpResponseStatus
import com.twitter.finagle.http._
import Version._
import org.json4s._
import org.json4s.native.JsonMethods._
import JsonDSL._

trait HttpResponseFactory {
  def errorResp(status: HttpResponseStatus, cause: String) =
    stdResp(status, "error" -> cause)
  
  def stdResp(status: HttpResponseStatus) = {
    val resp = Response(Http11, status)
    resp.contentType = MediaType.Json
    resp    
  }
  
  def stdResp(status: HttpResponseStatus, json: JValue): Response = {
    val resp = stdResp(status)
    resp.contentString = compact(render(json))
    resp
  }
}