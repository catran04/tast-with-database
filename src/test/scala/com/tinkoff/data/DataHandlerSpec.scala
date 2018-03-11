package com.tinkoff.data

import com.tinkoff.database.DaoMock
import com.tinkoff.options.{ApplicationContext, ApplicationOptions}
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}


class DataHandlerSpec extends FlatSpec with GivenWhenThen with Matchers{

  val appOptions = ApplicationOptions()
  val dao = DaoMock(1000)
  val appContext = ApplicationContext(appOptions, dao)
  val dataHandler = new DataHandler(appContext)

  "getBackData" should "return response with responseCode 0" in {
    Given("expected response code")
    val expectedResponseCode = 0

    When("response code")
    val responseCode = dataHandler.getBackData.responseCode

    Then("response code should be equal expected response code")
    responseCode shouldEqual expectedResponseCode
  }

  "getAllData" should "return response with responseCode 0" in {
    Given("expected response code")
    val expectedResponseCode = 0

    When("response code")
    val responseCode = dataHandler.getAllData.responseCode

    Then("response code should be equal expected response code")
    responseCode shouldEqual expectedResponseCode
  }
}
