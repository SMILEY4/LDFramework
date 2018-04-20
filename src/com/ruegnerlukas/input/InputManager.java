package com.ruegnerlukas.input;

import java.util.HashMap;
import java.util.Map;

import com.ruegnerlukas.input.actions.InputAction;
import com.ruegnerlukas.input.actions.InputAction.MultiMode;
import com.ruegnerlukas.input.actions.KeyAction;
import com.ruegnerlukas.input.actions.MouseAction;
import com.ruegnerlukas.input.actions.MouseWheelAction;
import com.ruegnerlukas.input.actions.MouseWheelAction.WheelDirection;
import com.ruegnerlukas.input.actions.MultiKeyAction;
import com.ruegnerlukas.input.actions.MultiMouseAction;

public class InputManager {

	
	public static enum InputState {
		UP,
		PRESSED,
		DOWN,
		RELEASED
	}
	
	
	
	
	
	
	private Map<String, InputAction> actions;
	private InputReciever reciever;
	
	private InputState[] keyStates;
	private InputState[] mouseStates;
	private int mouseWheelScrollAmount;

	
	public InputManager() {
		actions = new HashMap<String, InputAction>();
		
		keyStates = new InputState[256];
		for(int i=0; i<keyStates.length; i++) {
			keyStates[i] = InputState.UP;
		}
		
		mouseStates = new InputState[3];
		for(int i=0; i<mouseStates.length; i++) {
			mouseStates[i] = InputState.UP;
		}
		
	}
	
	
	
	
	
	
	public void update() {
		
		if(reciever == null) {
			return;
		}
		
		// mouse states
		for(int i=0; i<mouseStates.length; i++) {
			InputState lastState = mouseStates[i];
			boolean isDown = reciever.isMouseDown(i);
			
			if(lastState == InputState.UP) {
				if(isDown) {
					mouseStates[i] = InputState.PRESSED;
				} else {
					mouseStates[i] = InputState.UP;
				}
				
			} else if(lastState == InputState.PRESSED) {
				if(isDown) {
					mouseStates[i] = InputState.DOWN;
				} else {
					mouseStates[i] = InputState.RELEASED;
				}
				
			} else if(lastState == InputState.DOWN) {
				if(isDown) {
					mouseStates[i] = InputState.DOWN;
				} else {
					mouseStates[i] = InputState.RELEASED;
				}
				
			} else if(lastState == InputState.RELEASED) {
				if(isDown) {
					mouseStates[i] = InputState.PRESSED;
				} else {
					mouseStates[i] = InputState.UP;
				}
			}
			
		}
		
		mouseWheelScrollAmount = reciever.getMouseWheel();
		
		
		// key states
		for(int i=0; i<keyStates.length; i++) {
		
			InputState lastState = keyStates[i];
			boolean isDown = reciever.isKeyDown(i);
			
			if(lastState == InputState.UP) {
				if(isDown) {
					keyStates[i] = InputState.PRESSED;
				} else {
					keyStates[i] = InputState.UP;
				}
				
			} else if(lastState == InputState.PRESSED) {
				if(isDown) {
					keyStates[i] = InputState.DOWN;
				} else {
					keyStates[i] = InputState.RELEASED;
				}
				
			} else if(lastState == InputState.DOWN) {
				if(isDown) {
					keyStates[i] = InputState.DOWN;
				} else {
					keyStates[i] = InputState.RELEASED;
				}
				
			} else if(lastState == InputState.RELEASED) {
				if(isDown) {
					keyStates[i] = InputState.PRESSED;
				} else {
					keyStates[i] = InputState.UP;
				}
			}
			
		}
		
		
		reciever.updatePost();
	}
	
	
	
	
	public boolean action(String name) {
		InputAction action = actions.get(name);
		if(action == null) {
			return false;
		}
		
		// mouse actions
		if(action instanceof MouseAction) {
			MouseAction mouseAction = (MouseAction)action;
			return mouseStates[mouseAction.getButton()] == mouseAction.getState();
		}
		if(action instanceof MultiMouseAction) {
			MultiMouseAction multiMouseAction = (MultiMouseAction)action;
			
			int nCorrect = 0;

			for(int i=0; i<multiMouseAction.getButtons().length; i++) {
				int button = multiMouseAction.getButtons()[i];
				if(mouseStates[button] == multiMouseAction.getStates()[i]) {
					nCorrect++;
				}
			}
			
			if(multiMouseAction.getMode() == MultiMode.ALL) {
				return nCorrect == multiMouseAction.getButtons().length;
			} else {
				return nCorrect >= 1;
			}
		}
		if(action instanceof MouseWheelAction) {
			MouseWheelAction wheelAction = (MouseWheelAction)action;
			if(wheelAction.getDirection() == WheelDirection.NONE) {
				return mouseWheelScrollAmount == 0;
			} else if(wheelAction.getDirection() == WheelDirection.UP) {
				return mouseWheelScrollAmount < 0;
			} else if(wheelAction.getDirection() == WheelDirection.DOWN) {
				return mouseWheelScrollAmount > 0;
			}
		}
		
		
		// key actions
		if(action instanceof KeyAction) {
			KeyAction keyAction = (KeyAction)action;
			return keyStates[keyAction.getKeyCode()] == keyAction.getState();
		}
		if(action instanceof MultiKeyAction) {
			MultiKeyAction multiKeyAction = (MultiKeyAction)action;
			
			int nCorrect = 0;

			for(int i=0; i<multiKeyAction.getKeyCodes().length; i++) {
				int keyCode = multiKeyAction.getKeyCodes()[i];
				if(keyStates[keyCode] == multiKeyAction.getStates()[i]) {
					nCorrect++;
				}
			}
			
			if(multiKeyAction.getMode() == MultiMode.ALL) {
				return nCorrect == multiKeyAction.getKeyCodes().length;
			} else {
				return nCorrect >= 1;
			}
		}
		
		return false;
	}
	
	
	
	
	public void setReciever(InputReciever reciever) {
		if(this.reciever != null) {
			this.reciever.onRemoveReciever();
		}
		this.reciever = reciever;
		if(this.reciever != null) {
			this.reciever.onAddReciever();
		}
	}
	
	
	
	
	public void addAction(InputAction action) {
		actions.put(action.getName(), action);
	}
	
	
	
	
	public void removeAction(String name) {
		actions.remove(name);
	}
	
	
	
	
	
}
