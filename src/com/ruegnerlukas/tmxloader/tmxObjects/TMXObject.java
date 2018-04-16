package com.ruegnerlukas.tmxloader.tmxObjects;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.TMXProperty;

public abstract class TMXObject {

	public int id;
	public String name;
	public String type;
	public int rotation; // in deg
	public int gid;
	public boolean visible = true;
	public float x;
	public float y;
	public float width = 0;
	public float height = 0;
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
	
	public abstract void prettyPrint(int layer);
	
}
