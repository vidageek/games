package vggames.shared

import org.scalaquery.session.{ Database => SQDB }
import org.scalaquery.session.Database.threadLocalSession

trait Database {

  def onDatabase[T](f : => T) = SQDB.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession(f)

}