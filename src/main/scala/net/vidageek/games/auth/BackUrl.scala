package net.vidageek.games.auth
import br.com.caelum.vraptor.ioc.SessionScoped
import br.com.caelum.vraptor.ioc.Component

@Component
@SessionScoped
class BackUrl {
  var value:String = _ 
}