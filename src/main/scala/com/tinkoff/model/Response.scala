package com.tinkoff.model

import org.json4s.{NoTypeHints, native}
import org.json4s.native.Serialization.write

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
