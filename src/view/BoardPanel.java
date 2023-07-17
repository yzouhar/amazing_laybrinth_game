package view;

import controller.MainController;
import model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Code Written by Yahya Zouhar

public class BoardPanel extends JPanel implements MouseListener {

	public static final String RESOURCES_IMAGES_TILES = "resources/images/tiles/";
	public static final String RESOURCES_IMAGES_BUTTONS = "resources/images/buttons/";
	private final MainController mainController;
	private BufferedImage boardImage;

	private Tile[] tiles;

	private final static int[] AVAILABLE_SPOTS = {1, 3, 5, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 21, 22, 23, 24, 25, 26, 27, 29, 31, 33, 35, 36, 37, 37, 38, 39, 40, 41, 43, 45, 47};
	private final static int[] AVAILABLE_SPOTS_FOR_ARROWS = { 1, 3, 5 };

	// vertical 1: 0, 4, 10, 14, 20, 24, 30
	private final static int[] VERTICAL_PATH_1 = { 0, 4, 10, 14, 20, 24, 30 };

	// vertical 2: 1, 6, 11, 16, 21, 26, 31
	private final static int[] VERTICAL_PATH_2 = { 1, 6, 11, 16, 21, 26, 31 };

	// vertical 3: 2, 8, 12, 18, 22, 28, 32
	private final static int[] VERTICAL_PATH_3 = { 2, 8, 12, 18, 22, 28, 32 };

	// horizontal 1 : 3 -> 9
	private final static int[] HORIZONTAL_PATH_1 = { 3, 4, 5, 6, 7, 8, 9};

	// horizontal 2 : 13 -> 19
	private final static int[] HORIZONTAL_PATH_2 = { 13, 14, 15, 16, 17, 18, 19};

	// horizontal 3:  23 -> 29
	private final static int[] HORIZONTAL_PATH_3 = { 23, 24, 25, 26, 27, 28, 29};

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

	enum DIRECTIONS {UP, DOWN, RIGHT, LEFT};

	public BoardPanel(Tile[] tiles, MainController controller) throws IOException {
		this.mainController = controller;
		boardImage = ImageIO.read(new File("resources/images/GameBoard.png"));
		this.tiles = tiles;
		this.addMouseListener(this);
	}

    public void shiftTilesVertically(int position, Enum direction) throws IOException {

		int[] tiles_path = null;

		if (position == 1) {
			tiles_path = VERTICAL_PATH_1;
		} else if (position == 2 ) {
			tiles_path = VERTICAL_PATH_2;
		} else if (position == 3 )  {
			tiles_path = VERTICAL_PATH_3;
		}

		if (direction.equals(DIRECTIONS.UP)) {
			// shift down
			Tile freeTile = tiles[tiles_path[0]];
			mainController.setFreeTile(freeTile);

			System.out.println("Out to Free controller.model.Tile:" + freeTile);
			for (int i = 0; i < tiles_path.length - 1; i++) {
				tiles[tiles_path[i]] = tiles[tiles_path[i+1]];
			}
			// TODO: set the free tile
			tiles[tiles_path[6]] = mainController.getFreeTile();
			System.out.println("In to Board:" + mainController.getFreeTile());

		} else if (direction.equals(DIRECTIONS.DOWN)) {
			Tile freeTile = tiles[tiles_path[6]];
			for (int i = tiles_path.length - 2; i >= 0; i--) {
				tiles[tiles_path[i + 1]] = tiles[tiles_path[i]];
			}
			tiles[tiles_path[0]] = mainController.getFreeTile();
			System.out.println("In to Board:" + mainController.getFreeTile());
			mainController.setFreeTile(freeTile);

		}
    }

