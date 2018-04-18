package com.ruegnerlukas.scenes;



public abstract class Scene {

	private boolean isLoaded = false;
	
	protected void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
	protected boolean isLoaded() {
		return this.isLoaded;
	}
	
	
	public abstract void load();
	
	public abstract void update(int deltaMS);
	
	public abstract void unload();
	
}
