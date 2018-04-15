package com.ruegnerlukas.tmxloader;



public class TMXFrame {

	public int tileID;
	public int duration;
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"FRAME");
		System.out.println(spacing+"-tileid   = " + tileID);
		System.out.println(spacing+"-duration = " + duration);

	}
	
}
