package com.tinkoff.database.dao.sql

import java.sql.Connection

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.sql.{SqlConnector, SqlHandler}
import com.tinkoff.model.TimeData
import com.tinkoff.options.ApplicationOptions

/**
  * it is using for the handling of data from sql storage
  */
class SqlDao(appOptions: ApplicationOptions, sqlConnector: SqlConnector) extends Dao{

  private val connection: Connection = sqlConnector.getConnection(appOptions)
  private val mySqlHandler = SqlHandler(connection, appOptions)
  mySqlHandler.useDB()
  mySqlHandler.createTable()
  addTimeData(appOptions.dataLength)

  override def addTimeData(length: Int): Unit = {
    mySqlHandler.addTimeData(length)
  }

  override def getAllTimeData(): List[TimeData] = {
    mySqlHandler.getAllTimeData()
  }

  override def getBackTimeData(): List[TimeData] = {
    mySqlHandler.getBackTimeData()
  }

  override def deleteTimeDataAndDisconnect(): Unit = {
    mySqlHandler.disconnect()
  }
}

object SqlDao {
  def apply(appOptions: ApplicationOptions, sqlConnector: SqlConnector): SqlDao = new SqlDao(appOptions, sqlConnector)
}
