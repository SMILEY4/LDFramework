package com.ruegnerlukas.properties;



public class IntProperty extends Property {

	private int value;
	
	
	
	
	
	
	public IntProperty(String name) {
		super(name);
	}
	

	public IntProperty(String name, int value) {
		super(name);
		this.value = value;
	}
	
	
	
	
	
	
	public void setValue(int value) { 
		this.value = value;
	} 
	
	
	
	
	public int getValue() {
		return this.value;
	}
	
	
	
}
