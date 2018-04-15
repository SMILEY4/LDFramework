package com.ruegnerlukas.tilemap;



public class Layer {

	
	private int width, height;
	private int cellWidth, cellHeight;
	private Cell[][] cells;

	private float opacity = 1.0f;
	private boolean visible = true;
	
	
	
	
	
	public Layer(int width, int height, int cellWidth, int cellHeight) {
		
		this.width = width;
		this.height = height;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		
		cells = new Cell[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				cells[x][y] = new Cell(x, y);
			}
		}
		
	}
	
	
	
	
	
	
	public Cell getCell(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return cells[x][y];
	}
	
	
	
	
	public int getWidth() {
		return this.width;
	}
	
	
	
	
	public int getHeight() {
		return this.height;
	}
	
	
	
	public int getCellWidth() {
		return this.cellWidth;
	}
	
	
	
	
	public int getCellHeight() {
		return this.cellHeight;
	}
	
	
	
	
	public float getOpacity() {
		return opacity;
	}



	
	public void setOpacity(float opacity) {
		this.opacity = Math.max(0f, Math.min(opacity, 1f));
	}


	
	
	public boolean isVisible() {
		return visible;
	}



	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
}
