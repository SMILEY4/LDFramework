package com.ruegnerlukas.tilemap.mapobjects;

import java.util.HashMap;

import com.ruegnerlukas.properties.Property;
import com.ruegnerlukas.simplemath.geometry.shapes.rectangle.Rectanglef;

public abstract class MapObject {

	private Rectanglef bounds;
	private HashMap<String, Property> properties;

	
	
	
	public MapObject() {
		bounds = new Rectanglef(0,0,0,0);
	}
	
	
	
	
	
	
	public Rectanglef getBounds() {
		return this.bounds;
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
