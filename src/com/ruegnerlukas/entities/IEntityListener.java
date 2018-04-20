package com.ruegnerlukas.entities;



public interface IEntityListener {

	public void onEntityAdded(Entity e);
	
	public void onEntityRemoveRequested(Entity e);
	public void onEntityRemoved(Entity e);
	
}
