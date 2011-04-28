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

	def matchInDescription( 
			regex: 			String,
			actualFile: 	String,
			expectedFile:	String ): Boolean =	{
		
		// create the regular expression instance
		val addyRegex = new Regex( regex )
		// load the target XML file containing the posts
		val feed = XML loadFile( actualFile )
		// load the file with the expected values and convert to list
		var expectedValues = Source.fromFile( expectedFile ).getLines.toList
		// parse and get all the item elements from the XML file
		val posts = feed \ "item"			
		
		expectedValues.length should equal( posts.length )
		for( item <- posts \ "description" ) {
			item text match {
				case addyRegex( addy ) => {
					println( addy trim )
					addy.trim should equal( expectedValues.head ) 
				}
				case _ => fail
			}
			expectedValues = expectedValues tail
		}
		false
	}
		
	describe( "Regular expressions for addresses on a Way" ) {
		
		it( "should parse for addresses on a Way" ) {
			
			matchInDescription( 
					"""(?s).*((?:^\d+|\s\d+).{1,15}[Ww][Aa][Yy]).*""",
					"data/addresses/addresses_on_a_way.xml",
					"data/addresses/addresses_on_a_way_expected.txt" )
		}
	}
	
	describe( "Regular expressions for addresses on a Drive" ) {				
		
		it( "should parse for addresses on a Drive" ) {
			
			matchInDescription( 
					"""(?s).*(\b\d.*(?:[Dd][Rr][Ii][Vv][Ee]|[Dd][Rr]\.?)).*""",
					"data/addresses/addresses_on_a_drive.xml",
					"data/addresses/addresses_on_a_drive_expected.txt" )
		}
	}
	
	describe( "Regular expressions for addresses on a Lane" ) {				
		
		it( "should parse for addresses on a Lane" ) {
			
			matchInDescription(
					"""(?s).*(\s\d+ .* [Ll][Aa][Nn][Ee]).*""",
					"data/addresses/addresses_on_a_lane.xml",
					"data/addresses/addresses_on_a_lane_expected.txt" )
		}
	}	
	
	describe( "Regular expressions for addresses on a Court" ) {
		
		it( "should parse for addresses on a court" ) {
			
			matchInDescription( 
					"""(?s).*(\s\d+ .{1,15} (?:[Cc][Oo][Uu][Rr][Tt]|[Cc][Tt]\.?)).*""",
					"data/addresses/addresses_on_a_court.xml",
					"data/addresses/addresses_on_a_court_expected.txt")
		}
	}	
	
	describe( "Regular expressions for addresses on a Road" ) {
		
		it( "should parse for addresses on a Road" ) {
			
			matchInDescription(
					"""(?s).*((?:^\d+|\s\d+) .{1,15} (?:[Rr][Oa][Aa][Dd]|[Rr][Dd]\.?)).*""",
					"data/addresses/addresses_on_a_road.xml",
					"data/addresses/addresses_on_a_road_expected.txt" )
		}
	}	
	
	describe( "Regular expressions for addresses on a Circle" ) {
		
		it( "should parse for addresses on a circle" ) {
			
			matchInDescription(
					"""(?s).*((?:^\d+|\s\d+) .{1,15} (?:[Cc][Ii][Rr][Cc][Ll][Ee]|[Cc][Ii][Rr]\.?)).*""",
					"data/addresses/addresses_on_a_circle.xml",
					"data/addresses/addresses_on_a_circle_expected.txt" )
		}
	}	
	
	describe( "Regular expressions for addresses on an Avenue" ) {
		
		it( "should parse for addresses on a circle" ) {
			
			matchInDescription(
					"""(?s).*(\d{4} .* (?:[Aa][Vv][Ee][Nn][Uu][Ee]|[Aa][Vv][Ee]\.?)).*""",
					"data/addresses/addresses_on_an_avenue.xml",
					"data/addresses/addresses_on_an_avenue_expected.txt" )
		}
	}
	
	describe( "Regular expressions for addresses on a Place" ) {
		
		it( "should parse for addresses on a place" ) {
			
			matchInDescription(
					"""(?s).*(\d{4} .{1,15} (?:[Pp][Ll][Aa][Cc][Ee]|[Pp][Ll]\.?)).*""",
					"data/addresses/addresses_on_a_place.xml",
					"data/addresses/addresses_on_a_place_expected.txt" )
		}
	}	
	
	describe( "Regular expressions for addresses on a Street" ) {
		
		it( "should parse for addresses on a street" ) {
			
			matchInDescription(
					"""(?s).*(\d{4} .{1,15} (?:[Ss][Tt][Rr][Ee]+[Tt]|[Ss][Tt]\.?)).*""",
					"data/addresses/addresses_on_a_street.xml",
					"data/addresses/addresses_on_a_street_expected.txt" )
		}
	}	
}