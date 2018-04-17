package com.ruegnerlukas.tileset;

import java.util.HashMap;

import com.ruegnerlukas.properties.Property;
import com.ruegnerlukas.simplemath.vectors.vec4.Vector4i;
import com.ruegnerlukas.tilemap.Map;

public class TilesetLayer {

	
	private int firstID;
	private int tilewidth;
	private int tileheight;
	private int spacing;
	private int margin;
	private int columns;
	private int tileCount;
	
	private HashMap<Integer, TileAnimationData> animationData;
	private HashMap<Integer, HashMap<String, Property>> tileProperties;
	
	private HashMap<String, Property> properties;

	
	
	
	
	public TilesetLayer() {
		animationData = new HashMap<Integer, TileAnimationData>();
	}
	
	
	
	
	
	
	public Property getProperty(String name) {
		if(properties == null) {
			return null;
		}
		return properties.get(name);
	}
	
	
	
	
	public void addProperty(Property prop) {
		if(properties == null) {
			properties = new HashMap<String, Property>();
		}
		properties.put(prop.getName(), prop);
	}
	
	
	
	
	public void removeProperty(String name) {
		if(properties == null) {
			return;
		}
		properties.remove(name);
	}
	
	
	
	
	public Property getTileProperty(int id, String name) {
		if(tileProperties != null) {
			HashMap<String, Property> properties = tileProperties.get(id);
			return properties.get(name);
		}
		return null;
	}
	
	
	
	
	public void addTileProperty(int id, Property prop) {
		if(tileProperties == null) {
			tileProperties = new HashMap<Integer, HashMap<String, Property>>();
		}
		HashMap<String, Property> properties = tileProperties.get(id);
		if(properties == null) {
			properties = new HashMap<String, Property>();
			tileProperties.put(id, properties);
		}
		properties.put(prop.getName(), prop);
	}
	
	
	
	
	public void removeTileProperty(int id, String name) {
		if(tileProperties == null) {
			return;
		}
		HashMap<String, Property> properties = tileProperties.get(id);
		if(properties == null) {
			return;
		}
		properties.remove(name);
	}
	
	
	
	
	public void addTileAnimationData(int localID, TileAnimationData info) {
		animationData.put(localID, info);
	}
	
	
	
	
	
	public Vector4i getTile(int id) {
		Vector4i uv = new Vector4i();
		getTile(id, uv);
		return uv;
	}
	
	
	
	
	public void getTile(int id, Vector4i uvOut) {
		
		int localID = id - firstID;
		
		int col = localID % columns;
		int row = localID / columns;
		
		int x = col * tilewidth  + col * spacing + margin;
		int y = row * tileheight + row * spacing+ margin;
		
		uvOut.set(x, y, x+tilewidth, y+tileheight);
		
	}
	
	
	
	
	public Vector4i getTile(int id, int frame) {
		Vector4i uv = new Vector4i();
		getTile(id, frame, uv);
		return uv;
	}
	
	
	
	
	public void getTile(int id, int frame, Vector4i uvOut) {
		
		int localID = id - firstID;
		
		TileAnimationData animData = animationData.get(localID);
		if(animData != null) {
			localID = animData.getLocalID(frame);
		}

		int col = localID % columns;
		int row = localID / columns;
		
		int x = col * tilewidth  + col * spacing + margin;
		int y = row * tileheight + row * spacing+ margin;
		
		uvOut.set(x, y, x+tilewidth, y+tileheight);
		
		
	}
	
	
	
	
	public int getFrameDuration(int id, int frame) {
		
		int localID = id - firstID;
		
		TileAnimationData animData = animationData.get(localID);
		if(animData != null) {
			return animData.getFrameDuration(frame);
		} else {
			return -1;
		}
		
		
	}


	
	
	public int getFirstID() {
		return firstID;
	}


	
	
	public void setFirstID(int firstID) {
		this.firstID = firstID;
	}


	
	
	public int getTilewidth() {
		return tilewidth;
	}


	
	
	public void setTilewidth(int tilewidth) {
		this.tilewidth = tilewidth;
	}


	
	
	public int getTileheight() {
		return tileheight;
	}


	
	
	public void setTileheight(int tileheight) {
		this.tileheight = tileheight;
	}




	public int getSpacing() {
		return spacing;
	}



	
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}


	
	
	public int getMargin() {
		return margin;
	}


	
	
	public void setMargin(int margin) {
		this.margin = margin;
	}


	
	
	public int getColumns() {
		return columns;
	}


	
	
	public void setColumns(int columns) {
		this.columns = columns;
	}


	
	
	public int getTileCount() {
		return tileCount;
	}



	
	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}




	public HashMap<Integer, HashMap<String, Property>> getTileProperties() {
		return tileProperties;
	}


	
	
	public void setTileProperties(HashMap<Integer, HashMap<String, Property>> tileProperties) {
		this.tileProperties = tileProperties;
	}
	
	
	
}
