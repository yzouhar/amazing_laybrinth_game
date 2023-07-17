package model;

import java.awt.*;

public class Player {

	private Color color;
	private String[] cards;

	private String name;
	private String direction;

	public Player(String name, Color color, String[] cards, String direction) {
		this.color = color;
		this.cards = cards;
		this.direction = direction;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public String[] getCards() {
		return cards;
	}

	public String getDirection() {
		return direction;
	}

	public void setCards(String[] cards) {
		this.cards = cards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
