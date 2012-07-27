package vggames.shared.auth

import collection.mutable._
import scala.Serializable
import org.prevayler
import prevayler.{Prevayler, PrevaylerFactory}
import PrevaylerFactory._

object Users {
  def apply(resourceBase: String) = new Users(resourceBase)
}

class Users(resourceBase: String) extends Serializable {
  private val users = Map[String, Set[User]]()
  private val prevayler:Prevayler = createPrevayler(this, "%s/users-repo".format(resourceBase))

  def +=(providerUser:(AuthProvider, User)) = prevayler.execute(new AddUser(providerUser._1, providerUser._2))

  def +=(providerUser:(String, User)) = {
    val usersByProvider = users.getOrElse(providerUser._1, Set[User]())
    usersByProvider += providerUser._2
    users += providerUser._1 -> usersByProvider
  }

  def findBy(providerName:String, name: String): Option[User] = {
    val prevalencyUsers: Users = prevayler.prevalentSystem().asInstanceOf[Users]
    prevalencyUsers.users.getOrElse(providerName, Set.empty).find {
      user => user match { case User(found, _) => name == found }
    }
  }

  def clear = users.clear
}