package org.treasuremaps.application
import java.util._
import java.text.SimpleDateFormat
import java.io.FileWriter
import scala.collection.mutable.HashSet
import scala.xml.Elem
import scala.xml.Node
import scala.xml.PrettyPrinter
import org.treasuremaps.rss.Rss
import org.treasuremaps.regex.AddressRegex
import scala.util.matching.Regex
import scala.collection.immutable.Map
import scala.collection.immutable.List
import scala.collection.mutable.MultiMap
//Ahh.. Scala... u r00l
import scala.collection.mutable.{ Set => MSet, HashMap, MultiMap }
import scala.collection.mutable.{ Set => MSet, HashMap, MultiMap }
import org.treasuremaps.acquisition.FeedAcquirer

class TreasureCollector {
  /* collectTreasure(...)
   *  Given the data in Elem, use the parsers in cats2parsers, and build a map
   *  of category -> set with each group of matches 
   * 
   */
  def collectTreasure(acquirer : FeedAcquirer, cats2parsers: Map[String, Regex]): MultiMap[String, Node] = {

    println("collecting")

    // parse feed all the posts
    val posts = acquirer.acquire \ "item"
     //setup result, using map with MultiMap mixin to provide 1->many mapping
    val result: MultiMap[String, Node] = new HashMap[String, MSet[Node]] with MultiMap[String, Node]
    
    //Walk parsed posts...
    for (post <- posts \ "description") {
      //give it parser a shot at classification
      cats2parsers.foreach { parsers: (String, Regex) =>
        val (name, parser) = parsers
        post text match {
          case parser(addy) => {
            println("The addy: " + addy + " is qualified as a: " + name)
            result.add(name, post)
          }
          case _ => {
            println("The post is qualified as unidentifiable " + post  )
            result.add("unidentifiables", post)
          }
        }
      }
    }
    result
  }
  def computeStats(things2sum: MultiMap[String, Node] ): Map[String, Int] = {
    val result: scala.collection.immutable.Map[String,Int] = new  scala.collection.immutable.HashMap[String,Int]
    
    things2sum.foreach{ category: (String, MSet[Node]) =>
      var(name, nodes) = category
      //result.add(name,1)
      println(name + " count: " + nodes.size)
    }
    return result  
  }
}