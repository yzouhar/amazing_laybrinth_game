package model;

@SuppressWarnings("ALL")
public class Card {
	
	//fields
	private String name;
	private int ownsCard;
	private int point;
	private boolean foundCard;

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOwnsCard() {
		return ownsCard;
	}

	//checks which player has the treasure card
	public void setOwnsCard(int ownsCard) {
			this.ownsCard = ownsCard;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
			this.point = point;
	}

	public boolean isFoundCard() {
		return foundCard;
	}

	public void setFoundCard(boolean foundCard) {
		this.foundCard = foundCard;
	}

	//toString method
	@Override
	public String toString() {
		return "model.Card [name=" + name + ", ownsCard=" + ownsCard + ", point=" + point + ", foundCard=" + foundCard + "]";
	}

}