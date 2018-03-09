package com.tinkoff.database

import com.tinkoff.data.DataBuilder
import com.tinkoff.database.dao.Dao
import com.tinkoff.model.TimeData
import org.apache.log4j.Logger

/**
  * Created by Administrator on 3/6/2018.
  */
class DaoMock extends Dao {

  private val logger = Logger.getLogger(getClass)
  private var timeData: List[TimeData] = _

  override def addTimeData(length: Int): Unit = {
    DataBuilder(length)
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

  override def deleteTimeData(): Unit = {
    logger.info("deleting all data")
  }
}

object DaoMock {
  def apply(): DaoMock = new DaoMock()
}
