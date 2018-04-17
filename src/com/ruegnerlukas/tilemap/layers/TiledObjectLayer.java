package com.ruegnerlukas.tilemap.layers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ruegnerlukas.simplemath.MathUtils;
import com.ruegnerlukas.tilemap.mapobjects.MapObject;
import com.ruegnerlukas.tilemap.tiles.Tile;

public class TiledObjectLayer extends Layer {

	private int width, height;
	private int cellWidth, cellHeight;
	
	private Cell[][] cells;
	private List<MapObject> objects;
	
	
	public TiledObjectLayer(int width, int height, int cellWidth, int cellHeight) {
		this.width = width;
		this.height = height;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		
		cells = new Cell[width][height];
		objects = new ArrayList<MapObject>();
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				cells[x][y] = new Cell(x, y);
			}
		}
		
	}
	
	
	
	public List<MapObject> getObjects(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return cells[x][y].getObjects();
	}
	
	
	
	
	public List<MapObject> getAllObjects() {
		return Collections.unmodifiableList(this.objects);
	}
	
	
	public void addObject(MapObject obj) {
		
		int minCellX = (int)(obj.getBounds().getMinXFloat() / cellWidth);
		int minCellY = (int)(obj.getBounds().getMinYFloat() / cellHeight);
		int maxCellX = (int)(obj.getBounds().getMaxXFloat() / cellWidth);
		int maxCellY = (int)(obj.getBounds().getMaxYFloat() / cellHeight);
		
		minCellX = MathUtils.clamp(minCellX, 0, width-1);
		minCellY = MathUtils.clamp(minCellY, 0, height-1);
		maxCellX = MathUtils.clamp(maxCellX, 0, width-1);
		maxCellY = MathUtils.clamp(maxCellY, 0, height-1);

		for(int x=minCellX; x<=maxCellX; x++) {
			for(int y=minCellY; y<=maxCellY; y++) {
				Cell cell = cells[x][y];
				cell.getObjects().add(obj);
			}
		}
		
		objects.add(obj);
		
	}
	
	
	
	
	public void removeObject(MapObject obj) {
		
		int minCellX = (int)(obj.getBounds().getMinXFloat() / cellWidth);
		int minCellY = (int)(obj.getBounds().getMinYFloat() / cellHeight);
		int maxCellX = (int)(obj.getBounds().getMaxXFloat() / cellWidth);
		int maxCellY = (int)(obj.getBounds().getMaxYFloat() / cellHeight);
		
		minCellX = MathUtils.clamp(minCellX, 0, width-1);
		minCellY = MathUtils.clamp(minCellY, 0, height-1);
		maxCellX = MathUtils.clamp(maxCellX, 0, width-1);
		maxCellY = MathUtils.clamp(maxCellY, 0, height-1);

		for(int x=minCellX; x<=maxCellX; x++) {
			for(int y=minCellY; y<=maxCellY; y++) {
				Cell cell = cells[x][y];
				cell.getObjects().remove(obj);
			}
		}
		
		objects.remove(obj);

	}
	
	
	
	
	public void removeObjectComplete(MapObject obj) {
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				Cell cell = cells[x][y];
				cell.getObjects().remove(obj);
			}
		}
		
		objects.remove(obj);
	}
	
	
	
	
	public void collectObjects(List<MapObject> list, int ax, int ay, int aWidth, int aHeight) {
		
		int minCellX = (int)(ax / cellWidth);
		int minCellY = (int)(ay / cellHeight);
		int maxCellX = (int)(ax+aWidth / cellWidth);
		int maxCellY = (int)(ay+aHeight / cellHeight);
		
		minCellX = MathUtils.clamp(minCellX, 0, width-1);
		minCellY = MathUtils.clamp(minCellY, 0, height-1);
		maxCellX = MathUtils.clamp(maxCellX, 0, width-1);
		maxCellY = MathUtils.clamp(maxCellY, 0, height-1);
		
		for(int x=minCellX; x<=maxCellX; x++) {
			for(int y=minCellY; y<=maxCellY; y++) {
				Cell cell = cells[x][y];
				list.addAll(cell.getObjects());
			}
		}
		
	}
	
	
}








class Cell {
	
	public final int X;
	public final int Y;
	
	private List<MapObject> objects;
	
	
	
	public Cell(int x, int y) {
		this.X = x;
		this.Y = y;
		objects = new ArrayList<MapObject>();
	}

	
	
	
	public List<MapObject> getObjects() {
		return this.objects;
	}
	
}




