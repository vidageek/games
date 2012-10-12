package vggames.shared.vraptor

import java.util.concurrent.ConcurrentHashMap
import br.com.caelum.vraptor.ioc.{ ComponentFactory, Component, ApplicationScoped }
import vggames.shared.task.Descriptions
import scala.collection.JavaConversions

@Component
class DescriptionsFactory(data : RequestData, cache : DescriptionsCache) extends ComponentFactory[Descriptions] {

  override def getInstance : Descriptions = cache.get(data.game)
}

@Component
@ApplicationScoped
class DescriptionsCache {

  private val map = JavaConversions.asScalaConcurrentMap(new ConcurrentHashMap[String, Descriptions])

  def put(key : String, desc : Descriptions) = {
    map.putIfAbsent(key, desc)
    get(key)
  }

  def get(game : String) : Descriptions = map.get(game).getOrElse {
    val desc = new Descriptions(game)
    put(game, desc)
  }
}