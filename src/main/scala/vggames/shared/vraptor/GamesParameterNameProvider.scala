package vggames.shared.vraptor

import br.com.caelum.vraptor.http.DefaultParameterNameProvider
import br.com.caelum.vraptor.interceptor.TypeNameExtractor
import br.com.caelum.vraptor.ioc.Component
import java.lang.reflect.AccessibleObject
import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.http.ParanamerNameProvider

@Component
@ApplicationScoped
class GamesParameterNameProvider extends ParanamerNameProvider {

  override def parameterNamesFor(method : AccessibleObject) : Array[String] = {
    super.parameterNamesFor(method).map(_.replaceAll("\\$.*$", ""))
  }

}