package com.ruegnerlukas.tmxloader.tmxObjects;

import com.ruegnerlukas.tmxloader.TMXProperty;

public class TMXText extends TMXObject {

	public String text;
	public String fontfamily;
	public int pixelsize = 16;
	public boolean wrap = false;
	public String color = "#000000";
	public boolean bold;
	public boolean italic;
	public boolean underline;
	public boolean strikeout;
	public boolean kerning;
	public String halign;
	public String valign;
	
	
	
	
	@Override
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"TEXT");
		System.out.println(spacing+"-id         = " + id);
		System.out.println(spacing+"-name       = " + name);
		System.out.println(spacing+"-type       = " + type);
		System.out.println(spacing+"-position   = " + x + "," + y);
		System.out.println(spacing+"-size       = " + width + "x" + height);
		System.out.println(spacing+"-rotation   = " + rotation);
		System.out.println(spacing+"-gid        = " + gid);
		System.out.println(spacing+"-visible    = " + visible);
		System.out.println(spacing+"-text       = " + text);
		System.out.println(spacing+"-fontfamily = " + fontfamily);
		System.out.println(spacing+"-pixelsize  = " + pixelsize);
		System.out.println(spacing+"-wrap       = " + wrap);
		System.out.println(spacing+"-color      = " + color);
		System.out.println(spacing+"-bold       = " + bold);
		System.out.println(spacing+"-italic     = " + italic);
		System.out.println(spacing+"-underline  = " + underline);
		System.out.println(spacing+"-strikeout  = " + strikeout);
		System.out.println(spacing+"-kerning    = " + kerning);
		System.out.println(spacing+"-halign     = " + halign);
		System.out.println(spacing+"-valign     = " + valign);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		
	}
	
	
}