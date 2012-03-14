package net.vidageek.games.auth
import org.scribe.model.Verifier
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.SessionScoped
import org.scribe.model.Token

@Component @SessionScoped
class Player {
  // TODO: Melhorar isso, da erro de compilação esse negocio!
  var provider: AuthProvider = null
  
  def authorize(authorization: AuthorizationVerifier) {
    provider.accessToken(authorization.verifier)
  } 
  
  def getAutorized =  {
    provider != null
  }
  
  def getUserName = provider.userName
  
  def logout =  {
    if (getAutorized) {
    	provider.logout
    	provider = null
    }
  }
}