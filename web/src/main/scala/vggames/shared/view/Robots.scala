package vggames.shared.view

import scalatags._

class Robots extends TypedView[(List[String], List[String])] {

  override def renderString(t : (List[String], List[String])) = {
    val (games, inactiveGames) = t

    ("User-Agent: *" ::
      games.map(game => s"Disallow: /play/$game/task").mkString("\n") ::
      games.map(game => s"Disallow: /play/$game/resource").mkString("\n") ::
      inactiveGames.map(game => s"Disallow: /play/$game").mkString("\n") ::
      inactiveGames.map(game => s"Disallow: /reference/$game").mkString("\n") :: Nil).mkString("\n\n")
  }

  override def contentType = "text/plain"
}