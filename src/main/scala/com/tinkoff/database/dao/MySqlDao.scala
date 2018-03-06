package com.tinkoff.database.dao

import com.tinkoff.model.TimeData

/**
  * Created by Administrator on 3/6/2018.
  */
class MySqlDao extends Dao{
  override def addTimeData(elem: TimeData): Unit = ???

  override def addTimeDatas(listElem: List[TimeData]): Unit = ???

  override def getAllTimeData(): List[TimeData] = ???

  override def getBackTimeData(): List[TimeData] = ???

  override def deleteTimeData(): Unit = ???
}
