package com.ruegnerlukas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.ruegnerlukas.simplemath.vectors.vec4.Vector4i;
import com.ruegnerlukas.tilemap.Map;
import com.ruegnerlukas.tilemap.layers.TileLayer;
import com.ruegnerlukas.tilemap.tiles.Tile;
import com.ruegnerlukas.tileset.Tileset;
import com.ruegnerlukas.tileset.TilesetLayer;

public class Renderer {

	
	
	public static void render(int offx, int offy, Graphics2D g, Map map, Tileset set, BufferedImage tilesetImage) {
		
		int screenSize = 600;
		
		g.setColor(Color.GRAY);
		
		for(int i=0; i<map.getNumLayers(); i++) {

			TileLayer layer = (TileLayer)map.getLayer(i);
			
			for(int x=0; x<layer.getWidth(); x++) {
				for(int y=0; y<layer.getHeight(); y++) {
					Tile tile = layer.getTile(x, y);
					if(tile != null) {
						
						// draw tile 
						int tx = x * layer.getCellWidth() - offx;
						int ty = y * layer.getCellHeight() - offy;
						
						if(tx+layer.getCellWidth() < 0 || ty+layer.getCellHeight() < 0 || tx-layer.getCellWidth() > screenSize || ty-layer.getCellHeight() > screenSize) {
							continue;
						}
						
						if(tile.getId() == 0) {
							continue;
						}

						int id = tile.getId()-1;
						
						
						// get uvs from tileset
						TilesetLayer tsLayer = set.getLayer(id);
						if(tsLayer == null) {
							System.out.println(id);
							continue;
						}
						Vector4i tileUV = tsLayer.getTile(id);

						
						if(tile.isFlippedDiagonally()) {
							// possible in java2D ?
						}
						
						if(tile.isFlippedHorizontally()) {
							int tmp = tileUV.x;
							tileUV.x = tileUV.z;
							tileUV.z = tmp;
						}
						
						if(tile.isFlippedVertically()) {
							int tmp = tileUV.y;
							tileUV.y = tileUV.w;
							tileUV.w = tmp;
						}
						
						
						g.drawImage(tilesetImage, tx, ty, tx+layer.getCellWidth(), ty+layer.getCellHeight(), tileUV.x, tileUV.y, tileUV.z, tileUV.w, null);
						
						
					}
					
				}
			}
			
		}
		
		
	}
	
	
	
	
}
