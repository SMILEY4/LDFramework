package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXTileset {

	// tileset
	public String name;
	public String source;
	public int firstgid;
	public int tilewidth;
	public int tileheight;
	public int spacing;
	public int margin;
	public int tilecount;
	public int columns;

	// image
	public TMXImage image;
	
	// offset
	public int offsetX;
	public int offsetY;
	
	// terraintypes
	public List<TMXTerrain> terrains = new ArrayList<TMXTerrain>();
	
	// tiles
	public List<TMXTile> tiles = new ArrayList<TMXTile>();
	
	// grid
	public String orientation;
	public int gridWidth;
	public int gridHeight;
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();

	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"TILESET:");
		System.out.println(spacing+"-name        = " + name);
		System.out.println(spacing+"-source      = " + source);
		System.out.println(spacing+"-firstgid    = " + firstgid);
		System.out.println(spacing+"-tilesize    = " + tilewidth + "x" + tileheight);
		System.out.println(spacing+"-spacing     = " + this.spacing);
		System.out.println(spacing+"-margin      = " + margin);
		System.out.println(spacing+"-tilecount   = " + tilecount);
		System.out.println(spacing+"-columns     = " + columns);
		System.out.println(spacing+"-offset      = " + offsetX + "," + offsetY);
		System.out.println(spacing+"-orientation = " + orientation);
		System.out.println(spacing+"-gridSize    = " + gridWidth + "x" + gridHeight);

		
		if(image != null) image.prettyPrint(layer+1);
		
		for(TMXTerrain t : terrains) {
			t.prettyPrint(layer+1);
		}
		for(TMXTile t : tiles) {
			t.prettyPrint(layer+1);
		}
		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		

	}
	
}
