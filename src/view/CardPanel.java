package view;

import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardPanel extends JPanel {
//Code Written by Yahya Zouhar

	public static final String CARD_IMAGE_PATH = "resources/images/cards";
	private Player player;

	public CardPanel(Player player) throws IOException {
		this.player = player;
		this.setBackground(player.getColor());

		if (player.getDirection().equalsIgnoreCase(BorderLayout.NORTH) || player.getDirection().equalsIgnoreCase(BorderLayout.SOUTH)) {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		if (player.getDirection().equalsIgnoreCase(BorderLayout.EAST) || player.getDirection().equalsIgnoreCase(BorderLayout.WEST)) {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setAlignmentY(Component.CENTER_ALIGNMENT);
		}

		if (player.getDirection().equalsIgnoreCase(BorderLayout.SOUTH) || player.getDirection().equalsIgnoreCase(BorderLayout.NORTH)) {
			this.add( Box.createHorizontalGlue() );
		} else if (player.getDirection().equalsIgnoreCase(BorderLayout.EAST) || player.getDirection().equalsIgnoreCase(BorderLayout.WEST)) {
			this.add( Box.createVerticalGlue() );
		}

		for (int i = 0; i < player.getCards().length; i++) {
		   JLabel cardLabel = new JLabel();
		   String imagePath = CARD_IMAGE_PATH + "/" + player.getCards()[i] + ".png";
		   BufferedImage bufferedImage = rotate(imagePath);
		   ImageIcon imageIcon = new ImageIcon(bufferedImage);
		   Image image = imageIcon.getImage();
		   Image newimg = image.getScaledInstance(40, 50,  Image.SCALE_SMOOTH); // scale it the smooth way
			cardLabel.setIcon(new ImageIcon(newimg));
		   this.add(cardLabel);
	   }

		if (player.getDirection().equalsIgnoreCase(BorderLayout.SOUTH) || player.getDirection().equalsIgnoreCase(BorderLayout.NORTH)) {
			this.add( Box.createHorizontalGlue() );
		} else if (player.getDirection().equalsIgnoreCase(BorderLayout.EAST) || player.getDirection().equalsIgnoreCase(BorderLayout.WEST)) {
			this.add( Box.createVerticalGlue() );
		}

	}

	private BufferedImage rotate(String fileName) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(fileName));
		if (this.player.getDirection().equalsIgnoreCase(BorderLayout.WEST)){
			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.PI/2, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			bufferedImage = op.filter(bufferedImage, null);
		}
		if (player.getDirection().equalsIgnoreCase(BorderLayout.EAST)){
			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.PI * 3/2, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			bufferedImage = op.filter(bufferedImage, null);
		}
		return bufferedImage;
	}

	public Player getPlayer() {
		return player;
	}

}
