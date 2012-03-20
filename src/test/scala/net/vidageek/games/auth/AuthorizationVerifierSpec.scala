package net.vidageek.games.auth

import org.specs2.mutable._

class AuthorizationVerifierSpec extends Specification {
  
  "Authorization Verifier" should {
    "authorize with valid verifier" in {
      AuthorizationVerifier("validVerifier").authorized must beTrue
    }
    
    "not authorize" in {
      AuthorizationVerifier(null).authorized must beFalse
    }
  }
}
