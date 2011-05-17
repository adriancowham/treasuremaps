package org.treasuremaps.rss

import scala.xml._
import java.net._

/**
 * Class used to retrieve an RSS feed from a remote location
 *
 */
class Rss {

	/**
	 * Retrieves the feed
	 */
	def getFeed( location :String ) = {
		
		// first get the input stream of the remote RSS feed
		val url = new URL( location ).openConnection.getInputStream
		
		// now load the stream and return an XML DOM
		XML load( url )
	}
}