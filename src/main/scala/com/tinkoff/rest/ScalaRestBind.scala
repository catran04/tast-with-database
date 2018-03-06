package com.tinkoff.rest

import java.sql.DriverManager

import com.tinkoff.options.{ApplicationContext, ApplicationOptions}
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


  def launch(): Unit = {

    DateTimeZone.setDefault(DateTimeZone.UTC)

    val options = applicationContext.options

    logger.debug(s"Options applied: '${options}'")
//    logger.info(s"Running Scala application... ${options.rest.app_name()}...")

    logger.info("Job started")

    implicit val system: ActorSystem = ActorSystem("system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher // needed for the future flatMap/onComplete in the end
    implicit val formats = native.Serialization.formats(NoTypeHints)

    val route = RestRoute()

      val host = options.rest.host
      val port = options.rest.port

      Http().bindAndHandle(route.route, host, port)
      logger.info(s"Rule REST server is now listening on http://$host:$port/...")
  }
}

object ScalaRestBind {

  def main(args: Array[String]): Unit = {
    println(s"Parsing input arguments: '${args.mkString(" ")}'")
    val applicationOptions = ApplicationOptions(args)

    val applicationContext = ApplicationContext(applicationOptions)

    val app = new ScalaRestBind(applicationContext)
    app.launch()
  }

//  def testLaunch(args: Array[String]): Unit = {
//    new ScalaRestBind(AppContextForRest.testApplicationContext).launch(args)
//  }
}
