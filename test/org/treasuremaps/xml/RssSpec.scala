package org.treasuremaps.xml


import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import scala.util.matching.Regex
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

import scala.xml.XML

/*			
<item rdf:about="http://sacramento.craigslist.org/gms/2339995422.html"> 
	<title><![CDATA[GARAGE SALE SAT APR 23 7AM - NOON (North Natomas)]]></title> 
	<link>http://sacramento.craigslist.org/gms/2339995422.html</link> 
	<description>
		<![CDATA[1818 Irongate Way
		<br> 
		Sacramento CA 95835
		<br><br> 
		Huge garage sale...We have clothes, quality handbags, kitchen items, toys and miscellaneous items. Everything must go!!  
		<!-- START CLTAGS --> 
		<br><br>
		<ul class="blurbs"> 
			<li> <!-- CLTAG GeographicArea=North Natomas -->Location: North Natomas
			<li> <!-- CLTAG outsideContactOK=on -->it's ok to contact this poster with services or other commercial interests
		</ul> 
		<!-- END CLTAGS -->]]>
	</description> 
	<dc:date>2011-04-21T23:42:18-07:00</dc:date> 
	<dc:language>en-us</dc:language> 
	<dc:rights>Copyright &#x26;copy; 2011 craigslist, inc.</dc:rights> 
	<dc:source>http://sacramento.craigslist.org/gms/2339995422.html</dc:source> 
	<dc:title><![CDATA[GARAGE SALE SAT APR 23 7AM - NOON (North Natomas)]]></dc:title> 
	<dc:type>text</dc:type> 
	<dcterms:issued>2011-04-21T23:42:18-07:00</dcterms:issued> 
</item>
*/	
@RunWith(classOf[JUnitRunner])
class RssSpec extends Spec with ShouldMatchers    {

	describe( "RSS Reader" ) {
		
		val localRss = "data/index.rss"
		val feed = XML loadFile( localRss )
		val firstItem = (feed \ "item") head
			
		it( "should open an XML file" ) {			
			feed should not equal( null )
			feed should not be ( 'empty )
			feed should be ( 'nonEmpty )
		}
		
		it( "should get the title of the post" ) {
			// for some reason the parser was returning more than one title string
			// so I added the head function for parsing the title node and the tests
			// now pass
			
			// item -> title 
			( ( firstItem \ "title" ).head ).text should equal( "GARAGE SALE SAT APR 23 7AM - NOON (North Natomas)" )					
		}
		
		it( "should get the link of the post" ) {
			
			// item	 -> link			
			( firstItem \ "link" 	).text should equal( "http://sacramento.craigslist.org/gms/2339995422.html" );
		}	
		
		it( "should get the description of the post" ) {

			// item -> description 
			( firstItem \ "description" ).text should not be('empty)
		}		
		
		it( "should parse for addresses ending in Way" ) {
			
			val desc = (firstItem \ "description") text
			val AddyRegex = new Regex( """(?s).*(\d{4} .* [Ww]ay).*""" );
			desc match {
				case AddyRegex( addy ) => addy should equal( "1818 Irongate Way" ) 
				case _ => fail
			}
		}
		
		it( "shoud get the location of the treasure" ) {
			
			// item -> description -> ul ( with class=blurbs ) -> li.head ( with text.contains "Location:" )
			val desc = (firstItem \ "description") text
			val LocRegex = new Regex( """(?s).*Location: (.*)\s<li>.*""" )
			desc match {	
				case LocRegex( loc ) => loc should equal( "North Natomas" )
				case _ => fail
			}
		}
    }
}