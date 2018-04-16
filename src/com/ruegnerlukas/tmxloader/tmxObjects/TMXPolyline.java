package com.ruegnerlukas.tmxloader.tmxObjects;

import com.ruegnerlukas.tmxloader.TMXProperty;

public class TMXPolyline extends TMXObject {

	public float[] points;
	
	
	@Override
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"POLYLINE");
		System.out.println(spacing+"-id       = " + id);
		System.out.println(spacing+"-name     = " + name);
		System.out.println(spacing+"-type     = " + type);
		System.out.println(spacing+"-position = " + x + "," + y);
		System.out.println(spacing+"-rotation = " + rotation);
		System.out.println(spacing+"-gid      = " + gid);
		System.out.println(spacing+"-visible  = " + visible);
		
		String strPoints = "{ ";
		for(int i=0; i<points.length; ) {
			float x = points[i++];
			float y = points[i++];
			if(i >= points.length-2) {
				strPoints += "(" + x + "," + y + ")";
			} else {
				strPoints += "(" + x + "," + y + "), ";
			}
		}
		strPoints += " }";
		
		System.out.println(spacing+"-points  = " + strPoints);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		
	}
	
}