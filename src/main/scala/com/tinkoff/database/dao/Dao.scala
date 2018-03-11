package com.tinkoff.database.dao

import com.tinkoff.model.TimeData

/**
  * defines the different methods for the some implementations
  */
trait Dao {

  def addTimeData(length: Int): Unit
  def getAllTimeData(): List[TimeData]
  def getBackTimeData(): List[TimeData]
  def deleteTimeDataAndDisconnect(): Unit
}
