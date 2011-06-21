package org.treasuremaps.acquisition
import scala.xml.Elem

trait FeedAcquirer{
	def acquire() : Elem
}