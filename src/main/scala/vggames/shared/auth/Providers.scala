package vggames.shared.auth

import scala.collection.immutable.Map
import twitter.TwitterAuthProvider
import br.com.caelum.vraptor.ioc.{ApplicationScoped, Component}
import net.vidageek.mirror.dsl.Mirror

@Component @ApplicationScoped
class Providers {
  val providers = Map("twitter" -> classOf[TwitterAuthProvider])

  def quantity = providers.size

  def apply(name: String, a: AuthenticatedRequester): AuthProvider = new Mirror()
                                                        .on(providers(name))
                                                        .reflect().constructor()
                                                        .withArgs(classOf[AuthenticatedRequester])
                                                        .newInstance(a)
}
