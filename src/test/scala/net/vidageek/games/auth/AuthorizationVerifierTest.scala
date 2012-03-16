package net.vidageek.games.auth

import org.junit.Test
import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AuthorizationVerifierTest extends SpecificationWithJUnit{
  
  "Authorization Verifier" should {
    "authorize with valid verifier" in {
      AuthorizationVerifier("validVerifier").authorized must beTrue
    }
    "Not authorize" in {
      AuthorizationVerifier(null).authorized must beFalse
    }
  }

}