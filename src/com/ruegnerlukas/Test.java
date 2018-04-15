package com.ruegnerlukas;

import java.io.File;

import com.ruegnerlukas.tilemap.Layer;
import com.ruegnerlukas.tilemap.Tilemap;
import com.ruegnerlukas.tmxloader.TMXLoader;
import com.ruegnerlukas.tmxloader.TMXMap;

public class Test {

	public static void main(String[] args) {
		
		TMXMap tmxMap = TMXLoader.loadTMX(new File("res/unbenannt3.tmx"));
		tmxMap.prettyPrint(0);
		
		
		Tilemap map = new Tilemap();
		Layer layer = new Layer(80, 48, 32, 32);
		
		
		
	}
	
}
