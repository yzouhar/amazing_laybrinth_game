import model.Player;
import model.Tile;
import view.MainFrame;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainTest {
	//Code Written by Yahya Zouhar


	public static void main(String[] args) throws IOException {

		Player[] players = new Player[]{
				new Player("Player1", Color.RED, new String[]{"CardBat", "CardHorse", "CardMoney"}, "North"),
				new Player("Player2", Color.BLUE, new String[]{"CardBat", "CardBeetle", "CardHorse", "CardMoney"}, "East"),
				new Player("Player3", Color.YELLOW, new String[]{"CardBat", "CardHorse", "CardMoney"}, "South"),
				new Player("Player4", Color.GREEN, new String[]{"CardBat", "CardBeetle", "CardHorse", "CardMoney"}, "West")
		};

		//TODO: Randomize the tiles, 1 tile = 1 orientation
		//TODO: Add arrows to free tile
		//TODO: 13 I Paths and 9L paths
		//TODO: Add getDirection to tile when adding tile to board
		//TODO: Save the game feature

		// load tiles from the file
		Tile[] tiles = inputTreasureTiles();

		// randomly generate files to place to place on the board
		// this will be moved to controller
		Tile[] randomTiles = generateRandomTiles(tiles);

		// This should generated. It is hardcoded here for the UI test

		Tile[] tileList = new Tile[]{
				new Tile("Beetle", "T", 0, true, 1, 2),
				new Tile("Diamond", "T", 0, true, 1, 4),
				new Tile("Ghost", "T", 0, true, 1, 6),
				new Tile("Beetle", "T", 0, true, 1, 7),
				new Tile("L", "T", 0, true, 2, 1),
				new Tile("Sorceress", "T", 3, true, 2, 2),
				new Tile("I", "T", 1, true, 2, 3),
				new Tile("Beetle", "T", 0, true, 2, 4),
				new Tile("Mouse", "T", 2, true, 2, 5),
				new Tile("Lizard", "T", 3, true, 3, 7),
				new Tile("Horse", "T", 0, true, 3, 1),
				new Tile("I", "T", 2, true, -1, -1),
				new Tile("Lizard", "T", 0, true, -1, -1),
				new Tile("Beetle", "T", 0, true, -1, -1),
				new Tile("I", "T", 0, true, -1, -1),
				new Tile("L", "T", 1, true, -1, -1),
				new Tile("I", "T", 3, true, -1, -1),
				new Tile("Map", "T", 1, true, -1, -1),
				new Tile("L", "T", 2, true, -1, -1),
				new Tile("I", "T", 1, true, -1, -1),
				new Tile("Lizard", "T", 1, true, -1, -1),
				new Tile("L", "T", 3, true, -1, -1),
				new Tile("TreasureChest", "T", 3, true, -1, -1),
				new Tile("I", "T", 0, true, -1, -1),
				new Tile("L", "T", 1, true, -1, -1),
				new Tile("I", "T", 3, true, -1, -1),
				new Tile("Map", "T", 1, true, -1, -1),
				new Tile("L", "T", 2, true, -1, -1),
				new Tile("I", "T", 1, true, -1, -1),
				new Tile("Lizard", "T", 1, true, -1, -1),
				new Tile("L", "T", 1, true, -1, -1),
				new Tile("I", "T", 0, true, -1, -1),
				new Tile("Map", "T", 1, true, -1, -1),
				new Tile("L", "T", 2, true, -1, -1),
				new Tile("I", "T", 2, true, -1, -1),
				new Tile("Lizard", "T", 1, true, -1, -1),
				new Tile("L", "T", 3, true, -1, -1),
				new Tile("TreasureChest", "T", 3, true, -1, -1),
				new Tile("I", "T", 0, true, -1, -1)
		};

		new MainFrame(players, tileList, new Tile("Beetle", "T", 0, true, -1, -1));

	}

	private static Tile[] generateRandomTiles(Tile[] tiles) {
		return tiles;
	}

	// The original code didn't work. I had to use StringTokenizer. Tested and loading all tiles properly from Treasure.csv
	static private Tile[] inputTreasureTiles() {

		Tile[] tiles = new Tile[24];
		try {
			//retrieves Treasures.csv file
			Scanner input = new Scanner(new File("resources/data/Treasures.csv"));
			input.useDelimiter(",|\n");

			//initializes index variable
			int index = 0;

			//reads each line of input from file

			//continues to read each line from file until there is no more lines
			while (input.hasNext()) {
				String currentLine = input.nextLine();;
				StringTokenizer stringTokenizer = new StringTokenizer(currentLine, ",");
				//sets each index as a Tile
				tiles[index] = new Tile();
				//saves each line from file to an array index
				tiles[index].setName(stringTokenizer.nextToken());
				tiles[index].setShape(stringTokenizer.nextToken());
				tiles[index].setOrientation(Integer.parseInt(stringTokenizer.nextToken()));
				tiles[index].setMovable(Boolean.valueOf(stringTokenizer.nextToken()));
				tiles[index].setRow(Integer.parseInt(stringTokenizer.nextToken()));
				tiles[index].setColumn(Integer.parseInt(stringTokenizer.nextToken()));
				//Optional - prints treasureTile array in console
				System.out.println(tiles[index]);
				//increases index by 1
				index++;
			}
			//handles exceptions and errors
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return tiles;

	}
}
