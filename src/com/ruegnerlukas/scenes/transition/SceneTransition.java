package com.ruegnerlukas.scenes.transition;



public abstract class SceneTransition {

	public abstract void onStart();
	
	public abstract void update(int deltaMS);
	
	public abstract boolean isDone();
	
	
}
