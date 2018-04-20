package com.ruegnerlukas.input.actions;

import com.ruegnerlukas.input.InputManager.InputState;

public class MouseAction extends InputAction {

	
	

	private final String name;
	private final InputState state;
	private final int button;
	
	
	
	
	public MouseAction(String name, InputState state, int button) {
		this.name = name;
		this.state = state;
		this.button = button;
	}
	
	
	
	
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
	public InputState getState() {
		return this.state;
	}
	
	
	public int getButton() {
		return this.button;
	}
	
	
	
}
