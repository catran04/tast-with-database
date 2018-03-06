package com.tinkoff.model

import org.apache.log4j.Logger
import org.json4s.{NoTypeHints, native}
import org.json4s.native.Serialization.{write, read}

import scala.util.{Failure, Success, Try}

/**
  * Created by Administrator on 3/6/2018.
  */
case class TimeData(
                     id: Int,
                     timestamp: String,
                     backTime: Boolean
                   ) {

  implicit val formats = native.Serialization.formats(NoTypeHints)
  private val logger: Logger = Logger.getLogger(getClass)


  override def toString: String = {
    write[TimeData](this)
  }

  def toTimeData(json: String): Option[TimeData] = {
    if (json.isEmpty) {
      logger.info("json is empty")
      return None
    }

    Try(read[TimeData](json)) match {
      case Success(value) => Some(value)
      case Failure(t) => {
        logger.warn(s"error while deserialising ${json}.\n${t.printStackTrace()}")
        None
      }
    }
  }
}
