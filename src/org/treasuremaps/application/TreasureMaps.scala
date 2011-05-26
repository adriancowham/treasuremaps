package org.treasuremaps.application

import java.util._
import java.text.SimpleDateFormat
import java.io.FileWriter
import scala.collection.mutable.HashSet
import scala.xml.XML
import scala.xml.Node
import scala.xml.PrettyPrinter
import org.treasuremaps.model.tables.StreetNames
import org.treasuremaps.rss.Rss
import org.treasuremaps.regex.AddressRegex
import scala.util.matching.Regex

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
				
		val visited = new HashSet[Node]()

		// for every "post" in the feed, try to match its description against a
		// regular expression
		for( post <- posts \ "description" ) {
			// TODO: create filenames based on date/time for the current day and the type of 
			// street qualifier
			post text match {
				case AddressRegex.FullyQualifiedWay( addy ) => { 
						appendToFile( post, generateFilename( "ways" ) )
						visited += post
					}
				case AddressRegex.FullyQualifiedStreet( addy ) => { 
						appendToFile( post, generateFilename( "streets" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedCourt( addy ) => { 
						appendToFile( post, generateFilename( "courts" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedAvenue( addy ) => { 
						appendToFile( post, generateFilename( "avenues" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedPlace( addy ) => { 
						appendToFile( post, generateFilename( "places" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedLane( addy ) => { 
						appendToFile( post, generateFilename( "lanes" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedCircle( addy ) => { 
						appendToFile( post, generateFilename( "circles" ) ) 
						visited += post
					}
				case AddressRegex.FullyQualifiedRoad( addy ) => { 
						appendToFile( post, generateFilename( "roads" ) ) 
						visited += post
					}
				case _ => 
			}
		}
		// Workaround for a defect in the scala compiler, need to break-up the pattern
		// matching
		for( post <- posts \ "description" ) {
			post text match {
				case AddressRegex.FullyQualifiedDrive( addy ) => {
					appendToFile( post, generateFilename( "drives" ) )
						visited += post
				}
				case _ => {
					if( !visited( post ) ) {
						appendToFile( post, generateFilename( "unidentifiables" ) )
						visited += post
					}
				}
			}
		}		
	}
	
	def generateFilename( prefix :String, suffix :String = "" ) :String = {
		return "data/analytics/" + prefix + suffix + ".xml"
	}
	
	// TODO: Convert to scala speak when possible
	def appendToFile( node :Node, filename :String ) = {
		val builder = new StringBuilder()
		val printer = new PrettyPrinter( 100, 5 )
		printer.format( node, builder )
		val writer = new FileWriter( filename, true )
		writer.write( builder.toString )
		writer.close()
	}
}