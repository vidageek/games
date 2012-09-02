package vggames.shared.auth

import org.scribe.model.Verifier

case class VerifyPlayerAuthorization(providerName: String, handler: HandlerVerificationCheck, all: Providers, a: AuthenticateWithProvider) {
  def check(oAuthVerifier: String) = oAuthVerifier match {
    case null => handler.fail
    case _ => handler.ok(
      all(
        providerName, a.requester(new Verifier(oAuthVerifier))
      )
    )
  }
}
