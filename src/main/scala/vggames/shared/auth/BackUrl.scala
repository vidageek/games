package vggames.shared.auth

import br.com.caelum.vraptor.ioc.{SessionScoped, Component}

@Component
@SessionScoped
class BackUrl {
  var value:String = _ 
}