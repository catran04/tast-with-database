package com.tinkoff.rest

import java.sql.DriverManager

import com.tinkoff.options.ApplicationContext
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.HeaderDirectives
import akka.stream.ActorMaterializer
import org.joda.time.DateTimeZone
import org.json4s.{NoTypeHints, native}
import org.apache.log4j.Logger


/**
  * Created by Administrator on 3/5/2018.
  */
class ScalaRestBind (applicationContext: ApplicationContext) extends HeaderDirectives{

  private val logger: Logger = Logger.getLogger(getClass)


  def launch(args: Array[String]): Unit = {

    DateTimeZone.setDefault(DateTimeZone.UTC)
    println(s"Parsing input arguments: '${args.mkString(" ")}'")

    logger.debug(s"Options applied: '${_options}'")
    logger.info(s"Running Scala application... ${_options.app_name()}...")

    logger.info(ConfigClient.source)
    logger.info("Job started")

    implicit val system: ActorSystem = ActorSystem("system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher // needed for the future flatMap/onComplete in the end
    implicit val formats = native.Serialization.formats(NoTypeHints)

    val route = RestRoute(applicationContext)

    val listenAddresses = _options.rest.listen_addresses()
    logger.info(s"Listen addresses: $listenAddresses")

    val defaultPort = _options.rest.port()

    for (host <- listenAddresses.split(",")) {
      var tempHost = host.trim
      var tempPort = defaultPort
      if (host.contains(":")) {
        val hostAndPort = host.split(":")
        tempHost = hostAndPort.head
        tempPort = hostAndPort(1).toInt
      }
      val serverBinding = Http().bindAndHandle(route.route, tempHost, tempPort) // TODO: include support https
      logger.info(s"Rule REST server is now listening on http://$tempHost:$tempPort/...")
    }
  }
}

object ScalaRestBind {

  def main(args: Array[String]): Unit = {
    val applicationOptions = ApplicationOptions(args)

    val app = new ScalaRestBind(AppContextForRest.applicationContext(applicationOptions))
    app.launch(args)
  }

  def testLaunch(args: Array[String]): Unit = {
    new ScalaRestBind(AppContextForRest.testApplicationContext).launch(args)
  }
}
