package com.tinkoff.data

import java.sql.SQLException

import akka.http.scaladsl.model.StatusCodes
import com.tinkoff.model.{Response, TimeData}
import com.tinkoff.options.ApplicationContext
import org.apache.log4j.Logger

/**
  * Created by Administrator on 3/6/2018.
  */
class DataBuilder(appContext: ApplicationContext) {
  val logger = Logger.getLogger(getClass)
  val storage = appContext.storage

  def getBackData: Response = {
    try {
      val backData = storage.getBackTimeData()
      if(backData.isEmpty){
        logger.error("BackData was not found")
        return respondFailure(StatusCodes.NotFound.intValue, "BackData was not found")
      }
      respondSuccess(backData)
    } catch {
      case sqlExc: SQLException =>
        logger.error(sqlExc.printStackTrace())
        respondFailure(StatusCodes.InternalServerError.intValue, "Internal server error. We are already doing our best. Please try again later.")
      case exc: Exception =>
        logger.error(exc.printStackTrace())
        respondFailure(StatusCodes.InternalServerError.intValue, "internal server error. We are already doing our best. Please try again later.")
    }
  }

  def getAllData: Response = {
    try {
      val allData = storage.getAllTimeData()
      if(allData.isEmpty){
        logger.error("Data was not found")
        return respondFailure(StatusCodes.NotFound.intValue, "Data was not found")
      }
      respondSuccess(allData)
    } catch {
      case sqlExc: SQLException =>
        logger.error(sqlExc.printStackTrace())
        respondFailure(StatusCodes.InternalServerError.intValue, "Internal server error. We are already doing our best. Please try again later.")
      case exc: Exception =>
        logger.error(exc.printStackTrace())
        respondFailure(StatusCodes.InternalServerError.intValue, "internal server error. We are already doing our best. Please try again later.")
    }
  }

  private def respondFailure(responseCode: Int, errorMessage: String): Response = {
    Response(responseCode = responseCode, errorMessage = Some(errorMessage))
  }

  private def respondSuccess(data: List[TimeData]): Response = {
    Response(data = Some(data))
  }

}