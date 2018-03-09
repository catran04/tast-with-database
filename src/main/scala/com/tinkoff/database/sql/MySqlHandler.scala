package com.tinkoff.database.sql

import java.sql.{Connection, ResultSet, Statement}

import com.tinkoff.data.DataBuilder
import com.tinkoff.model.TimeData
import com.tinkoff.options.MysqlOptions
import org.apache.log4j.Logger

import scala.collection.mutable

/**
  * Created by Administrator on 3/8/2018.
  */
class MySqlHandler(cn: Connection, options: MysqlOptions) {



  private val logger = Logger.getLogger(getClass)
  private val statement = cn.createStatement()


  def useDB(): Unit = {
    statement.execute(s"USE ${options.databaseName};")
    logger.info(s"database ${options.databaseName} is used")
  }

  def createTable(): Unit = {
    statement.execute("CREATE TABLE IF NOT EXIST timeData(" +
      "id INTEGER PRIMARY KEY," +
      "timestamp TEXT," +
      "backField BOOLEAN" +
      ");")
    logger.info("the table 'timeData was created")
  }

  def addTimeData(length: Int): Unit = {
    val data = DataBuilder(length)
    val ps = cn.prepareStatement(s"INSERT INTO ${options.databaseName} (id, timestamp, backField) VALUES (?, ?, ?);")
    cn.setAutoCommit(false)
    for(i <- 0 until data.length) {
      ps.setInt(1 , data(i).id)
      ps.setString(2, data(i).timestamp)
      ps.setBoolean(3, data(i).backTime)
    }
    cn.setAutoCommit(true)
    logger.info(s"the data with length = ${length} was added to DB ${options.databaseName}")
  }

  def getAllTimeData(): List[TimeData] = {
    val rs: ResultSet = statement.executeQuery("SELECT * FROM timeData;")
    var timeDatas: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    while(rs.next()) {
      val timeData = TimeData(id = rs.getInt(1), timestamp = rs.getString(2), backTime = rs.getBoolean(3))
      timeDatas += timeData
    }
    timeDatas.toList
  }

  def getBackTimeData(): List[TimeData] = {
    val rs = statement.executeQuery("SELECT * FROM timeData WHERE backField = true;")
    var backTimeDatas: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    while(rs.next()) {
      val timeData = TimeData(id = rs.getInt(1), timestamp = rs.getString(2), backTime = rs.getBoolean(3))
      backTimeDatas += timeData
    }
    backTimeDatas.toList
  }
}

object MySqlHandler {
  def apply(cn: Connection, options: MysqlOptions): MySqlHandler = new MySqlHandler(cn, options)
}
