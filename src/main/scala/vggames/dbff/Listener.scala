package vggames.dbff

import javax.servlet.ServletContextListener
import javax.servlet.ServletContextEvent
import java.util.Enumeration
import scala.io.Source
import org.scalaquery.ql.extended.ExtendedTable
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database
import org.scalaquery.session.Database.threadLocalSession
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.PreparedStatement
import java.util.Scanner

class Listener extends ServletContextListener {

  implicit def addVersion(line : String) = new {
    val regex = "^(\\d+).*".r
    val regex(versionAsString) = line
    def version = versionAsString.toInt
  }

  override def contextInitialized(event : ServletContextEvent) {
    val dbVersion = DB.version

    Read("/dbff/dbff.files").getLines.foreach { line =>
      if (line.version > dbVersion) {
        DB.bump(line, line.version)
      }
    }
  }

  override def contextDestroyed(event : ServletContextEvent) {
  }

}

object Read {
  def apply(filename : String) = Source.fromInputStream(this.getClass.getResourceAsStream(filename))
}

object DB {

  Class.forName("org.sqlite.JDBC")

  def version : Int = {
    if (versionTableExists)
      query("""SELECT version FROM dbff_version;""")(_.getInt(1))
    else {
      println("Criando tabela de controle de versao do schema")
      update("""CREATE TABLE dbff_version ("version" int)""")
      update("""INSERT INTO dbff_version values(0)""")
      0
    }
  }

  def bump(name : String, version : Int) = {
    val filename = "/dbff/" + name
    println("Rodando script " + filename)
    update(Read(filename).getLines.mkString("\n"))
    update("UPDATE dbff_version SET version=" + version)
  }

  def versionTableExists : Boolean = query("SELECT name FROM sqlite_master WHERE type='table' AND name='dbff_version';")(_.next)

  def update(sql : String) : Unit = run(sql, stmt => { stmt.executeUpdate; Noop }, noop)

  def query[T](sql : String)(produceResult : ResultSet => T) : T = run(sql, _.executeQuery, produceResult)

  type Closeable = { def close() : Unit }

  def noop(a : Closeable) = {}

  object Noop {
    def close() = {}
  }

  def run[T, V <: Closeable](sql : String, executeStatement : PreparedStatement => V, produceResult : V => T) : T = {
    autoclose(DriverManager.getConnection("jdbc:sqlite:games.db")) { con =>
      autoclose(con.prepareStatement(sql)) { stmt =>
        autoclose(executeStatement(stmt))(produceResult)
      }
    }
  }

  def autoclose[T <: Closeable, R](code : => T)(f : T => R) : R = {
    val applied = code
    try {
      f(applied)
    } finally {
      applied.close
    }
  }
}
