package com.tinkoff.rest


import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.{Directives, StandardRoute}

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
            val response = service.validateAndRespond("")(token, someFixId)
            response.errorMessage match {
              case None => processSuccess(response.toString, s"getting the values of attribute: '${someFixId}'")
              case Some(msg) => processFailure(response.toString, response.responseCode)
            }
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

  private def processSuccess(responseAsJson: String): StandardRoute = {
    complete(StatusCodes.OK, responseAsJson)
  }

  private def processFailure(responseAsJson: String, responseCode: Int): StandardRoute = {
    responseCode match {
      case StatusCodes.NotFound.intValue => complete(StatusCodes.NotFound.intValue, responseAsJson)
      case _                             => complete(StatusCodes.InternalServerError.intValue, responseAsJson)
    }
}

object RestRoute {
  def apply(): RestRoute = new RestRoute()
}
