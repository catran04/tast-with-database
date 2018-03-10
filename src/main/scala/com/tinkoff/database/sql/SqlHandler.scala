package com.tinkoff.database.sql

import java.sql.{Connection, ResultSet}

import com.tinkoff.data.DataBuilder
import com.tinkoff.model.TimeData
import com.tinkoff.options.{ApplicationOptions, MysqlOptions}
import org.apache.log4j.Logger

import scala.collection.mutable

/**
  * contains some command for sql
  */
class SqlHandler(cn: Connection, options: ApplicationOptions) {



  private val logger = Logger.getLogger(getClass)
  private val statement = cn.createStatement()

  /**
    * the method for using concrete database.
    */
  def useDB(): Unit = {
    statement.execute(s"USE ${options.mysql.databaseName};")
    logger.info(s"database ${options.mysql.databaseName} is used")
  }

  /**
    * creates the table with name that defines in options
    */
  def createTable(): Unit = {
    statement.execute("CREATE TABLE IF NOT EXISTS timeData(" +
      "id INTEGER PRIMARY KEY," +
      "timestamp TEXT," +
      "backField BOOLEAN" +
      ");")
    logger.info("the table 'timeData was created")
  }

  /**
    * creates list of TimeData and adds to the sql storage
    * @param length: Int - length of list of TimeData
    */
  def addTimeData(length: Int): Unit = {
    val data = DataBuilder(length)
    val ps = cn.prepareStatement(s"INSERT IGNORE INTO timedata (id, timestamp, backField) VALUES (?, ?, ?);")
    cn.setAutoCommit(false)
    for(i <- 0 until data.length) {
      ps.setInt(1 , data(i).id)
      ps.setString(2, data(i).timestamp)
      ps.setBoolean(3, data(i).backTime)
      ps.executeUpdate()
    }
    cn.setAutoCommit(true)
  }

  /**
    * @return from the sql storage all list of timeData
    */
  def getAllTimeData(): List[TimeData] = {
    val rs: ResultSet = statement.executeQuery("SELECT * FROM timeData ORDER BY id;")
    var timeDatas: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    while(rs.next()) {
      val timeData = TimeData(id = rs.getInt(1), timestamp = rs.getString(2), backTime = rs.getBoolean(3))
      timeDatas += timeData
    }
    timeDatas.toList
  }

  /**
    * @return from the sql storage only back time data
    */
  def getBackTimeData(): List[TimeData] = {
    val rs = statement.executeQuery("SELECT * FROM timedata WHERE backField = true ORDER BY id;")
    var backTimeDatas: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    while(rs.next()) {
      val timeData = TimeData(id = rs.getInt(1), timestamp = rs.getString(2), backTime = rs.getBoolean(3))
      backTimeDatas += timeData
    }
    backTimeDatas.toList
  }
}

object  SqlHandler {
  def apply(cn: Connection, options: ApplicationOptions): SqlHandler = new SqlHandler(cn, options)
}
