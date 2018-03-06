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

object ApplicationOptions {
  val defaults = ApplicationOptions(Array.empty[String])

  /**
    * Initialize options with given arguments
    */
  def apply(args: Array[String]): ApplicationOptions = {
    if(args.isEmpty) return ApplicationOptions.defaults
    args.foldLeft(ApplicationOptions()) { (options, arg) =>
      arg.split("=") match {
        case Array("rest.host", value) => options.copy(rest = options.rest.copy(host = value))
        case Array("rest.port", value) => options.copy(rest = options.rest.copy(port = value.toInt))
        case Array("mysql.host", value) => options.copy(mysql = options.mysql.copy(host = value))
        case Array("mysql.port", value) => options.copy(mysql = options.mysql.copy(port = value.toInt))
        case Array("redis.host", value) => options.copy(redis = options.redis.copy(host = value))
        case Array("redis.port", value) => options.copy(redis = options.redis.copy(port = value.toInt))
        case Array("redis.delimiter", value) => options.copy(redis = options.redis.copy(delimiter = value))
        case exc => throw new RuntimeException(s"invalid args: ${exc.mkString}")
      }
    }
  }
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
                          host: String = "localhost",
                          port: Int = 6379,
                          delimiter: String = "."
                        ){}
