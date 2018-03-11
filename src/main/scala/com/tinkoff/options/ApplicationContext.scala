package com.tinkoff.options

import com.tinkoff.database.dao.Dao

/**
  * contains the different info for using application. Maybe to change
  */
case class ApplicationContext(
                             options: ApplicationOptions,
                             storage: Dao
                             ) {

}
