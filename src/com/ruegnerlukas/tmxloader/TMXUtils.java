package com.ruegnerlukas.tmxloader;

import java.util.List;

import com.ruegnerlukas.properties.BoolProperty;
import com.ruegnerlukas.properties.ColorProperty;
import com.ruegnerlukas.properties.FileProperty;
import com.ruegnerlukas.properties.FloatProperty;
import com.ruegnerlukas.properties.IntProperty;
import com.ruegnerlukas.properties.Property;
import com.ruegnerlukas.properties.StringProperty;
import com.ruegnerlukas.simplemath.geometry.shapes.polygon.Polygonf;
import com.ruegnerlukas.simplemath.geometry.shapes.rectangle.Rectanglef;
import com.ruegnerlukas.simplemath.vectors.vec2.Vector2f;
import com.ruegnerlukas.tmxloader.TMXProperty.TMXPropertyType;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXPoint;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXPolygon;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXPolyline;
import com.ruegnerlukas.tmxloader.tmxObjects.TMXRectangle;

public class TMXUtils {

	
	public static boolean hasProperty(String name, List<TMXProperty> properties) {
		for(int i=0; i<properties.size(); i++) {
			String propName = properties.get(i).name;
			if(propName != null && propName.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public static TMXProperty getProperty(String name, List<TMXProperty> properties) {
		for(int i=0; i<properties.size(); i++) {
			String propName = properties.get(i).name;
			if(propName != null && propName.equals(name)) {
				return properties.get(i);
			}
		}
		return null;
	}
	
	
	
	
	public static Property convertTMXProperty(TMXProperty tmxProperty) {
		if(tmxProperty.type == TMXPropertyType.BOOL) {
			return new BoolProperty(tmxProperty.name, "1".equalsIgnoreCase(tmxProperty.value));
		}
		if(tmxProperty.type == TMXPropertyType.INT) {
			return new IntProperty(tmxProperty.name, Integer.parseInt(tmxProperty.value));
		}
		if(tmxProperty.type == TMXPropertyType.FLOAT) {
			return new FloatProperty(tmxProperty.name, Float.parseFloat(tmxProperty.value));
		}
		if(tmxProperty.type == TMXPropertyType.STRING) {
			return new StringProperty(tmxProperty.name, tmxProperty.value);
		}
		if(tmxProperty.type == TMXPropertyType.COLOR) {
			return new ColorProperty(tmxProperty.name, tmxProperty.value);
		}
		if(tmxProperty.type == TMXPropertyType.FILE) {
			return new FileProperty(tmxProperty.name, tmxProperty.value);
		}
		return null;
	}
	
	
	
	
	public static Rectanglef convertTMXRectangle(TMXRectangle tmxRectangle) {
		return new Rectanglef(tmxRectangle.x, tmxRectangle.y, tmxRectangle.width, tmxRectangle.height);
	}
	
	
	
	public static Vector2f convertTMXPoint(TMXPoint tmxPoint) {
		return new Vector2f(tmxPoint.x, tmxPoint.y);
	}
	
	
	public static Polygonf convertTMXPolygon(TMXPolygon tmxPolygon) {
		return new Polygonf(tmxPolygon.points);
	}
	
	
	public static Polygonf convertTMXPolyline(TMXPolyline tmxPolyline) {
		return new Polygonf(tmxPolyline.points);
	}
	
	
	
}




