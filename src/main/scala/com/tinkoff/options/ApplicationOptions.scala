package com.tinkoff.options

/**
  * using for application the different options
  */
case class ApplicationOptions(
                               storage: String = "mock",
                               dataLength: Int = 10000,
                               rest: RestOptions = RestOptions(),
                               mysql: MysqlOptions = MysqlOptions(),
                               redis: RedisOptions = RedisOptions()
                             ) {
}

object ApplicationOptions {
  val defaults = new ApplicationOptions()

  /**
    * Initialize options with given arguments
    */
  def apply(args: Array[String]): ApplicationOptions = {
    println(s"args ${args.mkString(", ")}")
    if(args.isEmpty) return ApplicationOptions.defaults
    args.foldLeft(ApplicationOptions()) { (options, arg) =>
      arg.split("=") match {
        case Array("dataLength", value) => options.copy(dataLength = value.toInt)
        case Array("storage", value) => options.copy(storage = value)

        case Array("rest.host", value) => options.copy(rest = options.rest.copy(host = value))
        case Array("rest.port", value) => options.copy(rest = options.rest.copy(port = value.toInt))

        case Array("mysql.host", value) => options.copy(mysql = options.mysql.copy(host = value))
        case Array("mysql.port", value) => options.copy(mysql = options.mysql.copy(port = value.toInt))
        case Array("mysql.databaseName", value) => options.copy(mysql = options.mysql.copy(databaseName = value))
        case Array("mysql.autoReconnect", value) => options.copy(mysql = options.mysql.copy(autoReconnect = value.toBoolean))
        case Array("mysql.useSSL", value) => options.copy(mysql = options.mysql.copy(useSSL = value.toBoolean))
        case Array("mysql.useJDBCCompliantTimezoneShift", value) => options.copy(mysql = options.mysql.copy(useJDBCCompliantTimezoneShift = value.toBoolean))
        case Array("mysql.useLegacyDatetimeCode", value) => options.copy(mysql = options.mysql.copy(useLegacyDatetimeCode = value.toBoolean))
        case Array("mysql.serverTimezone", value) => options.copy(mysql = options.mysql.copy(serverTimezone = value))
        case Array("mysql.driver", value) => options.copy(mysql = options.mysql.copy(driver = value))
        case Array("mysql.username", value) => options.copy(mysql = options.mysql.copy(username = value))
        case Array("mysql.password", value) => options.copy(mysql = options.mysql.copy(password = value))

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
                       port: Int = 3306,
                       databaseName: String = "tinkoff",
                       autoReconnect: Boolean = true,
                       useSSL: Boolean = false,
                       useJDBCCompliantTimezoneShift: Boolean = true,
                       useLegacyDatetimeCode:Boolean = false,
                       serverTimezone: String = "UTC",
                       driver: String = "com.mysql.cj.jdbc.Driver",
                       username: String = "root",
                       password: String = "CrisBad33"
                       ){}

case class RedisOptions (
                          host: String = "localhost",
                          port: Int = 6379,
                          delimiter: String = "."
                        ){}
