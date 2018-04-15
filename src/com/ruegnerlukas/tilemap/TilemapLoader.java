package com.ruegnerlukas.tilemap;

import com.ruegnerlukas.tmxloader.TMXLayer;
import com.ruegnerlukas.tmxloader.TMXMap;

public class TilemapLoader {

	public static Tilemap createTilemap(TMXMap tmxMap) {
		
		Tilemap map = new Tilemap();
	
		
		for(int i=0; i<tmxMap.layers.size(); i++) {
			
			TMXLayer tmxLayer = tmxMap.layers.get(i);
			Layer layer = new Layer(tmxLayer.width, tmxLayer.height, tmxMap.tilewidth, tmxMap.tileheight);

			String[] cvsData = tmxLayer.data.data.split(",");
			for(int j=0; j<cvsData.length; j++) {
				cvsData[j] = cvsData[j].replaceAll(System.lineSeparator(), "").replaceAll("\n", "");
			}
			
			int cvsIndex = 0;
			for(int y=0; y<layer.getHeight(); y++) {
				for(int x=0; x<layer.getWidth(); x++) {
					Tile tile = new Tile(Integer.parseInt(cvsData[cvsIndex++]));
					layer.getCell(x, y).setTile(tile);
				}
				
			}
			
			map.addLayer(layer);
		}
		
		
		return map;
	}
	
	
}
