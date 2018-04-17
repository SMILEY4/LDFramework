package com.ruegnerlukas.properties;



public class BoolProperty extends Property {

	private boolean value;
	
	
	
	
	
	
	public BoolProperty(String name) {
		super(name);
	}
	

	public BoolProperty(String name, boolean value) {
		super(name);
		this.value = value;
	}
	
	
	
	
	
	
	public void setValue(boolean value) { 
		this.value = value;
	} 
	
	
	
	
	public boolean getValue() {
		return this.value;
	}
	
	
	
}
