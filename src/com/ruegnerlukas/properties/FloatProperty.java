package com.ruegnerlukas.properties;



public class FloatProperty extends Property {

	private float value;
	
	
	
	
	
	
	public FloatProperty(String name) {
		super(name);
	}
	

	public FloatProperty(String name, float value) {
		super(name);
		this.value = value;
	}
	
	
	
	
	
	
	public void setValue(float value) { 
		this.value = value;
	} 
	
	
	
	
	public float getValue() {
		return this.value;
	}
	
	
	
}
