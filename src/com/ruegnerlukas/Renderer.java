package com.ruegnerlukas;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ruegnerlukas.tilemap.Cell;
import com.ruegnerlukas.tilemap.Layer;
import com.ruegnerlukas.tilemap.Tile;
import com.ruegnerlukas.tilemap.Tilemap;
import com.ruegnerlukas.tileset.Tileset;

public class Renderer {

	
	
	public static void render(int offx, int offy, Graphics2D g, Tilemap map, Tileset set) {
		
		int screenSize = 600;
		
		g.setColor(Color.GRAY);
		
		for(int i=0; i<map.getNumLayers(); i++) {

			Layer layer = map.getLayer(i);
			
			for(int x=0; x<layer.getWidth(); x++) {
				for(int y=0; y<layer.getHeight(); y++) {
					Cell cell = layer.getCell(x, y);
					Tile tile = cell.getTile();
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
						

						int col = id % set.getColumns();
						int row = id / set.getColumns();

						int sx = col * set.getTileWidth() + set.getSpacing() * col;
						int sy = row * set.getTileHeight() + set.getSpacing() * row;

//						g.fillRect(tx+4, ty+4, layer.getCellWidth()-8, layer.getCellHeight()-8);

						g.drawImage(set.getImage(), tx, ty, tx+layer.getCellWidth(), ty+layer.getCellHeight(), sx, sy, sx+set.getTileWidth(), sy+set.getTileHeight(), null);
						
						
					}
					
				}
			}
			
		}
		
		
	}
	
	
	
	
}
