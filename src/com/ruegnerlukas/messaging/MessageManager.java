package com.ruegnerlukas.messaging;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

	
	private List<IMessageSystem> systems;
	private Map<String, List<IMessageSystem>> systemMap;
	
	
	
	
	public MessageManager() {
		systems = new ArrayList<IMessageSystem>();
		systemMap = new HashMap<String, List<IMessageSystem>>();
	}
	
	
	
	
	public void sendMessage(IMessage msg, String msgType, IMessageSystem... reciever) {
		for(int i=0; i<reciever.length; i++) {
			boolean consumed = reciever[i].handleMessage(msg, msgType);
			if(consumed) {
				break;
			}
		}
	}
	
	
	
	
	public void sendMessageToAll(IMessage msg, String msgType) {
		for(int i=0; i<systems.size(); i++) {
			boolean consumed = systems.get(i).handleMessage(msg, msgType);
			if(consumed) {
				break;
			}
		}
	}

	
	
	
	public void sendMessage(IMessage msg, String msgType) {
		List<IMessageSystem> systems = systemMap.get(msgType);
		for(int i=0; i<systems.size(); i++) {
			boolean consumed = systems.get(i).handleMessage(msg, msgType);
			if(consumed) {
				break;
			}
		}
	}
	
	
	
	
	public void subscribe(IMessageSystem system, String msgType) {
		List<IMessageSystem> systems = systemMap.get(msgType);
		if(systems == null) {
			systems = new ArrayList<IMessageSystem>();
			systemMap.get(system);
		}
		systems.add(system);
	}
	
	
	
	
	public void unsubscribe(IMessageSystem system, String msgType) {
		List<IMessageSystem> systems = systemMap.get(msgType);
		if(systems != null) {
			systems.remove(system);
		}
	}
	
	
	
	
	public void unsubscribeAll(IMessageSystem system) {
		for(Map.Entry<String, List<IMessageSystem>> entry : systemMap.entrySet()) {
			entry.getValue().remove(system);
		}
	}
	
	
	
	
	public void addSystem(IMessageSystem system) {
		systems.add(system);
	}
	
	
	
	
	public void removeSystem(IMessageSystem system) {
		systems.remove(system);
	}
	
	
	
}
