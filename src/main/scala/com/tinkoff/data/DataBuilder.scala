package com.tinkoff.data

import com.tinkoff.model.TimeData
import org.apache.log4j.Logger
import org.joda.time.DateTime

import scala.collection.mutable
import scala.util.Random

/**
  * Created by Administrator on 3/6/2018.
  */
object DataBuilder {

  private val random = Random
  private val dateTime = new DateTime()
  private val logger = Logger.getLogger(getClass)

  def apply(lenght: Int): List[TimeData] = {
    var listData: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    for(i <- 0 until lenght) {
      if(i % 3 == 0) {
        val timeData = TimeData(id = i,timestamp = dateTime.toString(), backTime = true)
        listData += timeData
      } else {
        val timeData = TimeData(id = i, timestamp = dateTime.toString(), backTime = false)
        listData += timeData
      }
    }
    logger.info(s"data was generated. Size = ${lenght}")
    listData.sortBy(data => data.id).toList
  }
}
