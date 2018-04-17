package com.ruegnerlukas.tilemap.layers;

import java.util.HashMap;

import com.ruegnerlukas.properties.Property;

public class Layer {

	
	private String name = "";
	private float opacity = 1.0f;
	private boolean visible = true;
	
	private HashMap<String, Property> properties;

	
	
	
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



	
	public String getName() {
		return name;
	}


	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
