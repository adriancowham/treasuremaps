package org.treasuremaps.acquisition

import scala.xml.Elem
import scala.xml.XML
import java.net.URL
/*
 * "Production" data acquirer... given the injected url, get it and return as @Elem
 * 
 */
class URLDataAcquirer(url: String) extends FeedAcquirer {

  def acquire(): Elem = { XML load (new URL( url ).openConnection.getInputStream)}

}