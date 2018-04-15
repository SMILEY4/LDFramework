package com.ruegnerlukas.tilemap;



public class Tile {

	private int id;
	private boolean flippedHorizontally = false;
	private boolean flippedVertically= false;
	private boolean flippedDiagonally = false;
	
	
	
	
	
	
	public Tile(int id) {
		this.id = id;
	}

	
	
	
	
	
	public int getId() {
		return id;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}

	
	
	public boolean isFlippedHorizontally() {
		return flippedHorizontally;
	}

	
	
	public void setFlippedHorizontally(boolean flippedHorizontally) {
		this.flippedHorizontally = flippedHorizontally;
	}

	
	
	public boolean isFlippedVertically() {
		return flippedVertically;
	}

	
	
	public void setFlippedVertically(boolean flippedVertically) {
		this.flippedVertically = flippedVertically;
	}

	
	
	public boolean isFlippedDiagonally() {
		return flippedDiagonally;
	}

	
	
	public void setFlippedDiagonally(boolean flippedDiagonally) {
		this.flippedDiagonally = flippedDiagonally;
	}

	
	
	
	
	
	
}
