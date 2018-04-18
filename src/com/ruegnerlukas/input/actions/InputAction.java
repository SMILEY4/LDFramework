package com.ruegnerlukas.input.actions;



public abstract class InputAction {

	public static enum MultiMode {
		ALL, ONE
	}
	
	public abstract String getName();
	
}
