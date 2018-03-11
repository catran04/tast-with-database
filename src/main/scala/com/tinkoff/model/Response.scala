package com.tinkoff.model

import org.apache.log4j.Logger
import org.json4s.{NoTypeHints, native}
import org.json4s.native.Serialization.{read, write}

import scala.util.{Failure, Success, Try}

/**
  * response for REST API
  */
case class Response(
                   responseCode: Int = 0,
                   data: Option[List[TimeData]] = None,
                   errorMessage: Option[String] = None
                   ) {

  override def toString: String = {
    implicit val formats = native.Serialization.formats(NoTypeHints)
    write[Response](this)
  }
}

object Response {
  private val logger = Logger.getLogger(getClass)

  def toResponse(stringResponse: String): Option[Response] = {
    if (stringResponse.isEmpty) {
      logger.warn("json is empty")
      return None
    }
    implicit val formats = native.Serialization.formats(NoTypeHints)

    Try(read[Response](stringResponse)) match {
      case Success(value) => Some(value)
      case Failure(t) => {
        logger.warn(s"error while deserialising ${stringResponse}.\n${t.printStackTrace()}")
        None
      }
    }

  }
}
