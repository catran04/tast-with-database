package com.tinkoff.database.dao.sql

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.sql.MySqlConnector
import com.tinkoff.model.TimeData
import com.tinkoff.options.ApplicationOptions

/**
  * Created by Administrator on 3/6/2018.
  */
class MySqlDao(appOptions: ApplicationOptions) extends Dao{

  private val connection = MySqlConnector.getConnection(appOptions.mysql)

  override def addTimeData(elem: TimeData): Unit = ???

  override def addTimeDatas(listElem: List[TimeData]): Unit = ???

  override def getAllTimeData(): List[TimeData] = ???

  override def getBackTimeData(): List[TimeData] = ???

  override def deleteTimeData(): Unit = ???
}

object MySqlDao {
  def apply(appOptions: ApplicationOptions): MySqlDao = new MySqlDao(appOptions)
}
