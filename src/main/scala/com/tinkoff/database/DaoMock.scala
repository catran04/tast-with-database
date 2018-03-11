package com.tinkoff.database

import com.tinkoff.data.DataBuilder
import com.tinkoff.database.dao.Dao
import com.tinkoff.model.TimeData
import org.apache.log4j.Logger

/**
  * Using for the tests or mock using
  */
class DaoMock(dataLength: Int) extends Dao {

  private val logger = Logger.getLogger(getClass)
  private var timeData: List[TimeData] = _
  addTimeData(dataLength)

  override def addTimeData(length: Int): Unit = {
    timeData = DataBuilder(length)
    logger.info(s"adding timeData. Length = ${length}")
  }

  override def getAllTimeData(): List[TimeData] = {
    logger.info("getting all data")
    timeData
  }

  override def getBackTimeData(): List[TimeData] = {
    logger.info("getting back time data")
    timeData.filter(elem => elem.backTime)
  }

  override def deleteTimeDataAndDisconnect(): Unit = {
    logger.info("deleting all data")
  }
}

object DaoMock {
  def apply(dataLength: Int): DaoMock = new DaoMock(dataLength)
}
