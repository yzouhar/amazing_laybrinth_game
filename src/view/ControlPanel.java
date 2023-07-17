package view;

import controller.MainController;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

//Code Written by Yahya Zouhar
public class ControlPanel extends JPanel {

	private final MainController mainController;
	private final FreeTilePanel freeTilePanel;
	private  JPanel playerPanel = new JPanel();
	private  JLabel playerNameLabel = new JLabel();

	public ControlPanel(Tile freeTile, MainController mainController) throws IOException {
		this.freeTilePanel = new FreeTilePanel(freeTile);
		this.mainController = mainController;
		this.setLayout(new GridLayout(1, 3, 500 , 500));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerNameLabel.setText(mainController.getCurrentPlayer().getName());
		playerPanel.add(playerNameLabel);
		playerPanel.setBackground(mainController.getCurrentPlayer().getColor());
		this.add(playerPanel);
		this.add(new ButtonPanel());
		this.add(this.freeTilePanel);

		playerPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainController.nextPlayer();
				playerPanel.setBackground(mainController.getCurrentPlayer().getColor());
				playerNameLabel.setText(mainController.getCurrentPlayer().getName());
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}
	public void setFreeTile(Tile freeTile) throws IOException {
		this.freeTilePanel.setFreeTile(freeTile);
		this.freeTilePanel.repaint();
	}

	public Tile getFreeTile() {
		return this.freeTilePanel.getFreeTile();
	}
}
