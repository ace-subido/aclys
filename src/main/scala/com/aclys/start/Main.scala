package com.aclys.start

import java.io._
import java.net.InetSocketAddress
import java.util.Properties

import com.twitter.finagle.{Service, SimpleFilter}
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.HttpVersion
import org.jboss.netty.util.CharsetUtil.UTF_8
import com.twitter.util.Future
import java.net.InetSocketAddress
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.http.Http
import scala.collection.JavaConversions._

import com.twitter.util._
import com.twitter.finagle.http._
import com.twitter.finagle.http.path._
import Method._
import Status._

object Main {
  def main(args: Array[String]) {
    val conf = new Properties
    conf.load(new FileInputStream("config.properties"))
    
    val aclys: Service[HttpRequest, HttpResponse] = new AclysService

    val server = ServerBuilder()
      .codec(Http().maxRequestSize(new StorageUnit(Int.MaxValue)))
      .bindTo(new InetSocketAddress(conf("server.port").toInt))
      .name("aclys")
      .build(aclys)
  }  
}

class AclysService() extends Service[HttpRequest, HttpResponse] {
  var data = Map[String, String]()

  def apply(arg: HttpRequest) = Future {    
    val request = Request(arg)
    val key = request.getParam("key")   
        
    (request.method) match {
      case Get => get(key)       
      case Post => put(key, request.contentString)        
      case Delete => delete(key)
      case _ => stdResp(NotFound)
    }
  }  
  
  def get(key: String): HttpResponse = {   
    if(data.contains(key)) stdResp(Ok, data(key))
    else stdResp(NotFound)     
  }
  
  def put(key: String, value: String): HttpResponse = {    
    data += (key -> value)
    stdResp(Ok)      
  }
  
  def delete(key: String): HttpResponse = {
		if(data.contains(key)) {
			data -= (key)
			stdResp(Ok)
		} else stdResp(NotFound)     
  }
  
  private def stdResp(status: HttpResponseStatus) = {
    val resp = Response(HttpVersion.HTTP_1_1, status)   
    resp.contentType = MediaType.WwwForm
    resp    
  }
  
  private def stdResp(status: HttpResponseStatus, value: String): HttpResponse = {
    val resp = stdResp(status)        
    resp.contentString = value
    resp
  }
}