	public void shiftTilesHorizontally(int position, Enum direction) throws IOException {
		int[] tiles_path = null;

		if (position == 1) {
			tiles_path = HORIZONTAL_PATH_1;
		} else if (position == 2 ) {
			tiles_path = HORIZONTAL_PATH_2;
		} else if (position == 3 )  {
			tiles_path = HORIZONTAL_PATH_3;
		}

		if (direction.equals(DIRECTIONS.LEFT)) {
			Tile freeTile = tiles[tiles_path[0]];
			for (int i = 0; i < tiles_path.length - 1; i++) {
				tiles[tiles_path[i]] = tiles[tiles_path[i+1]];
			}
			tiles[tiles_path[6]] = mainController.getFreeTile();
			mainController.setFreeTile(freeTile);
			System.out.println("In to Board:" + mainController.getFreeTile());

		} else if (direction.equals(DIRECTIONS.RIGHT)) {
			Tile freeTile = tiles[tiles_path[6]];
			for (int i = tiles_path.length - 2; i >= 0; i--) {
				tiles[tiles_path[i + 1]] = tiles[tiles_path[i]];
			}
			tiles[tiles_path[0]] = mainController.getFreeTile();
			mainController.setFreeTile(freeTile);
			System.out.println("In to Board:" + mainController.getFreeTile());

		}
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardImage,  this.getX() * 5, this.getY() / 2, this);
		Graphics2D g2d = (Graphics2D) g.create();

		int X_START_COORDINATE = 300;
		int Y_START_COORDINATE = 30;

		int X_COORDINATE = X_START_COORDINATE;
		int Y_COORDINATE = Y_START_COORDINATE;

		int X_LIMIT = 900;
		int Y_LIMIT = 672;

		int X_START_COORDINATE_FOR_ARROWS = 980;
		int Y_START_COORDINATE_FOR_ARROWS = 165;

		int X_COORDINATE_FOR_ARROWS = X_START_COORDINATE_FOR_ARROWS;
		int Y_COORDINATE_FOR_ARROWS = Y_START_COORDINATE_FOR_ARROWS;

		final int DELTA = 96;

		 int index = 0;
		 int movable_tile_index = 0;

