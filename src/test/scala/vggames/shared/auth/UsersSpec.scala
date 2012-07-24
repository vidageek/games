package vggames.shared.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.scribe.model.{Verifier, Token}
import org.specs2.specification.Scope
import org.prevayler.{Prevayler, PrevaylerFactory}
import twitter.TwitterAuthProvider

class UsersSpec extends Specification with Mockito {
  "Find by" should {
    "returns None when there isnt uses" in new FindAByContext {
      users findBy("other-provider-name", "user-name") must_== None
    }

    "found a user by provider and user name" in new FindAByContext {
      users += "provider-name" -> user
      users findBy("provider-name", "user-name") must_== Some(user)
    }

    "found a user when there are more than one at same provider" in new FindAByContext {
      val otherUser = User("other-user-name", mock[Token])
      users += "provider-name" -> user
      users += "provider-name" -> otherUser
      users findBy("provider-name", "user-name") must_== Some(user)
    }

    "found a user when there are more than one at diferents provider" in new FindAByContext {
      val otherUser = User("user-name", mock[Token])
      users += "provider-name" -> user
      users += "other-provider-name" -> otherUser
      users findBy("other-provider-name", "user-name") must_== Some(otherUser)
    }

    "returns None when search by absent user" in new FindAByContext {
      val otherUser = User("other-user-name", mock[Token])
      users += "provider-name" -> user
      users += "other-provider-name" -> otherUser
      users findBy("other-provider-name", "user-name") must_== None
    }
  }

  "prevayler" should {
    "persist" in new FindAByContext {
      val prevalencyUsers: Prevayler = PrevaylerFactory.createPrevayler(users, "/tmp/users")
      val provider: AuthProvider = new DummyAuthProvider
      prevalencyUsers.execute(new AddUser(provider, user))
      prevalencyUsers.takeSnapshot();
    }
  }
}

trait FindAByContext extends Scope with Mockito {
  val users = Users()
  val user = User("user-name", new Token("aToken", "aSecret"))
}

class DummyAuthProvider extends AuthProvider {
  def applicationAuthorizationUrl = null

  def name = null

  def accessToken(verifier: Verifier) {}

  def userName = null

  def logout {}
}
