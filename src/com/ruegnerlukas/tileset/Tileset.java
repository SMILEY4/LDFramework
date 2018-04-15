package com.ruegnerlukas.tileset;

import java.awt.image.BufferedImage;

public class Tileset {

	private BufferedImage image;
	
	private int tilewidth;
	private int tileheight;
	private int spacing;
	private int margin;
	private int columns;
	
	
	
	
	
	
	public BufferedImage getImage() {
		return image;
	}
	
	
	
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	
	
	public int getTileWidth() {
		return tilewidth;
	}
	
	
	
	
	public void setTilewidth(int tilewidth) {
		this.tilewidth = tilewidth;
	}
	
	
	
	
	public int getTileHeight() {
		return tileheight;
	}
	
	
	
	
	public void setTileheight(int tileheight) {
		this.tileheight = tileheight;
	}
	
	
	
	
	public int getSpacing() {
		return spacing;
	}
	
	
	
	
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}
	
	
	
	
	public int getMargin() {
		return margin;
	}
	
	
	
	
	public void setMargin(int margin) {
		this.margin = margin;
	}
	
	
	
	
	public int getColumns() {
		return columns;
	}
	
	
	
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
}
