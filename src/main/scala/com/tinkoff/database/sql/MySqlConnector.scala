package com.tinkoff.database.sql

import java.sql.{Connection, DriverManager, Statement}

import com.tinkoff.options.MysqlOptions
import org.apache.log4j.Logger

/**
  * Created by Administrator on 3/5/2018.
  */
object MySqlConnector {

  private val logger = Logger.getLogger(getClass)

  def getConnection(options: MysqlOptions): Connection = {

    val driver = options.driver

    val host = options.host
    val port = options.port
    val databaseName = options.databaseName
    val autoReconnect = options.autoReconnect
    val useSSL = options.useSSL
    val useJDBCCompliantTimezoneShift = options.useJDBCCompliantTimezoneShift
    val useLegacyDatetimeCode = options.useLegacyDatetimeCode
    val serverTimezone = options.serverTimezone


    val url = s"jdbc:mysql://${host}:${port}/${databaseName}" +
      s"?autoReconnect=${autoReconnect}&useSSL=${useSSL}&useJDBCCompliantTimezoneShift=${useJDBCCompliantTimezoneShift}" +
      s"&useLegacyDatetimeCode=${useLegacyDatetimeCode}&serverTimezone=${serverTimezone}"
    println(s"url ${url}")

    val username = options.username
    val password = options.password

    Class.forName(driver)
    val connection = DriverManager.getConnection(url, username, password)
    logger.info(s"connection to database ${databaseName} was success")
    connection
  }
}
