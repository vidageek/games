package vggames.shared

import scala.slick.session.{ Database => SQDB }

trait Database {

  def onDatabase[T](f : => T) = SQDB.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession(f)

}