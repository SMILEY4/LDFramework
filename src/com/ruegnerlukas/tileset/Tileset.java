package com.ruegnerlukas.tileset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruegnerlukas.properties.Property;

public class Tileset {

	private List<TilesetLayer> layers;
	
	private HashMap<String, Property> properties;

	
	
	
	public Tileset() {
		layers = new ArrayList<TilesetLayer>();
	}
	
	
	
	
	public void addLayer(TilesetLayer layer) {
		if(layers.isEmpty()) {
			layers.add(layer);
		} else {
			for(int i=0; i<layers.size(); i++) {
				if(layers.get(i).getFirstID() > layer.getFirstID()) {
					layers.add(i, layer);
					return;
				}
			}
		}
	}
	
	
	
	
	public TilesetLayer getLayer(int id) {
		for(int i=0; i<layers.size(); i++) {
			TilesetLayer layer = layers.get(i);
			if(layer.getFirstID() <= id && layer.getFirstID()+layer.getTileCount() > id) {
				return layer;
			}
		}
		return null;
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










