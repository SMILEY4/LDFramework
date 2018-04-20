package com.ruegnerlukas.entities;



public abstract class EntitySystem {

	
	private boolean active = true;
	public final int PRIORITY;
	
	
	
	
	public EntitySystem(int priority) {
		this.PRIORITY = priority;
	}
	
	
	
	
	
	
	public abstract void onCreate(EntityManager manager);
	
	
	
	
	public abstract void onRemove();
	
	
	
	
	public abstract void update(int deltaMS);
	
	
	
	
	public abstract void onEntityAdded(Entity e);
	
	
	
	
	public abstract void onEntityRemoved(Entity e);
	
	
	
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
	public boolean isActive() {
		return this.active;
	}
	
	
	
}
