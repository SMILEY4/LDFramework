package com.ruegnerlukas.properties;

import java.awt.Color;

public class ColorProperty extends Property {

	private float r;
	private float g;
	private float b;
	private float a;

	
	
	
	
	
	public ColorProperty(String name) {
		super(name);
	}
	
	public ColorProperty(String name, float r, float g, float b, float a) {
		super(name);
		setRed(r);
		setBlue(b);
		setGreen(g);
		setAlpha(a);
	}
	
	public ColorProperty(String name, String hexString) {
		super(name);
		setHexString(hexString);
	}
	

	
	
	
	
	public void setHexString(String hexString) {
		Color color = Color.decode( (hexString.startsWith("#") ? hexString : "#"+hexString) );
		setRed((float)color.getRed()/255f);
		setGreen((float)color.getGreen()/255f);
		setBlue((float)color.getBlue()/255f);
		setAlpha((float)color.getAlpha()/255f);

	}
	
	public void setRed(float r) {
		this.r = r;
	}
	
	public void setGreen(float g) {
		this.g = g;
	}
	
	public void setBlue(float b) {
		this.b = b;
	}
	
	public void setAlpha(float a) {
		this.a = a;
	}
	
	
	
	
	public float getRed() {
		return this.r;
	}
	
	public float getGreen() {
		return this.g;
	}
	
	public float getBlue() {
		return this.b;
	}
	
	public float getAlpha() {
		return this.a;
	}
	
	
	
}
