package vggames.shared.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.scribe.model.Token
import org.specs2.specification.{After, Scope}
import org.prevayler.{Transaction, Prevayler, PrevaylerFactory}
import java.util.Date

class UsersSpec extends Specification with Mockito with After {
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

  def after = new FindAByContext {
    val prevayler: Prevayler = PrevaylerFactory createPrevayler(users, "%s/users-repo".format("src/test/resources/prevayler"))
    prevayler.execute(new Transaction {
      def executeOn(prevalentSystem: Any, executionTime: Date) = prevalentSystem.asInstanceOf[Users].clear
    })
  }
}

trait FindAByContext extends Scope with Mockito {
  val users = Users("src/test/resources/prevayler")
  val user = User("user-name", new Token("aToken", "aSecret"))
}