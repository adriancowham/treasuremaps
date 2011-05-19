package org.treasuremaps.regex
import scala.util.matching.Regex

/**
 * Object used to hold regular expressions
 *
 */
object AddressRegex {

	val FullyQualifiedStreet = 	new Regex( """(?s).*((?:^\d+|\s\d+|\b\d+) .{1,15} (?:[Ss][Tt][Rr][Ee]+[Tt]|[Ss][Tt]))\W.*""" )
	val FullyQualifiedWay = 	new Regex( """(?s).*((?:^\d+|\s\d+|\b\d+).{1,15} [Ww][Aa][Yy]).*""" )
	val FullyQualifiedCourt = 	new Regex( """(?s).*(\b\d+ .{1,15} (?:[Cc][Oo][Uu][Rr][Tt]|[Cc][Tt])).*""" )
	val FullyQualifiedAvenue = 	new Regex( """(?s).*((?:\b\d+) .{1,15} (?:[Aa][Vv][Ee][Nn][Uu][Ee]|[Aa][Vv][Ee])).*""" )
	val FullyQualifiedPlace =	new Regex( """(?s).*((?:^\d+|\s\d+|\b\d+) .{1,15} (?:[Pp][Ll][Aa][Cc][Ee]|[Pp][Ll])).*""" )
	val FullyQualifiedLane = 	new Regex( """(?s).*(\b\d+ .{1,15} (?:[Ll][Aa][Nn][Ee]|[Ll][Nn])).*""" )
	val FullyQualifiedCircle = 	new Regex( """(?s).*((?:^\d+|\s\d+) .{1,15} (?:[Cc][Ii][Rr][Cc][Ll][Ee]|[Cc][Ii][Rr])).*""" )
	val FullyQualifiedRoad = 	new Regex( """(?s).*((?:^\d+|\s\d+|\b\d+) .{1,15} (?:[Rr][Oo][Aa][Dd]|[Rr][Dd])).*""" )
	val FullyQualifiedDrive = 	new Regex( """(?s).*((?:^\d+|\s\d+|\b\d+) .{1,15}(?:[Dd][Rr][Ii][Vv][Ee]|[Dd][Rr])).*""" )
}