package com.ruegnerlukas.tmxloader;



public class TMXProperty {

	public String name;
	public String type;
	public String value;

	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"PROPERTY");
		System.out.println(spacing+"-name  = " + name);
		System.out.println(spacing+"-type  = " + type);
		System.out.println(spacing+"-value = " + value);

	}

	
}
