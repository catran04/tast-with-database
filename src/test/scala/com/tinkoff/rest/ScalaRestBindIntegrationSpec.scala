package com.tinkoff.rest

import com.tinkoff.model.Response
import com.tinkoff.options.ApplicationOptions
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}


class ScalaRestBindIntegrationSpec extends FlatSpec with GivenWhenThen with Matchers{

  val argOptions = Array[String]("storage=mock", "dataLength=1000")
  ScalaRestBind.main(args = argOptions)
  val appOptions = ApplicationOptions()
  val host = appOptions.rest.host
  val port = appOptions.rest.port

  "sending request to getData" should "return response with data with length 1000" in {
    Given("entity response as String")
    val entityResponse = TestRequester.sendGetRequest(host, port, "tinkoff/data.getData")

    When("data length")
    val lengthData = Response.toResponse(entityResponse).get.data.get.length

    Then("length data should be equal 1000")
    lengthData shouldEqual 1000
  }

}
