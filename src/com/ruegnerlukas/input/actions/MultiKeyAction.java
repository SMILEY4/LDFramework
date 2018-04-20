package com.ruegnerlukas.input.actions;

import com.ruegnerlukas.input.InputManager.InputState;

public class MultiKeyAction extends InputAction {

	

	private final String name;
	private final InputState[] states;
	private final int[] keycodes;
	private final MultiMode mode;
	
	
	
	
	public MultiKeyAction(String name, InputState[] states, int[] keycodes, MultiMode mode) {
		this.name = name;
		this.states = states;
		this.keycodes = keycodes;
		this.mode = mode;
	}
	
	
	public MultiKeyAction(String name, InputState state, int[] keycodes, MultiMode mode) {
		this.name = name;
		this.states = new InputState[keycodes.length];
		for(int i=0; i<states.length; i++) {
			states[i] = state;
		}
		this.keycodes = keycodes;
		this.mode = mode;
	}
	
	
	
	
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
	
	
	public MultiMode getMode() {
		return this.mode;
	}
	
	
	
	
	public InputState[] getStates() {
		return this.states;
	}
	
	
	public int[] getKeyCodes() {
		return this.keycodes;
	}
	
	
	
}
