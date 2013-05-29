package com.orchestronix.finagle.http.quickstart

import org.apache.commons.codec.binary.Base64._
import org.apache.commons.codec.digest.DigestUtils._

import org.jboss.netty.handler.codec.http._
import org.slf4j.Logger

import com.google.inject._
import com.google.inject.name._

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import Method._
import Status._
import com.twitter.finagle.http.Status._
import com.twitter.util._

class AuthFilterImpl @Inject()(auth: Auth, log: Logger) extends AuthFilter {
  def validate(header: String) = {
    try {
      // Parse the Basic auth header for the caller
      val cred = new String(decodeBase64(header.split(" ")(1))).split(":")

      // Let the caller implement username/password validation
      auth.authenticate(cred(0), cred(1))
    }
    catch {
      case e: IndexOutOfBoundsException => false
    }
  }

  def apply(arg: HttpRequest, service: Service[HttpRequest, HttpResponse]) = {
    // If you need HTTP basic authentication, comment out this section and see below!
    // You don't always need to use basic auth, you can use digest, etc or your implement your own,
    // but please stick to standards! :)
    service(arg).handle {
      case e => {
        log.error(e.getStackTraceString, e)
        stdResp(InternalServerError)
      }
    }
    
    // To enable request HTTP basic authentication, uncomment below
    // and don't forget to implement the AuthImpl class!
//    Request(arg).headers.get("Authorization") match {
//      case Some(header) if validate(header) => {
//        service(arg).handle {
//          case e => {
//            log.error(e.getStackTraceString, e)
//            stdResp(InternalServerError)
//          }
//        }
//      }
//      case _ => Future(stdResp(Unauthorized))
//    }
  }
}