package com.ruegnerlukas.input.actions;

import com.ruegnerlukas.input.InputSystem.InputState;

public class MultiMouseAction extends InputAction {

	

	private final String name;
	private final InputState[] states;
	private final int[] buttons;
	private final MultiMode mode;
	
	
	
	
	public MultiMouseAction(String name, InputState[] states, int[] buttons, MultiMode mode) {
		this.name = name;
		this.states = states;
		this.buttons = buttons;
		this.mode = mode;
	}
	
	
	public MultiMouseAction(String name, InputState state, int[] buttons, MultiMode mode) {
		this.name = name;
		this.states = new InputState[buttons.length];
		for(int i=0; i<states.length; i++) {
			states[i] = state;
		}
		this.buttons = buttons;
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
	
	
	
	
	public int[] getButtons() {
		return this.buttons;
	}
	
	
	
}
