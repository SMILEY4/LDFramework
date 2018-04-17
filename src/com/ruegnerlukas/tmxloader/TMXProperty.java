package com.ruegnerlukas.tmxloader;



public class TMXProperty {

	public static enum TMXPropertyType {
		STRING,
		INT,
		FLOAT,
		BOOL,
		COLOR,
		FILE;
		
		public static TMXPropertyType getType(String strType) {
			if("string".equalsIgnoreCase(strType)) {
				return STRING;
			}
			if("int".equalsIgnoreCase(strType)) {
				return INT;
			}
			if("float".equalsIgnoreCase(strType)) {
				return FLOAT;
			}
			if("bool".equalsIgnoreCase(strType)) {
				return BOOL;
			}
			if("color".equalsIgnoreCase(strType)) {
				return COLOR;
			}
			if("file".equalsIgnoreCase(strType)) {
				return FILE;
			}
			return null;
		}
	}
	
	
	public String name;
	public TMXPropertyType type;
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
