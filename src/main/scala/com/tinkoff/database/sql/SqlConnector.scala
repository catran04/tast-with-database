package com.tinkoff.database.sql

import java.sql.Connection

import com.tinkoff.options.ApplicationOptions

/**
  * defines the connection to the MySQL server
  */
trait SqlConnector {

  def getConnection(options: ApplicationOptions): Connection
}
