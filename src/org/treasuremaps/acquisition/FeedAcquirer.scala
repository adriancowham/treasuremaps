package org.treasuremaps.acquisition
import scala.xml.Elem
/*
 * Basic trait to allow one interface for all data providers (file, URL, whatevs)
 */
trait FeedAcquirer{
	def acquire() : Elem
}