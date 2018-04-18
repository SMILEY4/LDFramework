package com.ruegnerlukas.input;



public interface InputReciever {

	public void onAddReciever();
	
	public void onRemoveReciever();
	
	public void updatePost();
	
	public boolean isKeyDown(int keycode);
	
	public boolean isMouseDown(int button);
	
	public int getMouseWheel();
	
	
}
