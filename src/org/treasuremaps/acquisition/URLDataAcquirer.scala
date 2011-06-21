package org.treasuremaps.acquisition

import scala.xml.Elem
import scala.xml.XML
import java.net.URL

class URLDataAcquirer extends FeedAcquirer {

  def acquire(): Elem = { XML load (new URL("http://sacramento.craigslist.org/gms/index.rss").openConnection.getInputStream)}

}