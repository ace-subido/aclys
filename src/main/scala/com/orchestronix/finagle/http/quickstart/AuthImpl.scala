package com.orchestronix.finagle.http.quickstart

class AuthImpl extends Auth {
  // TODO: Plug in your own implementation of authenticating api credentials!
  def authenticate(u: String, p: String) = true
}