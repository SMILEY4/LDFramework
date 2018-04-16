package com.ruegnerlukas.tmxloader.tmxObjects;

import com.ruegnerlukas.tmxloader.TMXProperty;

public class TMXRectangle extends TMXObject {

	@Override
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"RECTANGLE");
		System.out.println(spacing+"-id       = " + id);
		System.out.println(spacing+"-name     = " + name);
		System.out.println(spacing+"-type     = " + type);
		System.out.println(spacing+"-position = " + x + "," + y);
		System.out.println(spacing+"-size     = " + width + "x" + height);
		System.out.println(spacing+"-rotation = " + rotation);
		System.out.println(spacing+"-gid      = " + gid);
		System.out.println(spacing+"-visible  = " + visible);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		
	}
	
}
