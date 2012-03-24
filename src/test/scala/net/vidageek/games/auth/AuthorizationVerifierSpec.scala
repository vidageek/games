package net.vidageek.games.auth

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class AuthorizationVerifierSpec extends Specification {

  "The authorized" should {
    "authorize with valid verifier" in {
      AuthorizationVerifier("validVerifier").authorized must beTrue
    }

    "not authorize" in {
      AuthorizationVerifier(null).authorized must beFalse
    }
  }
}
