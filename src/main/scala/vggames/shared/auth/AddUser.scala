package vggames.shared.auth

import org.prevayler.TransactionWithQuery
import java.util.Date

object AddUser {
  def apply(in: AuthProvider, the:User) = new AddUser(in, the)
}

class AddUser(in: AuthProvider, the:User) extends TransactionWithQuery {
  override def executeAndQuery(objUsers: Object, executionTime: Date):Object = {
    val users = objUsers.asInstanceOf[Users]
    users += in.name -> the
  }
}
