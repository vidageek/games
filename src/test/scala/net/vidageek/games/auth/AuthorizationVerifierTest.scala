package net.vidageek.games.auth

import org.specs2.mutable._

class AuthorizationVerifierTest extends Specification {
  
  "Authorization Verifier" should {
    "authorize with valid verifier" in {
      AuthorizationVerifier("validVerifier").authorized must beTrue
    }
    "Not authorize" in {
      AuthorizationVerifier(null).authorized must beFalse
    }
  }

}