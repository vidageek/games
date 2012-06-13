package vggames.player

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerListSpec extends Specification {
  "PlayerList" should {

    "InsertPlayer" in {
      val playerList = new PlayerList()
      playerList.append(Player("asdrubal"))

      val player = playerList.get("asdrubal")
      player must_== Some(Player("asdrubal"))
    }
  }
}
