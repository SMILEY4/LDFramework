package com.ruegnerlukas.tilemap.tiles;

import java.util.HashMap;

import com.ruegnerlukas.properties.Property;

public class Tile {

	private int id;
	private boolean flippedHorizontally = false;
	private boolean flippedVertically= false;
	private boolean flippedDiagonally = false;
	
	private HashMap<String, Property> properties;

	
	
	
	
	
	public Tile(int id) {
		this.id = id;
	}

	
	
	
	
	
	public int getId() {
		return id;
	}

	
	
	
	public void setID(int id) {
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
	
	
	
	
	
	
	public Property getProperty(String name) {
		if(properties == null) {
			return null;
		}
		return properties.get(name);
	}
	
	
	
	
	public void addProperty(Property prop) {
		if(properties == null) {
			properties = new HashMap<String, Property>();
		}
		properties.put(prop.getName(), prop);
	}
	
	
	
	
	public void removeProperty(String name) {
		if(properties == null) {
			return;
		}
		properties.remove(name);
	}

	
	
	
	
	
	
}
