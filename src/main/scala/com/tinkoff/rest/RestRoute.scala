package com.tinkoff.rest


import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server
import akka.http.scaladsl.server.{Directives, StandardRoute}
import com.tinkoff.data.DataHandler
import com.tinkoff.options.ApplicationContext

/**
  * Created by Administrator on 3/6/2018.
  */
class RestRoute(appContext: ApplicationContext) extends Directives {

  private val dataBuilder = new DataHandler(appContext)

  def route: server.Route = {
    pathPrefix("tinkoff") {
      path("data.getData") {
        get {
          val response = dataBuilder.getAllData
          response.errorMessage match {
            case None => processSuccess(response.toString)
            case Some(msg) => processFailure(response.toString, response.responseCode)
          }
        }
      } ~
        path("data.getBackData") {
          get {
            val response = dataBuilder.getBackData
            response.errorMessage match {
              case None => processSuccess(response.toString)
              case Some(msg) => processFailure(response.toString, response.responseCode)
            }
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
      case _ => complete(StatusCodes.InternalServerError.intValue, responseAsJson)
    }
  }
}

object RestRoute {
  def apply(appContext: ApplicationContext): RestRoute = new RestRoute(appContext)
}
