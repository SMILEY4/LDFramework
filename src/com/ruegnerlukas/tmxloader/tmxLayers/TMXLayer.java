package com.ruegnerlukas.tmxloader.tmxLayers;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.TMXProperty;

public abstract class TMXLayer {

	public String name = "";
	public float opacity = 1;
	public boolean visible = true;
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
	
	
	
	public abstract void prettyPrint(int level);
	
}
