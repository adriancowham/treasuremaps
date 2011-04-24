package org.treasuremaps.regex

import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import scala.util.matching.Regex
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import scala.xml.XML

@RunWith( classOf[ JUnitRunner ] )
class AddressRegexSpec extends Spec with ShouldMatchers {

	describe( "Address Regular Expressions" ) {
		
		it( "should parse for addresses ending in way" ) {
			val feed = XML loadFile( "data/addresses/ends_with_way.xml" )
			
			val desc = ( feed \ "description" ) text
			val AddyRegex = new Regex( """(?s).*(\d{4} .* [Ww]ay).*""" );
			desc match {
				case AddyRegex( addy ) => addy should equal( "1818 Irongate Way" ) 
				case _ => fail
			}			
		}
	}
			
}