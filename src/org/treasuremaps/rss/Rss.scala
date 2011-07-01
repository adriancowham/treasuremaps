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
	def getFeed( location :String ) : Elem = XML load(  new URL( location ).openConnection.getInputStream )
}