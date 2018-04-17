package com.ruegnerlukas.tilemap.layers;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tilemap.mapobjects.MapObject;

public class ObjectLayer extends Layer {

	private List<MapObject> objects;
	
	
	
	
	public ObjectLayer() {
		objects = new ArrayList<MapObject>();
	}
	
	
	
	
	
	
	public void addObject(MapObject obj) {
		objects.add(obj);
	}

	
	
	
	public void removeObject(MapObject obj) {
		objects.remove(obj);
	}
	
	
	
	
	public List<MapObject> getObjects() {
		return this.objects;
	}
	
	
	
}
