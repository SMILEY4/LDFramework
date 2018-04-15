package com.ruegnerlukas.tilemap;

import java.util.ArrayList;
import java.util.List;

public class Tilemap {

	
	private List<Layer> layers;
	
	
	
	
	
	
	public Tilemap() {
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
	
	
	
	
}




