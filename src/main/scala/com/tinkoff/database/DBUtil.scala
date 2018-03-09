package com.tinkoff.database

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.dao.sql.SqlDao
import com.tinkoff.database.sql.MySqlConnector
import com.tinkoff.options.ApplicationOptions

/**
  * Created by Administrator on 3/9/2018.
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
