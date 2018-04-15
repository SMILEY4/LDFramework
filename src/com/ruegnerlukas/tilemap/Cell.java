package com.ruegnerlukas.tilemap;



public class Cell {

	private final int X;
	private final int Y;
	
	private Tile tile;
	
	
	
	
	
	public Cell(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	
	

	
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	
	
	
	public Tile getTile() {
		return this.tile;
	}
	
	
	
	
	public int getX() {
		return this.X;
	}
	
	
	
	
	public int getY() {
		return this.Y;
	}
	
	
}
