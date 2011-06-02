package org.treasuremaps.application

import java.io.FileWriter
import org.treasuremaps.regex.AddressRegex
import org.treasuremaps.rss.Rss
import scala.collection.mutable.HashSet
import scala.util.matching.Regex
import scala.xml.{PrettyPrinter, Node}
import scala.collection.mutable.HashMap

/**
 * Test application used to consume a CL RSS feed, parse it, and capture
 * unidentifiable addresses
 */
object TreasureMaps {

	// doesn't seem Scala-ish, refactor
	def saveData( parsedData: HashMap[String, List[Node]], 
	    visited: HashSet[Node],
	    streetType: String, 
	    data: Node ) = {
	  
	  val temp = parsedData.getOrElse( streetType, Nil )
	  parsedData += streetType -> ( data :: temp)
	  
	  visited += data
	}
	
	/**
	 * Main
	 */
	def main( args :Array[ String ] ) {
		// get RSS feed from remote location
		val rss = new Rss().getFeed( "http://sacramento.craigslist.org/gms/index.rss" );
		// parse feed all the posts
		val posts = rss \ "item"
				
		val visited = new HashSet[Node]()
		val parsedData = new HashMap[String, List[Node]]
		
		// for every "post" in the feed, try to match its description against a
		// regular expression
		for( post <- posts \ "description" ) {
			post text match {
				case AddressRegex.FullyQualifiedWay( addy ) => {						
						saveData( parsedData, visited, "ways", post )
					}
				case AddressRegex.FullyQualifiedStreet( addy ) => { 
						saveData( parsedData, visited, "streets", post )
					}
				case AddressRegex.FullyQualifiedCourt( addy ) => { 
						saveData( parsedData, visited, "courts", post )
					}
				case AddressRegex.FullyQualifiedAvenue( addy ) => { 
						saveData( parsedData, visited, "avenues", post )
					}
				case AddressRegex.FullyQualifiedPlace( addy ) => { 
						saveData( parsedData, visited, "places", post )
					}
				case AddressRegex.FullyQualifiedLane( addy ) => { 
						saveData( parsedData, visited, "lanes", post )
					}
				case AddressRegex.FullyQualifiedCircle( addy ) => { 
						saveData( parsedData, visited, "circles", post )
					}
				case AddressRegex.FullyQualifiedRoad( addy ) => { 
						saveData( parsedData, visited, "roads", post )
					}
				case _ => 
			}
		}
		// Workaround for a defect in the scala compiler, need to break-up the pattern
		// matching
		for( post <- posts \ "description" ) {
			post text match {
				case AddressRegex.FullyQualifiedDrive( addy ) => {
						saveData( parsedData, visited, "drives", post )
				}
				case _ => {
					if( !visited( post ) ) {
						saveData( parsedData, visited, "unidentifiables", post )
					}
				}
			}
		}
		
		// for each entry in parsedData write to disk
		
	}
	
	def writeToFile( filePrefix: String, data: List[Node] ) {
	  val filename = generateFilename( filePrefix )
	  // load xml file
	  
	  // get nodes
	  
	  // concat with data passed in
	  
	  // write to file
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