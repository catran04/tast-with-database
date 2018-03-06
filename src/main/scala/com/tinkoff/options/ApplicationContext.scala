package com.tinkoff.options

import com.tinkoff.database.dao.Dao
import com.tinkoff.database.dao.sql.MySqlDao

/**
  * Created by Administrator on 3/6/2018.
  */
case class ApplicationContext(
                             options: ApplicationOptions = ApplicationOptions.defaults,
                             storage: Dao = MySqlDao()
                             ) {

}
