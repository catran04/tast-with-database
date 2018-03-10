package com.tinkoff.data

import java.sql.SQLException

import akka.http.scaladsl.model.StatusCodes
import com.tinkoff.model.{Response, TimeData}
import com.tinkoff.options.ApplicationContext
import org.apache.log4j.Logger

/**
  * it is using for the different operation of data
  */
class DataHandler(appContext: ApplicationContext) {
  private val logger = Logger.getLogger(getClass)
  private val storage = appContext.storage

  /**
    * @return response that consist of responseCode and the filtered TimeData or errorMessage
    */
  def getBackData: Response = {
    try {
      val backData = storage.getBackTimeData()
      if(backData.isEmpty){
        logger.error("BackData was not found")
        return respondFailure(StatusCodes.NotFound.intValue, "BackData was not found")
      }
      logger.info(s"getting data. Length = ${backData.length}")
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

  /**
    *
    * @return response that consist of response code and all TimeData or errorMessage
    */
  def getAllData: Response = {
    try {
      val allData = storage.getAllTimeData()
      if(allData.isEmpty){
        logger.error("Data was not found")
        return respondFailure(StatusCodes.NotFound.intValue, "Data was not found")
      }
      logger.info(s"getting data. Length = ${allData.length}")
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
