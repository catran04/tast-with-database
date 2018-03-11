package com.tinkoff.data

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

class DataBuilderSpec extends FlatSpec with GivenWhenThen with Matchers{

  val databuilder = DataBuilder

  "databuilder" should "return list with length = 100" in {
    databuilder(100).length shouldEqual 100
  }

  "each elem of the list of data" should "have unique id" in {
    Given("list of data")
    val listOfData = databuilder(1000)

    When("list of duplicate ids")
    val listDuplicates = listOfData.map(elem => elem.id).groupBy(identity).collect { case (x, List(_, _, _*)) => x }

    Then("list of duplicate ids should be empty")
    listDuplicates shouldBe empty
  }

  "databuilder" should "throw IllegalArgumentException if length <= 0" in {
    assertThrows[IllegalArgumentException]{databuilder(0)}
  }

}
