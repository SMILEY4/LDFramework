package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXAnimation {

	public List<TMXFrame> frames = new ArrayList<TMXFrame>();
	
	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"ANIMATION");
		
		for(TMXFrame f : frames) {
			f.prettyPrint(layer+1);
		}

	}
	
	
}
