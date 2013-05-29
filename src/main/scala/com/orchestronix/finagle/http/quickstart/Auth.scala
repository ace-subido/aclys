package com.orchestronix.finagle.http.quickstart

trait Auth {
  def authenticate(u: String, p: String): Boolean
}