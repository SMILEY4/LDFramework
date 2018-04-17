package com.ruegnerlukas.tilemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruegnerlukas.properties.Property;
import com.ruegnerlukas.tilemap.layers.Layer;

public class Map {

	
	private List<Layer> layers;
	private HashMap<String, Property> properties;
	
	
	
	
	
	public Map() {
		layers = new ArrayList<Layer>();
	}
	
	
	
	
	
	
	public boolean addLayer(Layer layer) {
		layers.add(layer);
		return true;
	}

	
	
	
	public boolean addLayer(Layer layer, int index) {
		if(index < 0 || index >= layers.size()) {
			return false;
		}
		layers.add(index, layer);
		return true; 
	}

	
	
	
	public boolean removeLayer(int index) {
		if(index < 0 || index >= layers.size()) {
			return false;
		}
		layers.remove(index);
		return true;
	}

	
	
	
	public boolean removeLayer(Layer layer) {
		return layers.remove(layer);
	}

	
	
	
	public Layer getLayer(int index) {
		if(index < 0 || index >= layers.size()) {
			return null;
		}
		return layers.get(index);
	}
	
	
	
	
	public int getNumLayers() {
		return layers.size();
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




