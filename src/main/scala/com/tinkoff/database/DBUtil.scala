package com.tinkoff.database

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.dao.sql.SqlDao
import com.tinkoff.database.sql.MySqlConnector
import com.tinkoff.options.ApplicationOptions

/**
  * this method is receiving option and returns the implementation of Dao
  */
object DBUtil {
  def apply(options: ApplicationOptions): Dao = {
    options.storage match {
      case "MySql" => SqlDao(options, MySqlConnector)
      case "mock" => DaoMock(options.dataLength)
      case otherString => throw new IllegalArgumentException(s"using userStorage ${otherString} is impossible")
    }
  }
}
