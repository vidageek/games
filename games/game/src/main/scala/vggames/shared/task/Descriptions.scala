package vggames.shared.task

import java.util.Scanner
import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConverters._

class Descriptions(game: String) {

  private val descriptions = new ConcurrentHashMap[String, String]().asScala

  def forGroup(groupName: String): String = {
    descriptions.getOrElseUpdate(groupName,
      Option(classOf[Descriptions].getResourceAsStream("/desc/" + game + "/" + groupName + ".markdown")).
        map(new Scanner(_).useDelimiter("$$").next).map(Markdown(_, game)).getOrElse("No description for group " + groupName))
  }
}
