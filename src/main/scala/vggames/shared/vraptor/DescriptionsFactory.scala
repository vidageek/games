package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.shared.Descriptions
import java.util.concurrent.ConcurrentHashMap
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ApplicationScoped

@Component
@ApplicationScoped
class DescriptionsFactory(data : RequestData) extends ComponentFactory[Descriptions] {

  private val map = new ConcurrentHashMap[String, Descriptions]

  override def getInstance : Descriptions = {
    Option(map.get(data.game)).getOrElse({
      val desc = new Descriptions(data.game)
      map.put(data.game, desc)
      desc
    })
  }

}