package org.treasuremaps.regex

import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.io.Source
import scala.util.matching.Regex
import scala.xml.XML

@RunWith( classOf[ JUnitRunner ] )
class AddressRegexSpec extends Spec with ShouldMatchers {

	describe( "Address Regular Expressions" ) {
		
		val expected = Source.fromFile( "data/addresses/ends_with_way_expected.txt" ).getLines.toArray
				
		val addyRegex = new Regex( """(?s).*(\d{4} .* [Ww]ay).*""" );
		val feed = XML loadFile( "data/addresses/ends_with_way.xml" );
		val posts = feed \ "item"
		
		it( "should parse for addresses ending in way" ) {
			
			// make sure the data sets have the same length
			expected.length should equal( posts.length )
			var i = 0
			for( item <- posts \ "description" ) {
				
				item text match {
					case addyRegex( addy ) => addy should equal( expected( i ) ) 
					case _ => fail
				}
				i += 1
			}
		}
	}
}