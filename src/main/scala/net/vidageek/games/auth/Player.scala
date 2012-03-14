package net.vidageek.games.auth
import org.scribe.model.Verifier
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.SessionScoped
import org.scribe.model.Token

@Component @SessionScoped
class Player {
  // TODO: Melhorar isso, da erro de compilação esse negocio!
  var accessToken: Token = null
  var provider: AuthProvider = null
  
  def authorize(authorization: AuthorizationVerifier) {
    accessToken = provider.accessToken(authorization.verifier)
  } 
  
  def getAutorized =  {
    accessToken != null
  }
  
  def getUserName = provider.userName(accessToken)
}