package vggames.regex

import java.util.concurrent.ConcurrentHashMap
import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import java.util.Scanner

@Component
@ApplicationScoped
class Descriptions {
  val descriptions = new ConcurrentHashMap[String, String]

  def forGroup(groupName: String): String = {
    var description = descriptions.get(groupName)
    if (description != null) {
      return description
    }
    val stream = classOf[Descriptions].getResourceAsStream("/desc/" + groupName + ".html")
    if (stream == null) {
      description = "No description for group " + groupName
    } else {
      description = new Scanner(stream).useDelimiter("$$").next
    }
    descriptions.put(groupName, description)
    description
  }
}