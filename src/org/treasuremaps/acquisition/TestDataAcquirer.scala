package org.treasuremaps.acquisition

import scala.xml.Elem
import scala.xml.XML
import java.io.File
import java.io.FileInputStream
class TestDataAcquirer(feedAddress: String) extends FeedAcquirer {

  def acquire(): Elem = {
    
    XML load (new FileInputStream(new File( feedAddress )))
    
  }

}