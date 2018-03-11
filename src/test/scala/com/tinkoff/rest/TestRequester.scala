package com.tinkoff.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, RequestEntity}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods.GET
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.util.ByteString

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._


/**
  * Created by Administrator on 3/10/2018.
  */
object TestRequester {
  def sendGetRequest(host: String, port: Int, uri: String): String = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher
    val url = s"http://${host}:${port}/${uri}"
    val timeout = 5000.millis


    val respEntity = for {
      response <- Http().singleRequest(HttpRequest(method = HttpMethods.GET, uri = url))
      entity <- Unmarshal(response.entity).to[ByteString]
    } yield entity

    val payload: Future[ByteString] = respEntity.andThen {
      case Success(entity) =>
        entity.utf8String
      case Failure(ex) =>
        s"""{"error": "${ex.getMessage}"}"""
    }
    val byteString = Await.result(payload, timeout)
    byteString.utf8String
  }
}
