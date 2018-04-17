package com.ruegnerlukas.tmxloader.tmxLayers;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.tmxloader.TMXProperty;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXObject;

public class TMXGroupLayer extends TMXLayer {

	public int offsetX = 0;
	public int offsetY = 0;
	
	// layers
	public List<TMXLayer> layers = new ArrayList<TMXLayer>();
	
	
	
	
	
	@Override
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"GROUP_LAYER");
		System.out.println(spacing+"-name      = " + name);
		System.out.println(spacing+"-opacity   = " + opacity);
		System.out.println(spacing+"-visible   = " + visible);
		System.out.println(spacing+"-offset    = " + offsetX + "," + offsetY);

		for(TMXProperty p : properties) {
			p.prettyPrint(layer+1);
		}
		
		for(TMXLayer l : layers) {
			l.prettyPrint(layer+1);
		}
		
	}

}
