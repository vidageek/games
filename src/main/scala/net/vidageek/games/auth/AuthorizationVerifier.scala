package net.vidageek.games.auth
import org.scribe.model.Verifier

object AuthorizationVerifier {
  def apply(oAuthVerifier: String): AuthorizationVerifier = {
    new VerifiedResponse(new Verifier(oAuthVerifier))
  }
}

trait AuthorizationVerifier {
  def authorized: Boolean
  
  def verifier: Verifier
}

class VerifiedResponse(verifierResponse: Verifier) extends AuthorizationVerifier{
  
  override def authorized = true
  
  def verifier: Verifier = verifierResponse
  
}