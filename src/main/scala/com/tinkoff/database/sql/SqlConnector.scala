package com.tinkoff.database.sql

import java.sql.Connection

import com.tinkoff.options.ApplicationOptions

/**
  * Created by Administrator on 3/9/2018.
  */
trait SqlConnector {

  def getConnection(options: ApplicationOptions): Connection
}
