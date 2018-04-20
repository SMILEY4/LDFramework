package com.ruegnerlukas.input.actions;

import com.ruegnerlukas.input.InputManager.InputState;

public class MouseWheelAction extends InputAction {

	public static enum WheelDirection {
		NONE,
		UP,  // -1
		DOWN //  1
	}
	

	private final String name;
	private final WheelDirection direction;
	
	
	public MouseWheelAction(String name, WheelDirection direction) {
		this.name = name;
		this.direction = direction;
	}
	
	
	
	
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
	
	
	public WheelDirection getDirection() {
		return this.direction;
	}
	
	
}
