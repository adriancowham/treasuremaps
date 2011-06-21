package org.treasuremaps.acquisition

import scala.xml.Elem
import scala.xml.XML
import java.io.File
import java.io.FileInputStream

/*
 * Test enabler: Loads from local filesystem given feed in feedAddress and returns @Elem
 * 
 */
class TestDataAcquirer(feedAddress: String) extends FeedAcquirer {

  def acquire(): Elem = {
    
    XML load (new FileInputStream(new File( feedAddress )))
    
  }

}