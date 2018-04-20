package com.ruegnerlukas.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntityManager {

	
	private List<Entity> entities;
	private List<Entity> toRemove;

	private List<IEntityListener> entityListeners;

	private List<EntityGroup> groups;
	
	private List<EntitySystem> systems;
	private Comparator<EntitySystem> systemComp;
	
	
	
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
		entityListeners = new ArrayList<IEntityListener>();
		systems = new ArrayList<EntitySystem>();
		systemComp = new Comparator<EntitySystem>() {
			@Override public int compare(EntitySystem a, EntitySystem b) {
				if(a.PRIORITY == b.PRIORITY) {
					return  0;
				} else if(a.PRIORITY > b.PRIORITY) {
					return  1;
				} else {
					return -1;
				}
			}
		};
	}
	
	
	
	
	
	
	public void update(int deltaMS) {
		
		for(int i=0; i<systems.size(); i++) {
			EntitySystem system = systems.get(i);
			system.update(deltaMS);
		}
		
		
		// remove entities
		for(int i=0; i<toRemove.size(); i++) {
			forceRemoveEntity(toRemove.get(i));
		}
		toRemove.clear();
		
	}
	
	
	
	
	public void addEntity(Entity e) {
		e.setManager(this);
		entities.add(e);
		for(int i=0; i<entityListeners.size(); i++) {
			entityListeners.get(i).onEntityAdded(e);
		}
	}
	
	
	
	
	public void removeEntity(Entity e) {
		toRemove.add(e);
		for(int i=0; i<entityListeners.size(); i++) {
			entityListeners.get(i).onEntityRemoveRequested(e);
		}
	}
	
	
	
	
	public void removeAll() {
		toRemove.addAll(entities);
		for(int i=0; i<entityListeners.size(); i++) {
			IEntityListener listener = entityListeners.get(i);
			for(int j=0; j<entities.size(); j++) {
				listener.onEntityRemoveRequested(entities.get(j));
			}
		}
	}
	
	
	
	
	public void forceRemoveEntity(Entity e) {
		for(int i=0; i<entityListeners.size(); i++) {
			entityListeners.get(i).onEntityRemoved(e);
		}
		e.setManager(null);
		entities.remove(e);
	}
	
	
	
	
	public void forceRemoveAll() {
		for(int i=0; i<entityListeners.size(); i++) {
			IEntityListener listener = entityListeners.get(i);
			for(int j=0; j<entities.size(); j++) {
				listener.onEntityRemoved(entities.get(j));
			}
		}
		for(int i=0; i<entities.size(); i++) {
			entities.get(i).setManager(null);
		}
		entities.clear();
	}
	
	
	
	
	public void registerGroup(EntityGroup group) {
		this.groups.add(group);
		this.addEntityListener(group);
	}
	
	
	
	
	public void removeGroup(EntityGroup group) {
		this.groups.remove(group);
		this.entityListeners.remove(group);
	}
	
	
	
	
	public void addEntityListener(IEntityListener listener) {
		this.entityListeners.add(listener);
	}
	
	
	
	
	public void removeEntityListener(IEntityListener listener) {
		this.entityListeners.remove(listener);
	}
	
	
	
	
	public void addSystem(EntitySystem system) {
		this.systems.add(system);
		Collections.sort(this.systems, this.systemComp);
	}
	
	
	
	
	public void removeSystem(EntitySystem system) {
		this.systems.remove(system);
	}
	
}
