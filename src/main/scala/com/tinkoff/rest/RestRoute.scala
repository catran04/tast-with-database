package com.tinkoff.rest


import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives

/**
  * Created by Administrator on 3/6/2018.
  */
class RestRoute extends Directives {

  def route: server.Route = {
    pathPrefix("tinkoff") {
      pathPrefix("nosql") {
        path("data.getData") {
          get {
            complete("")
          }
        } ~
          path("data.getBackData") {
            get {
              complete("")
            }
          } ~
          path("disconnect") {
            get {
              complete("")
            }
          }
      } ~
        path("data.getData") {
          get {
            complete("")
          }
        } ~
        path("data.getBackData") {
          get {
            complete("")
          }
        } ~
        path("disconnect") {
          get {
            complete("")
          }
        }
    }
  }
}

object RestRoute {
  def apply(): RestRoute = new RestRoute()
}
