package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXTerrain {

	public String name;
	public int tile;
	
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		
		System.out.println(spacing+"TERRAIN");
		System.out.println(spacing+"-name = " + name);
		System.out.println(spacing+"-tile = " + tile);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
	}
	
}
