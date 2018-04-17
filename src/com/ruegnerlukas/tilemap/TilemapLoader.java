package com.ruegnerlukas.tilemap;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.properties.BoolProperty;
import com.ruegnerlukas.properties.ColorProperty;
import com.ruegnerlukas.properties.FileProperty;
import com.ruegnerlukas.properties.FloatProperty;
import com.ruegnerlukas.properties.IntProperty;
import com.ruegnerlukas.properties.StringProperty;
import com.ruegnerlukas.tilemap.layers.Layer;
import com.ruegnerlukas.tilemap.layers.TileLayer;
import com.ruegnerlukas.tilemap.tiles.Tile;
import com.ruegnerlukas.tmxloader.TMXMap;
import com.ruegnerlukas.tmxloader.TMXProperty;
import com.ruegnerlukas.tmxloader.TMXProperty.TMXPropertyType;
import com.ruegnerlukas.tmxloader.TMXUtils;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXGroupLayer;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXLayer;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXObjectLayer;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXTileLayer;

public class TilemapLoader {

	public static Map createTilemap(TMXMap tmxMap) {
		
		// create map
		Map map = new Map();
	
		// load properties
		List<TMXProperty> tmxMapProperties = tmxMap.properties;
		for(TMXProperty tmxProperty : tmxMapProperties) {
			map.addProperty(TMXUtils.convertTMXProperty(tmxProperty));
		}
		
		
		// flatten tmxlayer structure
		List<TMXLayer> tmxLayers = new ArrayList<TMXLayer>();
		flattenTMXLayers(tmxLayers, tmxMap.layers);
		
		
		// create tilemap layers
		for(int i=0; i<tmxLayers.size(); i++) {
			TMXLayer tmxLayer = tmxLayers.get(i);
			
			Layer layer = null;
			
			// TILE LAYER
			if(tmxLayer instanceof TMXTileLayer) {
				TMXTileLayer tmxTileLayer = (TMXTileLayer)tmxLayers.get(i);
				
				// create layer
				TileLayer tileLayer = new TileLayer(tmxTileLayer.width, tmxTileLayer.height, tmxMap.tilewidth, tmxMap.tileheight);

				// get cvsData
				String[] cvsData = tmxTileLayer.data.data.split(",");
				for(int j=0; j<cvsData.length; j++) {
					cvsData[j] = cvsData[j].replaceAll(System.lineSeparator(), "").replaceAll("\n", "");
				}
				
				// set tiles
				int cvsIndex = 0;
				for(int y=0; y<tileLayer.getHeight(); y++) {
					for(int x=0; x<tileLayer.getWidth(); x++) {
						
						long rawID = Long.parseLong(cvsData[cvsIndex++]);
						
						long bit32 = ((rawID >> 31) & 1); // hFlip
						long bit31 = ((rawID >> 30) & 1); // vFlip
						long bit30 = ((rawID >> 29) & 1); // dFLip

						int id = (int)(rawID & 0b11111111111111111111111111111);
						
						Tile tile = tileLayer.getTile(x, y);
						tile.setID(id);
						tile.setFlippedHorizontally(bit32==1);
						tile.setFlippedVertically(bit31==1);
						tile.setFlippedDiagonally(bit30==1);
					}
					
				}
				
				layer = tileLayer;
				
			}
			
			
			// OBJECT LAYER
			if(tmxLayer instanceof TMXObjectLayer) {
				// load obj layer
			}
			
			
			if(layer == null) {
				continue;
			}
			
			
			// add layer
			map.addLayer(layer);
			
			
			// load properties
			List<TMXProperty> tmxLayerProperties = tmxLayer.properties;
			for(TMXProperty tmxProperty : tmxLayerProperties) {
				layer.addProperty(TMXUtils.convertTMXProperty(tmxProperty));
			}
		}
		
		return map;
	}
	
	
	
	
	private static void flattenTMXLayers(List<TMXLayer> layersOut, List<TMXLayer> layersIn) {
		for(int i=0; i<layersIn.size(); i++) {
			TMXLayer tmxLayer = layersIn.get(i);
			if(tmxLayer instanceof TMXGroupLayer) {
				TMXGroupLayer tmxGroup = (TMXGroupLayer)tmxLayer;
				flattenTMXLayers(layersOut, tmxGroup.layers);
			} else {
				layersOut.add(tmxLayer);
			}
		}
	}
	

	
	
}
