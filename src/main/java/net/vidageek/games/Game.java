package net.vidageek.games;

public interface Game {

	Class<? extends GameController> beginClass();

	int size();
}
