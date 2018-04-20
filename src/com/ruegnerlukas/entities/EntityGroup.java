package com.ruegnerlukas.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup implements IEntityListener, IComponentListener {

	
	private List<Class<? extends IEntityComponent>> componentsMaxOne;	// all entities must have no more than one component of this list
	private List<Class<? extends IEntityComponent>> componentsMinOne;	// all entities must have at least one component of this list
	private List<Class<? extends IEntityComponent>> componentsNone;		// all entities must not have any component of this list
	private List<Class<? extends IEntityComponent>> componentsAll;		// all entities must contain all components of this list 
																		// order of checks: order: all, none, min, max
	private List<Entity> entities;
	
	private List<IEntityListener> entityListeners;

	
	
	
	public EntityGroup() {
		componentsMaxOne = new ArrayList<Class<? extends IEntityComponent>>();
		componentsMinOne = new ArrayList<Class<? extends IEntityComponent>>();
		componentsNone = new ArrayList<Class<? extends IEntityComponent>>();
		componentsAll = new ArrayList<Class<? extends IEntityComponent>>();
		entities = new ArrayList<Entity>();
		entityListeners = new ArrayList<IEntityListener>();
	}
	
	
	
	
	
	public boolean addEntity(Entity e) {
		if(checkEntity(e)) {
			entities.add(e);
			for(int i=0; i<entityListeners.size(); i++) {
				entityListeners.get(i).onEntityAdded(e);
			}
			e.addComponentListener(this);
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	public void removeEntity(Entity e) {
		entities.remove(e);
		for(int i=0; i<entityListeners.size(); i++) {
			entityListeners.get(i).onEntityRemoved(e);
		}
		e.removeComponentListener(this);
	}
	
	
	
	
	public boolean checkEntity(Entity e) {
		
		// check "ALL"
		for(int i=0; i<componentsAll.size(); i++) {
			if(!e.hasComponent(componentsAll.get(i))) {
				return false;
			}
		}
		
		// check "NONE"
		for(int i=0; i<componentsNone.size(); i++) {
			if(e.hasComponent(componentsNone.get(i))) {
				return false;
			}
		}
		
		// check "MIN ONE"
		boolean minOne = false;
		for(int i=0; i<componentsMinOne.size(); i++) {
			if(e.hasComponent(componentsMinOne.get(i))) {
				minOne = true;
				break;
			}
		}
		if(!minOne) {
			return false;
		}
		
		// check "MAX ONE"
		int nOfMax = 0;
		for(int i=0; i<componentsMaxOne.size(); i++) {
			if(e.hasComponent(componentsMaxOne.get(i))) {
				nOfMax++;
			}
		}
		if(nOfMax > 1) {
			return false;
		}
		
		// all test passed
		return true;
	}
	
	
	

	public EntityGroup maxOneOf(Class<? extends IEntityComponent>... componentTypes) {
		for(int i=0; i<componentTypes.length; i++) {
			Class<? extends IEntityComponent> type = componentTypes[i];
			if(!componentsMaxOne.contains(type)) {
				componentsMaxOne.add(type);
			}
		}
		return this;
	}

	
	
	
	public EntityGroup minOneOf(Class<? extends IEntityComponent>... componentTypes) {
		for(int i=0; i<componentTypes.length; i++) {
			Class<? extends IEntityComponent> type = componentTypes[i];
			if(!componentsMinOne.contains(type)) {
				componentsMinOne.add(type);
			}
		}
		return this;
	}

	
	
	
	public EntityGroup noneOf(Class<? extends IEntityComponent>... componentTypes) {
		for(int i=0; i<componentTypes.length; i++) {
			Class<? extends IEntityComponent> type = componentTypes[i];
			if(!componentsNone.contains(type)) {
				componentsNone.add(type);
			}
		}
		return this;
	}

	
	
	public EntityGroup allOf(Class<? extends IEntityComponent>... componentTypes) {
		for(int i=0; i<componentTypes.length; i++) {
			Class<? extends IEntityComponent> type = componentTypes[i];
			if(!componentsAll.contains(type)) {
				componentsAll.add(type);
			}
		}
		return this;
	}
	
	
	
	
	public void addEntityListener(IEntityListener listener) {
		this.entityListeners.add(listener);
	}
	
	
	
	
	public void removeEntityListener(IEntityListener listener) {
		this.entityListeners.remove(listener);
	}




	@Override
	public void onEntityRemoved(Entity e) {
		this.entities.remove(e);
	}
	
	
	
	
	@Override
	public void onComponentAdded(Entity e, IEntityComponent component) {
		if(!checkEntity(e)) {
			removeEntity(e);
		}
	}



	
	@Override
	public void onComponentRemoved(Entity e, IEntityComponent component) {
		if(!checkEntity(e)) {
			removeEntity(e);
		}
	}
	
	
	
	
	@Override public void onEntityAdded(Entity e) {}
	@Override public void onEntityRemoveRequested(Entity e) {}





	
	
}
