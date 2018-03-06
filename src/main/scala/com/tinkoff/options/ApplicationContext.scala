package com.tinkoff.options

import com.tinkoff.database.dao.Dao

/**
  * Created by Administrator on 3/6/2018.
  */
case class ApplicationContext(
                             options: ApplicationOptions,
                             storage: Dao
                             ) {

}