		 while (Y_COORDINATE <= Y_LIMIT) {
				 if (checkForSpots(index, AVAILABLE_SPOTS)) {
					 BufferedImage currentTile;
					 try {
						 String tilePath = RESOURCES_IMAGES_TILES + this.tiles[movable_tile_index].getName() + this.tiles[movable_tile_index].getOrientation() + ".png";
						 System.out.println("image path: " + tilePath);
						 currentTile = ImageIO.read(new File(tilePath));
					 } catch (IOException e) {
						 throw new RuntimeException(e);
					 }
					 g2d.drawImage(currentTile, X_COORDINATE, Y_COORDINATE, this);
					 movable_tile_index ++;
				 }
			 try {
				 placeArrows(g2d, X_COORDINATE, Y_COORDINATE_FOR_ARROWS, index);
			 } catch (IOException e) {
				 throw new RuntimeException(e);
			 }

			 X_COORDINATE += DELTA;
			if (X_COORDINATE >= X_LIMIT){
				X_COORDINATE = X_START_COORDINATE;
				Y_COORDINATE = Y_COORDINATE + DELTA;
			}
			if (Y_COORDINATE >= Y_LIMIT)
				g2d.dispose();
			index ++;
		 }

	}

	private void placeArrows(Graphics2D g2d, int X_COORDINATE, int Y_COORDINATE_FOR_ARROWS, int index) throws IOException {

		BufferedImage downArrowImage;
		BufferedImage upArrowImage;
		BufferedImage leftArrowImage;

		for (int i = 0; i < AVAILABLE_SPOTS_FOR_ARROWS.length; i++) {
			if (checkForSpots(index, AVAILABLE_SPOTS_FOR_ARROWS)) {
				try {
					downArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "DownArrow.png"));
					Image newDownArrowImage = downArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
					g2d.drawImage(newDownArrowImage, X_COORDINATE + 40, 15, this);

				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		for (int i = 0; i < AVAILABLE_SPOTS_FOR_ARROWS.length; i++) {
			if (checkForSpots(index, AVAILABLE_SPOTS_FOR_ARROWS)) {
				try {
					upArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "UpArrow.png"));
					Image newUpArrowImage = upArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
					g2d.drawImage(newUpArrowImage, X_COORDINATE + 40, 700, this);

				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

		BufferedImage rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "rightArrow.png"));
		Image newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  280, 170, this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "rightArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  280, 360, this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "rightArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  280,550 , this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "rightArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  280, 360, this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "leftArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  970,360 , this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "leftArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  970,550 , this);

		rightArrowImage = ImageIO.read(new File(RESOURCES_IMAGES_BUTTONS + "leftArrow.png"));
		newRightArrowImage = rightArrowImage.getScaledInstance(15,15, Image.SCALE_SMOOTH);
		g2d.drawImage(newRightArrowImage,  970,170 , this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		boolean shift_down_row_1 =  (420 <= e.getX() && e.getX() < 450 && 20 <= e.getY() && e.getY() <= 30 );
		boolean shift_down_row_2 =  (610 <= e.getX() && e.getX() < 640 && 20 <= e.getY() && e.getY() <= 30 );
		boolean shift_down_row_3 =  (800 <= e.getX() && e.getX() < 830 && 20 <= e.getY() && e.getY() <= 30 );
		try {
			if (shift_down_row_1) {
				this.shiftTilesVertically(1, DIRECTIONS.DOWN);
			} else if (shift_down_row_2) {
				this.shiftTilesVertically(2, DIRECTIONS.DOWN);
			} else if (shift_down_row_3) {
				this.shiftTilesVertically(3, DIRECTIONS.DOWN);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		boolean shift_up_row_1 =  (420 <= e.getX() && e.getX() < 450 && 700 <= e.getY() && e.getY() <= 710 );
		boolean shift_up_row_2 =  (610 <= e.getX() && e.getX() < 640 && 700 <= e.getY() && e.getY() <= 710 );
		boolean shift_up_row_3 =  (800 <= e.getX() && e.getX() < 830 && 700 <= e.getY() && e.getY() <= 710 );
		try {
			if (shift_up_row_1) {
				this.shiftTilesVertically(1, DIRECTIONS.UP);
			} else if (shift_up_row_2) {
				this.shiftTilesVertically(2, DIRECTIONS.UP);
			} else if (shift_up_row_3) {
				this.shiftTilesVertically(3, DIRECTIONS.UP);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		boolean shift_right_row_1 =  (280 <= e.getX() && e.getX() < 300 && 170 <= e.getY() && e.getY() <= 190 );
		boolean shift_right_row_2 =  (280 <= e.getX() && e.getX() < 300 && 360 <= e.getY() && e.getY() <= 380 );
		boolean shift_right_row_3 =  (280 <= e.getX() && e.getX() < 300 && 545 <= e.getY() && e.getY() <= 560 );
		try {
			if (shift_right_row_1) {
				this.shiftTilesHorizontally(1, DIRECTIONS.RIGHT);
			} else if (shift_right_row_2) {
				this.shiftTilesHorizontally(2, DIRECTIONS.RIGHT);
			} else if (shift_right_row_3) {
				this.shiftTilesHorizontally(3, DIRECTIONS.RIGHT);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		boolean shift_left_row_1 =  (970 <= e.getX() && e.getX() < 990 && 170 <= e.getY() && e.getY() <= 190 );
		boolean shift_left_row_2 =  (970 <= e.getX() && e.getX() < 990 && 360 <= e.getY() && e.getY() <= 380 );
		boolean shift_left_row_3 =  (970 <= e.getX() && e.getX() < 990 && 545 <= e.getY() && e.getY() <= 560 );
		try {
			if (shift_left_row_1) {
				this.shiftTilesHorizontally(1, DIRECTIONS.LEFT);
			} else if (shift_left_row_2) {
				this.shiftTilesHorizontally(2, DIRECTIONS.LEFT);
			} else if (shift_left_row_3) {
				this.shiftTilesHorizontally(3, DIRECTIONS.LEFT);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		this.repaint();
	}

	private boolean checkForSpots(int number, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (number == array[i]) {
				return true;
			}
		}
		return false;
	}
}

