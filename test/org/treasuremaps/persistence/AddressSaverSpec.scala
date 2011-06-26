package org.treasuremaps.persistence

import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.treasuremaps.application.TreasureCollector
import org.treasuremaps.regex.AddressRegex
import scala.xml.XML
import java.net._
import org.treasuremaps.acquisition._

@RunWith(classOf[JUnitRunner])
class AddressSaverSpec extends Spec{
  describe("Should produce file as side effect"){
    
    val results = new TreasureCollector().collectTreasure(
      new TestDataAcquirer("data/index.rss") ,
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
     val saver = new AddressSaver
     /*This is lame for now, really have just moved the naming of the file (by region)
      * outside of AddressSaver, but there is no mechanism to actually automagically get the region
      */
     
     val  files = saver.persistAddys( results, "sacramento" )
     it("files should not be empty"){
    	assert(files.size > 0)
    }
    it("should produce files that exist"){
      files.foreach{case(file) =>
        println(file.getName() )
        assert(file.exists())
      }
    }
     it("shold have exactly one file per category"){
       assert(files.size == 10,"size is: " + files.size )
     }
    
     
  }

}