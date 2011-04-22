package org.treasuremaps.xml

import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

import scala.xml.XML

/*			
<item rdf:about="http://sfbay.craigslist.org/eby/gms/2335866614.html">
	<title><![CDATA[BUICK TIRES FOR SALE (OAKLAND) $150]]></title>
	<link>http://sfbay.craigslist.org/eby/gms/2335866614.html</link>
	<description><![CDATA[HELLO WE HAVE 4 BLACK BUICK TIRES FOR SALE AS IS ASKING $150 OBO IN EXCELLENT CONDITION SO IF U WANT THEM COME BY THEM ASAP WE TRYING TO MOVE SO PLEASE HELP US OUT CALL 510 575-7402 OR 510-213-3277 CALL CALL THANK U GOD BLESS<!-- START CLTAGS -->
	<br><br><ul class="blurbs">
	<li> <!-- CLTAG GeographicArea=OAKLAND -->Location: OAKLAND
	<li>it's NOT ok to contact this poster with services or other commercial interests</ul>
	<!-- END CLTAGS -->]]></description>
	<dc:date>2011-04-19T20:58:43-07:00</dc:date>
	<dc:language>en-us</dc:language>
	<dc:rights>Copyright &#x26;copy; 2011 craigslist, inc.</dc:rights>
	<dc:source>http://sfbay.craigslist.org/eby/gms/2335866614.html</dc:source>
	<dc:title><![CDATA[BUICK TIRES FOR SALE (OAKLAND) $150]]></dc:title>
	<dc:type>text</dc:type>
	<dcterms:issued>2011-04-19T20:58:43-07:00</dcterms:issued>
</item>
*/	
@RunWith(classOf[JUnitRunner])
class RssSpec extends Spec with ShouldMatchers    {

	describe( "RSS Reader" ) {
		
		val localRss = "data/index.rss"
		val feed = XML.loadFile( localRss )
		val firstItem = (feed \ "item").head
			
		it( "should open an XML file" ) {			
			feed should not equal( null )
			feed should not be ( 'empty )
			feed should be ( 'nonEmpty )
		}
		
		it( "should get the title of the post" ) {
			// for some reason the parser was returning more than one title string
			// so I added the head function for parsing the title node and the tests
			// now pass
			( ( firstItem \ "title" ).head ).text should equal( "GARAGE SALE SAT APR 23 7AM - NOON (North Natomas)" )					
		}
		
		it( "should get the link of the post" ) {
			( firstItem \ "link" ).text should equal( "http://sacramento.craigslist.org/gms/2339995422.html" );
		}	
		
		it( "should get the description of the post" ) {
			( firstItem \ "description" ).text should not be('empty)
		}			
    }
}