package view;

import controller.MainController;
import model.Player;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame implements MainController {
//Code Written by Yahya Zouhar

	private final Player[] players;
	MainPanel mainPanel;
	ControlPanel controlPanel;

	private int currentPlayerIndex;

	public MainFrame(Player[] players, Tile[] tiles, Tile freeTile) throws IOException {

		this.players = players;
        this.controlPanel = new ControlPanel(freeTile, this);
		this.mainPanel = new MainPanel(players, tiles, this);

		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.SOUTH);
		controlPanel.setPreferredSize(new Dimension(800, 60));

		this.setSize(1920,1080);
        this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	@Override
	public void setFreeTile(Tile freeTile) throws IOException {
		this.controlPanel.setFreeTile(freeTile);
	}

	@Override
	public Tile getFreeTile() {
		return controlPanel.getFreeTile();
	}

	@Override
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayerIndex];
	}

	@Override
	public void nextPlayer() {
        if (this.currentPlayerIndex == 3) this.currentPlayerIndex = 0;
		else this.currentPlayerIndex ++;
	}

}
