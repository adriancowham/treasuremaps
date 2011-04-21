package org.treasuremaps.xml

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

import scala.xml.XML

@RunWith(classOf[JUnitRunner])
class XmlSpec extends Spec with ShouldMatchers    {

	describe( "RSS Reader" ) {
		
		it( "should open an XML file" ) {
			val localRss = "data/index.rss"
			val feed = XML.loadFile( localRss )
		}
    }
}