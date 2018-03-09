package com.tinkoff.database.dao.sql

import java.sql.Connection

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.sql.{MySqlConnector, MySqlHandler}
import com.tinkoff.model.TimeData
import com.tinkoff.options.{ApplicationOptions, MysqlOptions}

/**
  * Created by Administrator on 3/6/2018.
  */
class MySqlDao(appOptions: MysqlOptions) extends Dao{

  private val connection: Connection = MySqlConnector.getConnection(appOptions)
  private val mySqlHandler = MySqlHandler(connection, appOptions)

  override def addTimeData(length: Int): Unit = {
    mySqlHandler.addTimeData(length)
  }

  override def getAllTimeData(): List[TimeData] = {
    mySqlHandler.getAllTimeData()
  }

  override def getBackTimeData(): List[TimeData] = {
    mySqlHandler.getBackTimeData()
  }

  override def deleteTimeData(): Unit = {

  }
}

object MySqlDao {
  def apply(appOptions: MysqlOptions): MySqlDao = new MySqlDao(appOptions)
}
