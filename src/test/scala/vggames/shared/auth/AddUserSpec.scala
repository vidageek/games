package vggames.shared.auth

import org.specs2.mutable.Specification
import java.util.Date
import org.specs2.mock.Mockito
import org.scribe.model.Token
import org.specs2.specification.Scope

class AddUserSpec extends Specification {
  "Execute and query" should {
    "adds a User to Users" in new ExecuteAndQueryContext {
      add executeAndQuery(users, new Date())
      users findBy(provider.name, "user-name") must_== Some(user)
    }
  }

}

trait ExecuteAndQueryContext extends Scope with Mockito {
  val user = User("user-name", mock[Token])

  val users = Users()

  val provider = mock[AuthProvider]
  provider.name returns "provider-name"

  val add = AddUser(provider, user)
}
