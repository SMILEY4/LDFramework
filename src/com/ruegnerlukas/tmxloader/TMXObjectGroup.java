package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.tmxObjects.TMXObject;

public class TMXObjectGroup implements TMXLayerInt {

	// layer
	public String name;
	public String color;
	public float opacity = 1f;
	public boolean visible = true;
	public int offsetX = 0;
	public int offsetY = 0;
	public String drawOrder = "topdown";
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
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
