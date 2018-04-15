package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXLayer {

	// layer
	public String name;
	public int x;
	public int y;
	public int width;
	public int height;
	public float opacity;
	public boolean visible;
	public int offsetX;
	public int offsetY;
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
	// data
	public TMXData data;
	
	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"LAYER");
		System.out.println(spacing+"-name    = " + name);
		System.out.println(spacing+"-pos     = " + x + "," + y);
		System.out.println(spacing+"-size    = " + width + "x" + height);
		System.out.println(spacing+"-opacity = " + opacity);
		System.out.println(spacing+"-visible = " + visible);
		System.out.println(spacing+"-offset  = " + offsetX + "," + offsetY);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		if(data != null) data.prettyPrint(layer+1);
		
	}
	
}
