package com.tinkoff.options

/**
  * Created by Administrator on 3/6/2018.
  */
case class ApplicationOptions(
                             rest: RestOptions = RestOptions(),
                             mysql: MysqlOptions = MysqlOptions(),
                             redis: RedisOptions = RedisOptions()
                             ) {

}

case class RestOptions(
                      host: String = "localhost",
                      port: Int = 9080
                      ){}

case class MysqlOptions(
                       host: String = "localhost",
                       port: Int = 4032
                       ){}

case class RedisOptions (
                          val host: String = "localhost",
                          port: Int = 6379,
                          delimiter: String = "."
                        ){}
