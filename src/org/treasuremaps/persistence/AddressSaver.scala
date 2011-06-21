package org.treasuremaps.persistence
import scala.xml.PrettyPrinter
import java.text.SimpleDateFormat
import java.io.FileWriter
import scala.collection.mutable.MultiMap
import scala.xml.Node
import scala.collection.mutable.{ Set => MSet, HashMap, MultiMap }

class AddressSaver {

  def persistAddys(data: MultiMap[String, Node]) = {
    /* TODO.. this is just java with scala syntax..
     * Refactor as idiomatic
     * 
     */
    data.foreach { nodes: (String, MSet[Node]) =>
      val (name, node) = nodes
      node.foreach { nodeSet: (Node) =>
        val (chunk) = nodeSet
        appendToFile(chunk, generateFilename(name, name))
      }
    }

  }
  def generateFilename(prefix: String, suffix: String = ""): String = {

    "data/analytics/" + prefix + suffix + "-test.xml"

  }

  def appendToFile(node: Node, filename: String) = {
    println("Writing file: " + filename)
    val builder = new StringBuilder()
    val printer = new PrettyPrinter(100, 5)
    printer.format(node, builder)
    val writer = new FileWriter(filename, true)
    writer.write(builder.toString)
    writer.close()
  }

}