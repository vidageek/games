package net.vidageek.games.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayerListTest {

  @Test
  public void shouldInsertPlayer() {
    PlayerList list = new PlayerList();
    list.append(new Player("asdrubal"));
    Player player = list.get("asdrubal");
    assertNotNull(player);
    assertEquals("asdrubal", player.name());

  }
}
