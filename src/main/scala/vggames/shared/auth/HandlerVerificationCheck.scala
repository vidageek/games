package vggames.shared.auth

trait HandlerVerificationCheck {

  def ok(a: AuthProvider)

  def fail
}
