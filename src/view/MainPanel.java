package view;

import controller.MainController;
import model.Player;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {
	//Code Written by Yahya Zouhar

	private  final MainController mainController;
	public MainPanel(Player[] players, Tile[] tiles, MainController mainController) throws IOException {

		BoardPanel boardPanel = new BoardPanel(tiles, mainController);
		CardPanel[] cardPanels = new CardPanel[4];

		for(int i = 0; i< players.length; i ++ ){
			cardPanels[i] = new CardPanel(players[i]);
		}

		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.add(boardPanel, BorderLayout.CENTER);
		this.mainController = mainController;
		for (int i = 0; i < 4; i++) {
			this.add(cardPanels[i], cardPanels[i].getPlayer().getDirection());
			if (cardPanels[i].getPlayer().getDirection().equalsIgnoreCase(BorderLayout.EAST) || cardPanels[i].getPlayer().getDirection().equalsIgnoreCase(BorderLayout.WEST)) {
				cardPanels[i].setPreferredSize(new Dimension(60, this.getHeight()));
			} else {
				cardPanels[i].setPreferredSize(new Dimension(this.getHeight(), 60));

			}
		}
	}

}