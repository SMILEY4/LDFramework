package com.ruegnerlukas.tmxloader.tmxLayers;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.TMXProperty;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXObject;

public class TMXObjectLayer extends TMXLayer {

	// layer
	public String color;
	public int offsetX = 0;
	public int offsetY = 0;
	public String drawOrder = "topdown";
	
	// objects
	public List<TMXObject> objects = new ArrayList<TMXObject>();

	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"OBJECT_LAYER");
		System.out.println(spacing+"-name      = " + name);
		System.out.println(spacing+"-color     = " + color);
		System.out.println(spacing+"-opacity   = " + opacity);
		System.out.println(spacing+"-visible   = " + visible);
		System.out.println(spacing+"-offset    = " + offsetX + "," + offsetY);
		System.out.println(spacing+"-drawOrder = " + drawOrder);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		for(TMXObject o : objects) {
			o.prettyPrint(layer+1);
		}
		
	}
	
	
}
