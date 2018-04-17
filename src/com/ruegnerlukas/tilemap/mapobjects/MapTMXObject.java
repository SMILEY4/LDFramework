package com.ruegnerlukas.tilemap.mapobjects;

import java.security.KeyStore.Entry;

import com.ruegnerlukas.properties.BoolProperty;
import com.ruegnerlukas.properties.ColorProperty;
import com.ruegnerlukas.properties.FileProperty;
import com.ruegnerlukas.properties.FloatProperty;
import com.ruegnerlukas.properties.IntProperty;
import com.ruegnerlukas.properties.StringProperty;
import com.ruegnerlukas.tmxloader.TMXProperty;
import com.ruegnerlukas.tmxloader.TMXProperty.TMXPropertyType;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXObject;

public class MapTMXObject extends MapObject {

	private TMXObject object;
	
	
	
	
	
	
	public MapTMXObject(TMXObject object) {
		setTMXObject(object);
	}
	
	
	
	
	
	
	public void setTMXObject(TMXObject object) {
		if(object == null) {
			this.object = null;
			for(TMXProperty tmxProp : this.object.properties) {
				removeProperty(tmxProp.name);
			}
			
		} else {
			for(TMXProperty tmxProp : this.object.properties) {
				removeProperty(tmxProp.name);
			}
			this.object = object;
			for(TMXProperty tmxProperty : object.properties) {
				if(tmxProperty.type == TMXPropertyType.BOOL) {
					addProperty(new BoolProperty(tmxProperty.name, "1".equalsIgnoreCase(tmxProperty.value)));
					continue;
				}
				if(tmxProperty.type == TMXPropertyType.INT) {
					addProperty(new IntProperty(tmxProperty.name, Integer.parseInt(tmxProperty.value)));
					continue;
				}
				if(tmxProperty.type == TMXPropertyType.FLOAT) {
					addProperty(new FloatProperty(tmxProperty.name, Float.parseFloat(tmxProperty.value)));
					continue;
				}
				if(tmxProperty.type == TMXPropertyType.STRING) {
					addProperty(new StringProperty(tmxProperty.name, tmxProperty.value));
					continue;
				}
				if(tmxProperty.type == TMXPropertyType.COLOR) {
					addProperty(new ColorProperty(tmxProperty.name, tmxProperty.value));
					continue;
				}
				if(tmxProperty.type == TMXPropertyType.FILE) {
					addProperty(new FileProperty(tmxProperty.name, tmxProperty.value));
					continue;
				}
			}
		}
	}
	
	
	
	
	public TMXObject getTMXObject() {
		return this.object;
	}
	
	
	
}











