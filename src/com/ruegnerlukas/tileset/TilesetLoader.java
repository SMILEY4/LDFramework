package com.ruegnerlukas.tileset;

import java.util.ArrayList;
import java.util.List;

import com.ruegnerlukas.properties.BoolProperty;
import com.ruegnerlukas.properties.ColorProperty;
import com.ruegnerlukas.properties.FileProperty;
import com.ruegnerlukas.properties.FloatProperty;
import com.ruegnerlukas.properties.IntProperty;
import com.ruegnerlukas.properties.StringProperty;
import com.ruegnerlukas.tmxloader.TMXAnimation;
import com.ruegnerlukas.tmxloader.TMXFrame;
import com.ruegnerlukas.tmxloader.TMXProperty;
import com.ruegnerlukas.tmxloader.TMXTile;
import com.ruegnerlukas.tmxloader.TMXTileset;
import com.ruegnerlukas.tmxloader.TMXUtils;
import com.ruegnerlukas.tmxloader.TMXProperty.TMXPropertyType;

public class TilesetLoader {

	
	
	
	public static Tileset createTileset(TMXTileset tmxTileset) {
		List<TMXTileset> tmxTilesets = new ArrayList<TMXTileset>(1);
		tmxTilesets.add(tmxTileset);
		return createTileset(tmxTilesets);
	}
	
	
	
	
	public static Tileset createTileset(List<TMXTileset> tmxTilesets) {
		
		Tileset tileset = new Tileset();
		
		for(int i=0; i<tmxTilesets.size(); i++) {
			TMXTileset tmxTileset = tmxTilesets.get(i);
			
			// general data
			TilesetLayer layer = new TilesetLayer();
			layer.setFirstID(tmxTileset.firstgid);
			layer.setTilewidth(tmxTileset.tilewidth);
			layer.setTileheight(tmxTileset.tileheight);
			layer.setSpacing(tmxTileset.spacing);
			layer.setMargin(tmxTileset.margin);
			layer.setColumns(tmxTileset.columns);
			layer.setTileCount(tmxTileset.tilecount);
			
			// animation data
			for(TMXTile tmxTile : tmxTileset.tiles) {
				if(tmxTile.animation != null) {
					
					TMXAnimation anim = tmxTile.animation;
					TileAnimationData animData = new TileAnimationData();
					animData.setFrameCount(anim.frames.size());
					
					for(int j=0; j<anim.frames.size(); j++) {
						TMXFrame frame = anim.frames.get(j);
						animData.setFrameDuration(j, frame.duration);
						animData.setLocalID(j, frame.tileID);
					}
					
					layer.addTileAnimationData(tmxTile.id, animData);
				}
			}
			
			
			// tile properties
			for(TMXTile tmxTile : tmxTileset.tiles) {
				if(tmxTile.properties.size() > 0) {
					List<TMXProperty> tmxTileProperties = tmxTile.properties;
					for(TMXProperty tmxProperty : tmxTileProperties) {
						layer.addTileProperty(tmxTile.id, TMXUtils.convertTMXProperty(tmxProperty));
					}
				}
			}
			
			
			// layer properties
			List<TMXProperty> tmxMapProperties = tmxTileset.properties;
			for(TMXProperty tmxProperty : tmxMapProperties) {
				layer.addProperty(TMXUtils.convertTMXProperty(tmxProperty));
			}
			
			tileset.addLayer(layer);
			
		}
		
		return tileset;
	}
	
	
	
	
	
	
	
	
}
