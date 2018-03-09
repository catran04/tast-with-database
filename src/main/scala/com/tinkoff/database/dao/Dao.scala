package com.tinkoff.database.dao

import com.tinkoff.model.TimeData

/**
  * Created by Administrator on 3/6/2018.
  */
trait Dao {

  def addTimeData(length: Int): Unit
  def getAllTimeData(): List[TimeData]
  def getBackTimeData(): List[TimeData]
  def deleteTimeData(): Unit
}
