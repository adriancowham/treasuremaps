package org.treasuremaps.model.tables

import org.scalaquery.ql._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.basic.BasicTable


object StreetNames extends BasicTable[ ( Int, String, String, Int) ]( "street_names" ) {

	val TableName = "street_names"
		
	def id = column[ Int ]( "id", O NotNull )
	def street_type = column[ String ]( "street_type" )
	def street_name = column[ String ]( "street_name" )
	def street_number = column[ Int ]( "street_number" )
	def * = id ~ street_type ~ street_name ~ street_number
}