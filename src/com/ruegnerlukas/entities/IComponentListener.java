package com.ruegnerlukas.entities;



public interface IComponentListener {

	public void onComponentAdded(Entity e, IEntityComponent component);
	
	public void onComponentRemoved(Entity e, IEntityComponent component);
	
}
