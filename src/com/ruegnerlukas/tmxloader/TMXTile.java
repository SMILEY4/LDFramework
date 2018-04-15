package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXTile {

	public int id;
	public String type;
	public String terrain;
	public float probabilty;
	
	public TMXImage image;
	
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();

	public TMXAnimation animation;
	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"TILE:");
		System.out.println(spacing+"-id         = " + id);
		System.out.println(spacing+"-type       = " + type);
		System.out.println(spacing+"-terrain    = " + terrain);
		System.out.println(spacing+"-probabilty = " + probabilty);

		
		if(image != null) image.prettyPrint(layer+1);
		
		if(animation != null) animation.prettyPrint(layer+1);
		
		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}

	}
	
}
