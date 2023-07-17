package model;

public class Tile {

	//fields
	private String name;
	private String shape;

	private int orientation;
	private boolean movable;
	private int row; //-1 means no default position
	private int column; //-1 means no default position

	public Tile(){

	}
	public Tile(String name, String shape, int orientation, boolean movable, int row, int column) {
		this.name = name;
		this.shape = shape;
		this.orientation = orientation;
		this.movable = movable;
		this.row = row;
		this.column = column;
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

//	public boolean isTreasure() {
//		return treasure;
//	}
//
//	public void setTreasure(boolean treasure) {
//		this.treasure = treasure;
//	}

	//toString method
	@Override
	public String toString() {
		return "controller.model.Tile [name=" + name + ", shape=" + shape + ", orientation=" + orientation + ", movable=" + movable + ", row=" + row
				+ ", column=" + column + "]";
	}



}
