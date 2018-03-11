package com.tinkoff.data

import com.tinkoff.model.TimeData
import org.apache.log4j.Logger
import org.joda.time.DateTime

import scala.collection.mutable

object DataBuilder {

  private val logger = Logger.getLogger(getClass)

  /**
    * creates List of data in TimeData view
    *
    * @param length - length of list
    * @return List[TimeData]
    */
  def apply(length: Int): List[TimeData] = {
    if(length <= 0) throw new IllegalArgumentException("length of data should be more than 0")
    var listData: mutable.MutableList[TimeData] = mutable.MutableList[TimeData]()
    for(i <- 0 until length) {
      if(i % 3 == 0) {
        val timeData = TimeData(id = i,timestamp = new DateTime(System.currentTimeMillis()- 10000000).toString("yyyy-MM-dd'T'HH:mm:ss.SSSz"), backTime = true)
        listData += timeData
      } else {
        val timeData = TimeData(id = i, timestamp = new DateTime().toString("yyyy-MM-dd'T'HH:mm:ss.SSSz"), backTime = false)
        listData += timeData
      }
    }
    logger.info(s"data was generated. Size = ${length}")
    listData.sortBy(data => data.id).toList
  }
}
