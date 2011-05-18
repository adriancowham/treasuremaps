package org.treasuremaps.application

import java.util._
import java.text.SimpleDateFormat

import java.io.FileWriter

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
		
		// generate a base filename with today's date
		val today = Calendar.getInstance.getTime
		val suffix = new SimpleDateFormat( "yyyy_MM_dd" ).format( today )
		
		// for every "post" in the feed, try to match its description against a
		// regular expression
		var matched = false
		for( post <- posts \ "description" ) {
			// TODO: create filenames based on date/time for the current day and the type of 
			// street qualifier
			post text match {
				case AddressRegex.FullyQualifiedWay		( addy ) => matched = appendToFile( post text, generateFilename( "ways-", suffix ) )
				case AddressRegex.FullyQualifiedStreet	( addy ) => matched = appendToFile( post text, generateFilename( "streets-", suffix ) )
				case AddressRegex.FullyQualifiedCourt	( addy ) => matched = appendToFile( post text, generateFilename( "courts-", suffix ) )
				case AddressRegex.FullyQualifiedAvenue	( addy ) => matched = appendToFile( post text, generateFilename( "avenues-", suffix ) )
				case AddressRegex.FullyQualifiedPlace	( addy ) => matched = appendToFile( post text, generateFilename( "places-", suffix ) )
				case AddressRegex.FullyQualifiedLane	( addy ) => matched = appendToFile( post text, generateFilename( "lanes-", suffix ) )
				case AddressRegex.FullyQualifiedCircle	( addy ) => matched = appendToFile( post text, generateFilename( "circles-", suffix ) )
				case AddressRegex.FullyQualifiedRoad	( addy ) => matched = appendToFile( post text, generateFilename( "roads-", suffix ) )
				case _ => appendToFile( post text, generateFilename( "unidentifiables-", suffix ) )
			}
		}
		// Workaround for a defect in the scala compiler, need to break-up the pattern
		// matching
		if( !matched ) {
			for( post <- posts \ "description" ) {
				post text match {
					case AddressRegex.FullyQualifiedDrive	( addy ) => appendToFile( post text, generateFilename( "drives-", suffix ) )
					case _ => appendToFile( post text, generateFilename( "unidentifiables-", suffix ) )					
				}
			}
		}
	}
	
	def generateFilename( prefix :String, suffix :String ) :String= {
		return "data/analytics/" + prefix + suffix + ".xml"
	}
	
	// TODO: Convert to scala speak when possible
	def appendToFile( text :String, filename :String ) :Boolean = {
		val writer = new FileWriter( filename, true )
		writer.write( text )
		writer.close()
		return true;
	}
}