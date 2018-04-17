package com.ruegnerlukas.tilemap.layers;

import com.ruegnerlukas.tilemap.tiles.Tile;

public class TileLayer extends Layer {

	private int width, height;
	private int tileWidth, tileHeight;
	private Tile[][] tiles;
	
	
	
	
	public TileLayer(int width, int height, int tileWidth, int tileHeight) {
		
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		tiles = new Tile[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				setTile(new Tile(0), x, y);
			}
		}
		
	}
	
	
	
	
	
	
	public boolean setTile(Tile tile, int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return false;
		}
		tiles[x][y] = tile;
		return true;
	}
	
	
	
	
	public Tile getTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return tiles[x][y];
	}
	
	
	
	
	public int getWidth() {
		return this.width;
	}
	
	
	
	
	public int getHeight() {
		return this.height;
	}
	
	
	
	
	public int getCellWidth() {
		return this.tileWidth;
	}
	
	
	
	
	public int getCellHeight() {
		return this.tileHeight;
	}
	

}
