package com.ruegnerlukas.messaging;



public interface IMessageSystem {

	// return true to consume message
	public boolean handleMessage(IMessage msg, String type);
	
}
