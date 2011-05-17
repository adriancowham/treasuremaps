package org.treasuremaps.application

import java.util._
import java.text.SimpleDateFormat

import org.treasuremaps.rss.Rss
import org.treasuremaps.regex.AddressRegex

/**
 * Test application used to consume a CL RSS feed, parse it, and capture
 * unidentifiable addresses
 */
object TreasureMaps {

	/**
	 * Main
	 */
	def main( args :Array[ String ] ) {
		
		// get RSS feed from remote location
		val rss = new Rss().getFeed( "http://sacramento.craigslist.org/gms/index.rss" );
		// parse feed all the posts
		val posts = rss \ "item"
		
		// generate a base filename for today
		val today = Calendar.getInstance.getTime
		val dateFormat = new SimpleDateFormat( "yyyy_MM_dd_" )
		val destFile = "data/analytics/" + dateFormat.format( today )
		
		// for every "post" in the feed, try to match its description against a
		// regular expression		
		for( post <- posts \ "description" ) {
			// TODO: create filenames based on date/time for the current day and the type of 
			// street qualifier
			post text match {
				case AddressRegex.FullyQualifiedWay		( addy ) => appendToFile( post text, destFile + "_way.xml" )
				case AddressRegex.FullyQualifiedStreet	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedCourt	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedAvenue	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedPlace	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedLane	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedCircle	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedRoad	( addy ) => appendToFile( post text, "" )
				case AddressRegex.FullyQualifiedDrive	( addy ) => appendToFile( post text, "" )
				case _ => appendToFile( post text, "" )
			}
		}
	}
	
	def appendToFile( text :String, filename :String ) = {
		
	}
}