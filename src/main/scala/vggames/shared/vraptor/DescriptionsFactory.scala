package vggames.shared.vraptor

import java.util.concurrent.ConcurrentHashMap

import br.com.caelum.vraptor.ioc.{ComponentFactory, Component, ApplicationScoped}
import vggames.shared.task.Descriptions

@Component
class DescriptionsFactory(data : RequestData, cache : DescriptionsCache) extends ComponentFactory[Descriptions] {

  override def getInstance : Descriptions = {
    Option(cache.get(data.game)).getOrElse({
      val desc = new Descriptions(data.game)
      cache.put(data.game, desc)
      desc
    })
  }
}

@Component
@ApplicationScoped
class DescriptionsCache {

  private val map = new ConcurrentHashMap[String, Descriptions]

  def get(key : String) = map.get(key)

  def put(key : String, desc : Descriptions) = map.put(key, desc)
}