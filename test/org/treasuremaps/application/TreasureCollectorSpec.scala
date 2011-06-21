package org.treasuremaps.application

import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.util.matching.Regex
import scala.xml.XML
import scala.xml._
import java.net._
import scala.io.Source
import scala.collection.immutable.List
import org.treasuremaps.regex.AddressRegex
import org.treasuremaps.acquisition._

@RunWith(classOf[JUnitRunner])
class TreasureCollectorSpec extends Spec {

  describe("TreasureCollector Returns results that are not empty") {
    val rss = XML load (new URL("http://sacramento.craigslist.org/gms/index.rss").openConnection.getInputStream)
    val acquirer = new TestDataAcquirer("test/data/gms/sacramento/index.rss")
    
    var collector = new TreasureCollector

    val results = collector.collectTreasure(acquirer,
      Map("ways"    -> AddressRegex.FullyQualifiedWay,
          "streets" -> AddressRegex.FullyQualifiedStreet,
          "courts"  -> AddressRegex.FullyQualifiedCourt,
          "avenues" -> AddressRegex.FullyQualifiedAvenue,
          "places"  -> AddressRegex.FullyQualifiedPlace,
          "lanes"   -> AddressRegex.FullyQualifiedLane,
          "circles" -> AddressRegex.FullyQualifiedCircle,
          "roads"   -> AddressRegex.FullyQualifiedRoad,
          "drives"  -> AddressRegex.FullyQualifiedDrive)
     )
    
    it("should return a collection that is not empty") {
      assert(!results.isEmpty)
    }
    it("should return a collection that has more than one set mapped") {

      assert(results.size > 1)
    }
    it("ways should return a set that has more than 1 result") {
      val waysSet = results("ways")
      assert(waysSet.size > 1)

    }
    it("should return have some unidentfiables") {
      val waysSet = results("unidentifiables")
      assert(waysSet.size > 1)
    }
    val cats2counts = collector.computeStats(results)
     it("should return have counts") {
    	val ways = cats2counts.get("ways")
    	
    }
    
  }
}