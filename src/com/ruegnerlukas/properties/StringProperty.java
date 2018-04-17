package com.ruegnerlukas.properties;



public class StringProperty extends Property {

	private String value;
	
	
	
	
	
	
	public StringProperty(String name) {
		super(name);
	}
	

	public StringProperty(String name, String value) {
		super(name);
		this.value = value;
	}
	
	
	
	
	
	
	public void setValue(String value) { 
		this.value = value;
	} 
	
	
	
	
	public String getValue() {
		return this.value;
	}
	
	
	
}
