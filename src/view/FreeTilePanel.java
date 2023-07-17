package view;

import model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class FreeTilePanel extends JPanel {
//Code Written by Yahya Zouhar

	JLabel freeTileLabel = new JLabel();

    Tile freeTile;
	public FreeTilePanel(Tile freeTileImagePath) throws IOException {
		this.setLayout(new BorderLayout());
		this.setFreeTile(freeTileImagePath);
		JLabel freeTileHeader = new JLabel("Free Tile");
		this.add(freeTileHeader, BorderLayout.NORTH);
		BasicArrowButton rightButton = new BasicArrowButton(BasicArrowButton.EAST);
		BasicArrowButton leftButton = new BasicArrowButton(BasicArrowButton.WEST);

		this.add(rightButton, BorderLayout.EAST);
		this.add(freeTileLabel, BorderLayout.CENTER);
		this.add(leftButton, BorderLayout.WEST);

		leftButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int orientation = freeTile.getOrientation();
				orientation --;
				if (orientation < 0 ) orientation = 3;
				freeTile.setOrientation(orientation);
				try {
					setFreeTile(freeTile);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
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
		rightButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int orientation = freeTile.getOrientation();
				orientation ++;
				if (orientation > 3 ) orientation = 0;
				freeTile.setOrientation(orientation);
				try {
					setFreeTile(freeTile);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
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
		this.freeTile = freeTile;
		String imagePath = "resources/images/tiles/" + freeTile.getName() + freeTile.getOrientation() + ".png";
		System.out.println("image path" + imagePath);
		Image freeTileImage = ImageIO.read(new File(imagePath));
		Image newDownArrowImage = freeTileImage.getScaledInstance(50 ,50, Image.SCALE_SMOOTH);
		ImageIcon freeTileIcon = new ImageIcon(newDownArrowImage);
		freeTileLabel.setIcon(freeTileIcon);


	}

	public Tile getFreeTile(){
		return this.freeTile;
	}
}
