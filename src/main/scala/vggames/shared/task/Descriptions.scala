package vggames.shared.task

import java.util.concurrent.ConcurrentHashMap
import java.util.Scanner

class Descriptions(game : String) {

  private val descriptions = new ConcurrentHashMap[String, String]

  def forGroup(groupName : String) : String = {
    var description = descriptions.get(groupName)
    if (description != null) {
      return description
    }
    val stream = classOf[Descriptions].getResourceAsStream("/desc/" + game + "/" + groupName + ".html")
    if (stream == null) {
      description = "No description for group " + groupName
    } else {
      description = new Scanner(stream).useDelimiter("$$").next
    }
    descriptions.put(groupName, description)
    description
  }
}