package com.tinkoff.database.sql

import java.sql.{Connection, DriverManager}

/**
  * Created by Administrator on 3/5/2018.
  */
object DatabaseBulder {
  def main(args: Array[String]): Unit = {
    def main(args: Array[String]) {
      // connect to the database named "mysql" on the localhost
      val driver = "com.mysql.jdbc.Driver"
      val url = "jdbc:mysql://localhost/tinkoffDB"
      val username = "root"
      val password = "root"

      // there's probably a better way to do this
      var connection:Connection = null

      try {
        // make the connection
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)

        // create the statement, and run the select query
        val statement = connection.createStatement()
        var resultSet = statement.executeQuery("CREATE DATABASE menagerie;")
        resultSet = statement.executeQuery("SHOW DATABASES:")
        while ( resultSet.next() ) {
          val host = resultSet.getString("host")
          val user = resultSet.getString("user")
          println("host, user = " + host + ", " + user)
        }
        println(s"im here ${resultSet}")
      } catch {
        case e => e.printStackTrace
      } finally {
        connection.close()
      }
    }
  }

}