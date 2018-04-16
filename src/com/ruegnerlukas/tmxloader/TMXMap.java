package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.tmxLayers.TMXLayer;

public class TMXMap {

	// map
	public String version;
	public String tiledversion;
	public String orientation;
	public String renderorder = "right-down";
	public int width;
	public int height;
	public int tilewidth;
	public int tileheight;
	public int hexsidelength;
	public String staggeraxis;
	public String staggerindex;
	public String backgroundcolor;
	public int nextobjectid;
	
	// properties
	public List<TMXProperty> properties = new ArrayList<TMXProperty>();
	
	// tilesets
	public List<TMXTileset> tilesets = new ArrayList<TMXTileset>();

	// layers
	public List<TMXLayer> layers = new ArrayList<TMXLayer>();


	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"MAP");
		System.out.println(spacing+"-version       = " + version);
		System.out.println(spacing+"-tiledversion  = " + tiledversion);
		System.out.println(spacing+"-orientation   = " + orientation);
		System.out.println(spacing+"-renderorder   = " + renderorder);
		System.out.println(spacing+"-size          = " + width + "x" + height);
		System.out.println(spacing+"-tilesize      = " + tilewidth + "x" + tileheight);
		System.out.println(spacing+"-hexsidelength = " + hexsidelength);
		System.out.println(spacing+"-staggeraxis   = " + staggeraxis);
		System.out.println(spacing+"-staggerindex  = " + staggerindex);
		System.out.println(spacing+"-backgroundClr = " + backgroundcolor);
		System.out.println(spacing+"-nextobjectid  = " + nextobjectid);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		for(TMXTileset t : tilesets) {
			t.prettyPrint(layer+1);
		}
		for(TMXLayer l : layers) {
			l.prettyPrint(layer+1);
		}

		
	}
	
	
	
}




