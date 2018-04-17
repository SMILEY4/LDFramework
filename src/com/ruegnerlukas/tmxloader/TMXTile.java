package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.tmxObjects.TMXObject;

public class TMXTile {

	public int id;
	public String type;
	public String terrain;
	public float probabilty = 1.0f;
	
	public TMXImage image;
	
	public List<TMXObject> objects = new ArrayList<TMXObject>();
	
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
		
		for(TMXObject o : objects) {
			o.prettyPrint(layer+1);
		}
		
		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}

	}
	
}
