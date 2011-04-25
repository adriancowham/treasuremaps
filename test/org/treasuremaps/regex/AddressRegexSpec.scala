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

	describe( "Addresses with Way regular expressions" ) {
		
		val expected = Source.fromFile( "data/addresses/addresses_with_way_expected.txt" ).getLines.toArray
				
		val addyRegex = new Regex( """(?s).*(\b\d.*[Ww][Aa][Yy]).*""" );
		val feed = XML loadFile( "data/addresses/addresses_with_way.xml" );
		val posts = feed \ "item"
		
		it( "should parse for addresses on a Way" ) {
			
			// make sure the data sets have the same length
			expected.length should equal( posts.length )
			var i = 0
			for( item <- posts \ "description" ) {
				
				item text match {
					case addyRegex( addy ) => {
						println( addy )
						addy should equal( expected( i ) ) 
					}
					case _ => fail
				}
				i += 1
			}
		}
	}
	
	describe( "Addresses with Drive regular expressions" ) {
				
		val expected = Source.fromFile( "data/addresses/addresses_with_drive_expected.txt" ).getLines.toArray

		val addyRegex = new Regex( """(?s).*(\d{3} .* [Dd][Rr][Ii][Vv][Ee]).*""" );
		val feed = XML loadFile( "data/addresses/addresses_with_drive.xml" );
		val posts = feed \ "item"
		
		it( "should parse for addresses on a Drive" ) {
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
	
	describe( "Addresses with Lane regular expressions" ) {
				
		val expected = Source.fromFile( "data/addresses/addresses_with_lane_expected.txt" ).getLines.toArray

		val addyRegex = new Regex( """(?s).*(\d{3} .* [Ll][Aa][Nn][Ee]).*""" );
		val feed = XML loadFile( "data/addresses/addresses_with_lane.xml" );
		val posts = feed \ "item"
		
		it( "should parse for addresses on a Lane" ) {
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