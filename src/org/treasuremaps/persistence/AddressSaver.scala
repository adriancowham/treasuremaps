package org.treasuremaps.persistence
import scala.xml.PrettyPrinter
import java.text.SimpleDateFormat
import java.io.FileWriter
import scala.collection.mutable.MultiMap
import scala.xml.Node
import scala.collection.mutable.{ Set => MSet, HashMap, MultiMap }
import java.io.File


import java.util.Calendar
class AddressSaver {
  val DATE_FORMAT = "yyyyMMddHHmmss"
    
  def persistAddys(data: MultiMap[String, Node], region: String):List[File] = {
    /* TODO.. this is just java with scala syntax..
     * Refactor as idiomatic
     * 
     */
    var files =   List[File]()
    
    val timeStamp = getTimeStamp()
    
    data.foreach { nodes: (String, MSet[Node]) =>
      val (name, node) = nodes
      val fileName = generateFilename( region,name,timeStamp)
      node.foreach { nodeSet: (Node) =>
        val (chunk) = nodeSet
        appendToFile(chunk, fileName)
      }
      files = files ::: List( new File(fileName) )
    }
    files

  }
  def generateFilename(region: String, suffix: String = "",
      timeStamp: String = new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime())): String = {

    "data/analytics/" + region + "-"  + suffix + "-" + timeStamp+ ".xml"

  }
  
  def getTimeStamp(): String = {
    
    new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
    
  }

  def appendToFile(node: Node, filename: String):Unit = {
    println("Writing file: " + filename)
    val builder = new StringBuilder()
    val printer = new PrettyPrinter(100, 5)
    printer.format(node, builder)
    val writer = new FileWriter(filename, true)
    writer.write(builder.toString)
    writer.close()
  }

}