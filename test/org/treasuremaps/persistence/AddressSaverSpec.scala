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
      new TestDataAcquirer("test/data/gms/sacramento/index.rss") ,
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
     saver.persistAddys( results )
     //TODO... assert on something here
  }

}