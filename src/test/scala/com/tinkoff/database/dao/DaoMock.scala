package com.tinkoff.database.dao

import com.tinkoff.model.TimeData
import org.apache.log4j.Logger

/**
  * Created by Administrator on 3/6/2018.
  */
class DaoMock extends Dao{

  private val logger = Logger.getLogger(getClass)

  override def addTimeData(elem: TimeData): Unit = {
    logger.info(s"adding elem ${elem}")
  }

  override def addTimeDatas(listElem: List[TimeData]): Unit = {
    logger.info(s"adding list of elems ${listElem}")
  }

  override def getAllTimeData(): List[TimeData] = {
    logger.info("getting all data")
    List.empty
  }

  override def getBackTimeData(): List[TimeData] = {
    logger.info("getting back time data")
    List.empty
  }

  override def deleteTimeData(): Unit = {
    logger.info("deleting all data")
  }
}
