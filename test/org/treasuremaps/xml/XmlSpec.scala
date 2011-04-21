package org.treasuremaps.xml

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import scala.xml.XML

class XmlSpec extends Spec with ShouldMatchers    {

	describe( "RSS Reader" ) {
		
		it( "should open an XML file" ) {
			val localRss = "data/index.rss"
			val feed = XML.loadFile( localRss )
		}
    }
}