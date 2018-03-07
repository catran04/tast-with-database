package com.tinkoff.data

import com.tinkoff.model.TimeData
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

/**
  * Created by Administrator on 3/6/2018.
  */
class DataBuilderSpec extends FlatSpec with GivenWhenThen with Matchers{

  val databuilder = DataBuilder

  "databuilder" should "return list with lenght = 100" in {
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

}
