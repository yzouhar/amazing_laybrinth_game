package controller;

import model.Player;
import model.Tile;

import java.io.IOException;

public interface MainController {

	void setFreeTile(Tile freeTile) throws IOException;

	Tile getFreeTile();

	Player getCurrentPlayer();

	void nextPlayer();

}
