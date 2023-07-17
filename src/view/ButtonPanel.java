package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Code Written by Yahya Zouhar

public class ButtonPanel extends JPanel {


	public static final String RESOURCES_IMAGES_BUTTONS = "resources/images/buttons";
	String direction;

	public JButton leftButton;
	public JButton rightButton;
	public JButton centerButton;
	public JButton downButton;
	public JButton upButton;

	public ButtonPanel() throws IOException {

		this.setLayout(new BorderLayout());

		Image leftArrow = ImageIO.read (new File(RESOURCES_IMAGES_BUTTONS + "/LeftArrow.png"));
		leftButton = new JButton(new ImageIcon(leftArrow));
		this.add(leftButton, BorderLayout.WEST);

		Image rightArrow = ImageIO.read (new File(RESOURCES_IMAGES_BUTTONS + "/RightArrow.png"));
		rightButton = new JButton(new ImageIcon(rightArrow));
		this.add(rightButton, BorderLayout.EAST);

		centerButton = new JButton("OK");
		this.add(centerButton, BorderLayout.CENTER);

		Image upArrow = ImageIO.read (new File(RESOURCES_IMAGES_BUTTONS + "/UpArrow.png"));
		upButton = new JButton(new ImageIcon(upArrow));
		this.add(upButton, BorderLayout.NORTH);

		Image downArrow = ImageIO.read (new File(RESOURCES_IMAGES_BUTTONS + "/DownArrow.png"));
		downButton = new JButton(new ImageIcon(downArrow));
		this.add(downButton, BorderLayout.SOUTH);

		this.setSize(new Dimension(100, 50));

	}

	public String getDirection() {
		return direction;
	}
}
