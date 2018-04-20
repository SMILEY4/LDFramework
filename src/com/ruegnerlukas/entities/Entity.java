package com.ruegnerlukas.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
	
	
	private Map<Class<? extends IEntityComponent>, IEntityComponent> components;
	
	private List<IEntityComponent> componentList;
	private List<Class<? extends IEntityComponent>> componentTypeList;

	private List<IComponentListener> componentListeners;
	
	private EntityManager manager;
	
	
	
	public Entity() {
		components = new HashMap<Class<? extends IEntityComponent>, IEntityComponent>();
		componentList = new ArrayList<IEntityComponent>();
		componentTypeList = new ArrayList<Class<? extends IEntityComponent>>();
		componentListeners = new ArrayList<IComponentListener>(1);
	}
	
	
	
	
	
	
	
	public void addComponent(IEntityComponent component) {
		if(components.containsKey(component.getClass())) {
			removeComponent(component.getClass());
		}
		components.put(component.getClass(), component);
		componentList.add(component);
		componentTypeList.add(component.getClass());
	} 

	
	
	
	public IEntityComponent getComponent(Class<? extends IEntityComponent> componentType) {
		return components.get(componentType);
	}
	
	
	
	
	public List<IEntityComponent> getAllComponents() {
		return Collections.unmodifiableList(componentList);
	} 
	
	
	
	
	public List<Class<? extends IEntityComponent>> getAllComponentTypes() {
		return Collections.unmodifiableList(componentTypeList);
	} 
	
	
	
	
	public boolean hasComponent(Class<? extends IEntityComponent> componentType) {
		return components.containsKey(componentType);
	}

	
	
	
	public void removeComponent(Class<? extends IEntityComponent> componentType) {
		if(components.containsKey(componentType)) {
			for(int i=0; i<componentList.size(); i++) {
				if(componentList.get(i).getClass() == componentType) {
					componentList.remove(i);
					break;
				}
			}
			componentTypeList.remove(componentType);
			components.remove(componentType);
		}
	}
	
	
	
	
	public void removeAllComponents() {
		components.clear();
		componentList.clear();
		componentTypeList.clear();
	} 
	
	
	
	
	public void addComponentListener(IComponentListener listener) {
		this.componentListeners.add(listener);
	}
	
	
	
	
	public void removeComponentListener(IComponentListener listener) {
		this.componentListeners.remove(listener);
	}
	
	
	
	
	protected void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	
	
	
	public EntityManager getManager() {
		return this.manager;
	}
	
	
	
}













