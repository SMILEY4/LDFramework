package com.ruegnerlukas.tmxloader;



public class TMXImage {

	public String source;
	public String format;
	public String trans;
	public int width;
	public int height;
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"IMAGE");
		System.out.println(spacing+"-source = " + source);
		System.out.println(spacing+"-format = " + format);
		System.out.println(spacing+"-trans  = " + trans);
		System.out.println(spacing+"-size   = " + width + "x" + height);

	}
}
