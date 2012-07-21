package vggames.shared.auth

import collection.mutable._
import collection.mutable

object UserList {
  def apply() = new UserList
}

class UserList {
  private val users = Map[String, Set[User]]()
  def +=(providerUser:(String, User)) = {
    val usersByProvider = users.getOrElse(providerUser._1, Set[User]())
    usersByProvider += providerUser._2
    users += providerUser._1 -> usersByProvider
  }

  def findBy(providerName:String, name: String): Option[User] = {
    users.getOrElse(providerName, mutable.Set.empty).find {
      user => user match { case User(found, _) => name == found }
    }
  }
}
