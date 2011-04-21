package org.treasuremaps.xml

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import scala.xml.XML

class XmlSpec extends Spec with ShouldMatchers {

  describe ( "RSS Spec" ) {
	  val localRss = "data/index.rss"
	    
	  it( "should open a file" ) {
		  val feed = XML.loadFile( localRss )
		  feed should not be( null )
	  }    
  }
}