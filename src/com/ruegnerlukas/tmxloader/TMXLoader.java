package com.ruegnerlukas.tmxloader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ruegnerlukas.tmxloader.tmxLayers.TMXGroupLayer;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXObjectLayer;
import com.ruegnerlukas.tmxloader.tmxLayers.TMXTileLayer;
import com.ruegnerlukas.tmxloader.tmxObjects.*;

/**
 * http://doc.mapeditor.org/en/latest/reference/tmx-map-format/
 * */
public class TMXLoader {

	
	public static TMXTileset loadTSX(File fileTileset) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fileTileset);
			return loadTSX(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static TMXTileset loadTSX(String strTileset) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(strTileset);
			return loadTSX(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private static TMXTileset loadTSX(Document doc) {
		
		doc.getDocumentElement().normalize();
		
		Node nTileset = doc.getFirstChild();
		if(!"tileset".equalsIgnoreCase(nTileset.getNodeName())) {
			System.err.println("Document is not tileset!");
			return null;
		}
		
		return parseTileset(nTileset);
	}
	
	
	
	
	public static TMXMap loadTMX(File file) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			return loadTMX(doc, file.getParent()+"\\");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static TMXMap loadTMX(String strTileset) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(strTileset);
			
			return loadTMX(doc, "");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	private static TMXMap loadTMX(Document doc, String dir) {
		
		doc.getDocumentElement().normalize();
		
		Node ndMap = doc.getFirstChild();
		if(!"map".equalsIgnoreCase(ndMap.getNodeName())) {
			System.err.println("Document is not map!");
			return null;
		}
		
		return  parseMap(ndMap, dir);
	}
	
	
	
	
	private static TMXMap parseMap(Node ndMap, String dir) {
		
		TMXMap map = new TMXMap();
		
		NamedNodeMap mapAttribs = ndMap.getAttributes();
		for(int i=0; i<mapAttribs.getLength(); i++) {
			Node att = mapAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("version".equalsIgnoreCase(attName)) {
				map.version = attValue;
				continue;
			}
			if("tiledversion".equalsIgnoreCase(attName)) {
				map.tiledversion = attValue;
				continue;
			}
			if("orientation".equalsIgnoreCase(attName)) {
				map.orientation = attValue;
				continue;
			}
			if("renderorder".equalsIgnoreCase(attName)) {
				map.renderorder = attValue;
				continue;
			}
			if("width".equalsIgnoreCase(attName)) {
				map.width = Integer.parseInt(attValue);
				continue;
			}
			if("height".equalsIgnoreCase(attName)) {
				map.height = Integer.parseInt(attValue);
				continue;
			}
			if("tilewidth".equalsIgnoreCase(attName)) {
				map.tilewidth = Integer.parseInt(attValue);
				continue;
			}
			if("tileheight".equalsIgnoreCase(attName)) {
				map.tileheight = Integer.parseInt(attValue);
				continue;
			}
			if("hexsidelength".equalsIgnoreCase(attName)) {
				map.hexsidelength = Integer.parseInt(attValue);
				continue;
			}
			if("staggeraxis".equalsIgnoreCase(attName)) {
				map.staggeraxis = attValue;
				continue;
			}
			if("staggerindex".equalsIgnoreCase(attName)) {
				map.staggerindex = attValue;
				continue;
			}
			if("backgroundcolor".equalsIgnoreCase(attName)) {
				map.backgroundcolor = attValue;
				continue;
			}
			if("nextobjectid".equalsIgnoreCase(attName)) {
				map.nextobjectid = Integer.parseInt(attValue);
				continue;
			}
			
		}	
		
		
		NodeList children = ndMap.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						map.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
			
			if("tileset".equalsIgnoreCase(child.getNodeName())) {
				TMXTileset tileset = parseTileset(child);
				if(tileset.source != null && !tileset.source.isEmpty()) {
					File file = new File(dir+tileset.source);
					if(file.exists()) {
						tileset = loadTSX(file);
					} else {
						System.err.println("TSX-File does not exist: " + file);
					}
					
				}
				map.tilesets.add(tileset);
				continue;
			}
			
			if("layer".equalsIgnoreCase(child.getNodeName())) {
				map.layers.add(parseLayer(child));
				continue;
			}
			
			if("objectgroup".equalsIgnoreCase(child.getNodeName())) {
				map.layers.add(parseObjectLayer(child));
				continue;
			}
			
			if("imagelayer".equalsIgnoreCase(child.getNodeName())) {
				// TODO
				continue;
			}
			
			if("group".equalsIgnoreCase(child.getNodeName())) {
				map.layers.add(parseGroupLayer(child));
				continue;
			}
			
		}		
		
		return map;
	}

	
	
	public static TMXGroupLayer parseGroupLayer(Node ndGroup) {
		
		TMXGroupLayer groupLayer = new TMXGroupLayer();
		
		NamedNodeMap groupAttribs = ndGroup.getAttributes();
		for(int i=0; i<groupAttribs.getLength(); i++) {
			Node att = groupAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("name".equalsIgnoreCase(attName)) {
				groupLayer.name = attValue;
				continue;
			}
			if("offsetx".equalsIgnoreCase(attName)) {
				groupLayer.offsetX = Integer.parseInt(attValue);
				continue;
			}
			if("offsety".equalsIgnoreCase(attName)) {
				groupLayer.offsetY = Integer.parseInt(attValue);
				continue;
			}
			if("opacity".equalsIgnoreCase(attName)) {
				groupLayer.opacity = Float.parseFloat(attValue);
				continue;
			}
			if("visible".equalsIgnoreCase(attName)) {
				groupLayer.visible = attValue.equalsIgnoreCase("1");
				continue;
			}
		}
		
		
		NodeList children = ndGroup.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						groupLayer.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			if("layer".equalsIgnoreCase(child.getNodeName())) {
				groupLayer.layers.add(parseLayer(child));
				continue;
			}
			
			if("objectgroup".equalsIgnoreCase(child.getNodeName())) {
				groupLayer.layers.add(parseObjectLayer(child));
				continue;
			}
			
			if("imagelayer".equalsIgnoreCase(child.getNodeName())) {
				// TODO
				continue;
			}
			
			if("group".equalsIgnoreCase(child.getNodeName())) {
				groupLayer.layers.add(parseGroupLayer(child));
				continue;
			}
			
		}
		
		
		return groupLayer;
	}
	
	
	

	private static TMXObjectLayer parseObjectLayer(Node ndObjectGroup) {
		
		TMXObjectLayer objGroup = new TMXObjectLayer();
		
		NamedNodeMap groupAttribsAttribs = ndObjectGroup.getAttributes();
		for(int i=0; i<groupAttribsAttribs.getLength(); i++) {
			Node att = groupAttribsAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("name".equalsIgnoreCase(attName)) {
				objGroup.name = attValue;
				continue;
			}
			if("color".equalsIgnoreCase(attName)) {
				objGroup.color = attValue;
				continue;
			}
			if("opacity".equalsIgnoreCase(attName)) {
				objGroup.opacity = Float.parseFloat(attValue);
				continue;
			}
			if("visible".equalsIgnoreCase(attName)) {
				objGroup.visible = attValue.equalsIgnoreCase("1");
				continue;
			}
			if("offsetx".equalsIgnoreCase(attName)) {
				objGroup.offsetX = Integer.parseInt(attValue);
				continue;
			}
			if("offsety".equalsIgnoreCase(attName)) {
				objGroup.offsetY = Integer.parseInt(attValue);
				continue;
			}
			if("draworder".equalsIgnoreCase(attName)) {
				objGroup.drawOrder = attValue;
				continue;
			}
			
		}
		
		
		// children
		NodeList children = ndObjectGroup.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						objGroup.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
			if("object".equalsIgnoreCase(child.getNodeName())) {
				objGroup.objects.add(parseObject(child));
				continue;
			}
			
		}
		
		return objGroup;
	}
	
	
	
	
	private static TMXObject parseObject(Node ndObject) {
		
		TMXObject object = null;
		
		// define type
		NodeList children = ndObject.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("ellipse".equalsIgnoreCase(child.getNodeName())) {
				object = new TMXEllipse();
				continue;
			}
			if("point".equalsIgnoreCase(child.getNodeName())) {
				object = new TMXEllipse();
				continue;
			}
			if("polygon".equalsIgnoreCase(child.getNodeName())) {
				object = new TMXPolygon();

				NamedNodeMap polygonAttribs = child.getAttributes();
				for(int j=0; j<polygonAttribs.getLength(); j++) {
					Node att = polygonAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					
					if("points".equalsIgnoreCase(attName)) {
						String[] strPoints = attValue.split(" ");
						int nPoints = strPoints.length;
						float[] points = new float[nPoints*2];
						for(int k=0, l=0; k<nPoints; k++) {
							String strPoint = strPoints[k];
							points[l++] = Float.parseFloat(strPoint.split(",")[0]);
							points[l++] = Float.parseFloat(strPoint.split(",")[1]);
						}
						((TMXPolygon)object).points = points;
						continue;
					}
				}
				
				continue;
			}
			if("polyline".equalsIgnoreCase(child.getNodeName())) {
				object = new TMXPolyline();
				
				NamedNodeMap polygonAttribs = child.getAttributes();
				for(int j=0; j<polygonAttribs.getLength(); j++) {
					Node att = polygonAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					if("points".equalsIgnoreCase(attName)) {
						String[] strPoints = attValue.split(" ");
						int nPoints = strPoints.length;
						float[] points = new float[nPoints*2];
						for(int k=0, l=0; k<nPoints; k++) {
							String strPoint = strPoints[k];
							points[l++] = Float.parseFloat(strPoint.split(",")[0]);
							points[l++] = Float.parseFloat(strPoint.split(",")[1]);
						}
						((TMXPolyline)object).points = points;
						continue;
					}
				}
				
				continue;
			}
			if("text".equalsIgnoreCase(child.getNodeName())) {
				object = new TMXText();
				TMXText txtObj = (TMXText)object;
				
				NamedNodeMap polygonAttribs = child.getAttributes();
				for(int j=0; j<polygonAttribs.getLength(); j++) {
					Node att = polygonAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					
					if("fontfamily".equalsIgnoreCase(attName)) {
						txtObj.fontfamily = attValue;
						continue;
					}
					if("pixelsize".equalsIgnoreCase(attName)) {
						txtObj.pixelsize = Integer.parseInt(attValue);
						continue;
					}
					if("wrap".equalsIgnoreCase(attName)) {
						txtObj.wrap = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("color".equalsIgnoreCase(attName)) {
						txtObj.color = attValue;
						continue;
					}
					if("bold".equalsIgnoreCase(attName)) {
						txtObj.bold = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("italic".equalsIgnoreCase(attName)) {
						txtObj.italic = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("underline".equalsIgnoreCase(attName)) {
						txtObj.underline = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("strikeout".equalsIgnoreCase(attName)) {
						txtObj.strikeout = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("kerning".equalsIgnoreCase(attName)) {
						txtObj.kerning = attValue.equalsIgnoreCase("1");
						continue;
					}
					if("halign".equalsIgnoreCase(attName)) {
						txtObj.halign = attValue;
						continue;
					}
					if("valign".equalsIgnoreCase(attName)) {
						txtObj.valign = attValue;
						continue;
					}
					
				}
				
				continue;
			}
			
		}
		if(object == null) {
			object = new TMXRectangle();
		}
		
		
		// get properties
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						object.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
		}
		
		
		
		NamedNodeMap objectAttribs = ndObject.getAttributes();
		for(int i=0; i<objectAttribs.getLength(); i++) {
			Node att = objectAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("id".equalsIgnoreCase(attName)) {
				object.id = Integer.parseInt(attValue);
				continue;
			}
			if("name".equalsIgnoreCase(attName)) {
				object.name = attValue;
				continue;
			}
			if("type".equalsIgnoreCase(attName)) {
				object.type = attValue;
				continue;
			}
			if("rotation".equalsIgnoreCase(attName)) {
				object.rotation = Integer.parseInt(attValue);
				continue;
			}
			if("gid".equalsIgnoreCase(attName)) {
				object.gid = Integer.parseInt(attValue);
				continue;
			}
			if("visible".equalsIgnoreCase(attName)) {
				object.visible = attValue.equalsIgnoreCase("1");
				continue;
			}
			if("x".equalsIgnoreCase(attName)) {
				object.x = Float.parseFloat(attValue);
				continue;
			}
			if("y".equalsIgnoreCase(attName)) {
				object.y = Float.parseFloat(attValue);
				continue;
			}
			if("width".equalsIgnoreCase(attName)) {
				object.width = Float.parseFloat(attValue);
				continue;
			}
			if("height".equalsIgnoreCase(attName)) {
				object.height = Float.parseFloat(attValue);
				continue;
			}
		}
		
		
		return object;
	}
	
	
	
	
	private static TMXTileLayer parseLayer(Node ndLayer) {
		
		TMXTileLayer layer = new TMXTileLayer();
		
		NamedNodeMap layerAttribs = ndLayer.getAttributes();
		for(int i=0; i<layerAttribs.getLength(); i++) {
			Node att = layerAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("name".equalsIgnoreCase(attName)) {
				layer.name = attValue;
				continue;
			}
			if("x".equalsIgnoreCase(attName)) {
				layer.x = Integer.parseInt(attValue);
				continue;
			}
			if("y".equalsIgnoreCase(attName)) {
				layer.y = Integer.parseInt(attValue);
				continue;
			}
			if("width".equalsIgnoreCase(attName)) {
				layer.width = Integer.parseInt(attValue);
				continue;
			}
			if("height".equalsIgnoreCase(attName)) {
				layer.height = Integer.parseInt(attValue);
				continue;
			}
			if("opacity".equalsIgnoreCase(attName)) {
				layer.opacity = Float.parseFloat(attValue);
				continue;
			}
			if("visible".equalsIgnoreCase(attName)) {
				layer.visible = attValue.equalsIgnoreCase("1");
				continue;
			}
			if("offsetx".equalsIgnoreCase(attName)) {
				layer.offsetX = Integer.parseInt(attValue);
				continue;
			}
			if("offsety".equalsIgnoreCase(attName)) {
				layer.offsetY = Integer.parseInt(attValue);
				continue;
			}
			
		}
		
		
		// children
		NodeList children = ndLayer.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						layer.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
			if("data".equalsIgnoreCase(child.getNodeName())) {
				layer.data = parseData(child);
				continue;
			}
			
		}
		
		return layer;
	}
	
	
	
	
	public static TMXData parseData(Node ndData) {
		TMXData data = new TMXData();
		
		data.data = ndData.getTextContent();
		
		NamedNodeMap dataAttibs = ndData.getAttributes();
		for(int i=0; i<dataAttibs.getLength(); i++) {
			Node att = dataAttibs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("encoding".equalsIgnoreCase(attName)) {
				data.encoding = attValue;
				continue;
			}
			if("compression".equalsIgnoreCase(attName)) {
				data.compression = attValue;
				continue;
			}
		}
		
		NodeList children = ndData.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("tile".equalsIgnoreCase(child.getNodeName())) {
				NamedNodeMap tileAttribs = child.getAttributes();
				Node attrib = tileAttribs.item(0);
				if(attrib != null && "gid".equalsIgnoreCase(attrib.getNodeName())) {
					data.tileGIDs.add(Integer.parseInt(attrib.getNodeValue()));
				} else {
					System.err.println("Data: Error loading tileGID");
					data.tileGIDs.add(0);
				}
				continue;
			}
			
			if("chunk".equalsIgnoreCase(child.getNodeName())) {
				data.chunks.add(parseChunk(child));
				continue;
			}
			
		}
		
		return data;
	}
	
	
	
	private static TMXChunk parseChunk(Node ndChunk) {
		
		TMXChunk chunk = new TMXChunk();
		
		NamedNodeMap chunkAttibs = ndChunk.getAttributes();
		for(int i=0; i<chunkAttibs.getLength(); i++) {
			Node att = chunkAttibs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			if("x".equalsIgnoreCase(attName)) {
				chunk.x = Integer.parseInt(attValue);
				continue;
			}
			if("y".equalsIgnoreCase(attName)) {
				chunk.y = Integer.parseInt(attValue);
				continue;
			}
			if("width".equalsIgnoreCase(attName)) {
				chunk.width = Integer.parseInt(attValue);
				continue;
			}
			if("height".equalsIgnoreCase(attName)) {
				chunk.height = Integer.parseInt(attValue);
				continue;
			}
		}
		
		
		NodeList children = ndChunk.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			if("tile".equalsIgnoreCase(child.getNodeName())) {
				NamedNodeMap tileAttribs = child.getAttributes();
				Node attrib = tileAttribs.item(0);
				if(attrib != null && "gid".equalsIgnoreCase(attrib.getNodeName())) {
					chunk.tileGIDs.add(Integer.parseInt(attrib.getNodeValue()));
				} else {
					System.err.println("Chunk: Error loading tileGID");
					chunk.tileGIDs.add(0);
				}
				continue;
			}
			
			
		}
		
		return chunk;
	}
	
	
	
	
	private static TMXTileset parseTileset(Node ndTileset) {
		
		TMXTileset tileset = new TMXTileset();
		
		NamedNodeMap tilesetAttribs = ndTileset.getAttributes();
		for(int i=0; i<tilesetAttribs.getLength(); i++) {
			Node att = tilesetAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			
			if("firstgid".equalsIgnoreCase(attName)) {
				tileset.firstgid = Integer.parseInt(attValue);				
				continue;
			}
			if("source".equalsIgnoreCase(attName)) {
				tileset.source = attValue;		
				continue;
			}
			if("name".equalsIgnoreCase(attName)) {
				tileset.name = attValue;		
				continue;
			}
			if("tilewidth".equalsIgnoreCase(attName)) {
				tileset.tilewidth = Integer.parseInt(attValue);				
				continue;
			}
			if("tileheight".equalsIgnoreCase(attName)) {
				tileset.tileheight = Integer.parseInt(attValue);				
				continue;
			}
			if("spacing".equalsIgnoreCase(attName)) {
				tileset.spacing = Integer.parseInt(attValue);				
				continue;
			}
			if("margin".equalsIgnoreCase(attName)) {
				tileset.margin = Integer.parseInt(attValue);				
				continue;
			}
			if("tilecount".equalsIgnoreCase(attName)) {
				tileset.tilecount = Integer.parseInt(attValue);				
				continue;
			}
			if("columns".equalsIgnoreCase(attName)) {
				tileset.columns = Integer.parseInt(attValue);				
				continue;
			}
			
		}
		
		
		// children
		NodeList children = ndTileset.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			
			
			if("tileoffset".equalsIgnoreCase(child.getNodeName())) {
				Node ndOffset = child;
				NamedNodeMap offsetAttribs = ndOffset.getAttributes();
				for(int j=0; j<offsetAttribs.getLength(); j++) {
					Node att = offsetAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					if("x".equalsIgnoreCase(attName)) {
						tileset.offsetX = Integer.parseInt(attValue);
						continue;
					}
					if("y".equalsIgnoreCase(attName)) {
						tileset.offsetY = Integer.parseInt(attValue);
						continue;
					}
				}
				continue;
			}
			
			
			if("grid".equalsIgnoreCase(child.getNodeName())) {
				Node ndGrid = child;
				NamedNodeMap gridAttribs = ndGrid.getAttributes();
				for(int j=0; j<gridAttribs.getLength(); j++) {
					Node att = gridAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					if("orientation".equalsIgnoreCase(attName)) {
						tileset.orientation = attValue;
						continue;
					}
					if("width".equalsIgnoreCase(attName)) {
						tileset.gridWidth = Integer.parseInt(attValue);
						continue;
					}
					if("height".equalsIgnoreCase(attName)) {
						tileset.gridHeight = Integer.parseInt(attValue);
						continue;
					}
				}
				continue;
			}
			
			
			if("properties".equalsIgnoreCase(child.getNodeName())) {
				NodeList propertyChildren = child.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						tileset.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
			
			if("image".equalsIgnoreCase(child.getNodeName())) {
				TMXImage image = parseImage(child);
				tileset.image = image;
				continue;
			}
			
			
			if("terraintypes".equalsIgnoreCase(child.getNodeName())) {
				NodeList terraintypeChildren = child.getChildNodes();
				for(int j=0; j<terraintypeChildren.getLength(); j++) {
					Node cTerraintype = terraintypeChildren.item(j);
					if("terrain".equalsIgnoreCase(cTerraintype.getNodeName())) {
						tileset.terrains.add(parseTerrain(cTerraintype));
						continue;
					}
				}
				continue;
			}
			
			
			if("tile".equalsIgnoreCase(child.getNodeName())) {
				tileset.tiles.add(parseTile(child));
				continue;
			}
			
			
			if("wangsets".equalsIgnoreCase(child.getNodeName())) {
				// TODO
				continue;
			}
			
		}
		
		
		
		return tileset;
		
	}
	
	
	
	
	private static TMXProperty parseProperty(Node ndProperty) {
		
		TMXProperty property = new TMXProperty();
		
		NamedNodeMap propAttribs = ndProperty.getAttributes();
		for(int i=0; i<propAttribs.getLength(); i++) {
			Node att = propAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			if("name".equalsIgnoreCase(attName)) {
				property.name = attValue;
				continue;
			}
			if("type".equalsIgnoreCase(attName)) {
				property.type = attValue;
				continue;
			}
			if("value".equalsIgnoreCase(attName)) {
				property.type = attValue;
				continue;
			}
		}
		
		return property;
	}
	
	
	
	
	private static TMXTile parseTile(Node ndTile) {
		
		TMXTile tile = new TMXTile();
		
		NamedNodeMap tileAttribs = ndTile.getAttributes();
		for(int i=0; i<tileAttribs.getLength(); i++) {
			Node att = tileAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			if("id".equalsIgnoreCase(attName)) {
				tile.id = Integer.parseInt(attValue);
				continue;
			}
			if("type".equalsIgnoreCase(attName)) {
				tile.type = attValue;
				continue;
			}
			if("terrain".equalsIgnoreCase(attName)) {
				tile.terrain = attValue;
				continue;
			}
			if("probability".equalsIgnoreCase(attName)) {
				tile.probabilty = Float.parseFloat(attValue);
				continue;
			}
		}
		
		NodeList tileChildren = ndTile.getChildNodes();
		for(int i=0; i<tileChildren.getLength(); i++) {
			Node cTile = tileChildren.item(i);
			
			if("properties".equalsIgnoreCase(cTile.getNodeName())) {
				NodeList propertyChildren = cTile.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						tile.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
			
			if("image".equalsIgnoreCase(cTile.getNodeName())) {
				tile.image = parseImage(cTile);
				continue;
			}
			
			
			if("objectgroup".equalsIgnoreCase(cTile.getNodeName())) {
				// TODO
				continue;
			}
			
			
			if("animation".equalsIgnoreCase(cTile.getNodeName())) {
				tile.animation = parseAnimation(cTile);
				continue;
			}
			
		}

		return tile;
	}
	
	
	
	
	private static TMXAnimation parseAnimation(Node ndAnimation) {
		
		TMXAnimation anim = new TMXAnimation();
		
		
		NodeList animChildren = ndAnimation.getChildNodes();
		for(int i=0; i<animChildren.getLength(); i++) {
			Node cAnim = animChildren.item(i);
			
			if("frame".equalsIgnoreCase(cAnim.getNodeName())) {
				
				TMXFrame frame = new TMXFrame();
				
				NamedNodeMap frameAttribs = cAnim.getAttributes();
				for(int j=0; j<frameAttribs.getLength(); j++) {
					Node att = frameAttribs.item(j);
					String attName = att.getNodeName();
					String attValue = att.getNodeValue();
					if("tileid".equalsIgnoreCase(attName)) {
						frame.tileID = Integer.parseInt(attValue);
						continue;
					}
					if("duration".equalsIgnoreCase(attName)) {
						frame.duration = Integer.parseInt(attValue);
						continue;
					}
				}
				
				anim.frames.add(frame);
				
				continue;
			}
			
		}
		
		return anim;
	}
	
	
	
	
	private static TMXTerrain parseTerrain(Node ndTerrain) {
		
		TMXTerrain terrain = new TMXTerrain();
		
		NamedNodeMap terrainAttribs = ndTerrain.getAttributes();
		for(int i=0; i<terrainAttribs.getLength(); i++) {
			Node att = terrainAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			if("name".equalsIgnoreCase(attName)) {
				terrain.name = attValue;
				continue;
			}
			if("tile".equalsIgnoreCase(attName)) {
				terrain.tile = Integer.parseInt(attValue);
				continue;
			}
		}
		
		NodeList terrainChildren = ndTerrain.getChildNodes();
		for(int i=0; i<terrainChildren.getLength(); i++) {
			Node cTerrain = terrainChildren.item(i);
			
			if("properties".equalsIgnoreCase(cTerrain.getNodeName())) {
				NodeList propertyChildren = cTerrain.getChildNodes();
				for(int j=0; j<propertyChildren.getLength(); j++) {
					Node cProperty = propertyChildren.item(j);
					if("property".equalsIgnoreCase(cProperty.getNodeName())) {
						terrain.properties.add(parseProperty(cProperty));
						continue;
					}
				}
				continue;
			}
			
		}
		
		return terrain;
	}
	
	
	
	
	private static TMXImage parseImage(Node ndImage) {
	
		TMXImage image = new TMXImage();
		
		NamedNodeMap imageAttribs = ndImage.getAttributes();
		for(int i=0; i<imageAttribs.getLength(); i++) {
			Node att = imageAttribs.item(i);
			String attName = att.getNodeName();
			String attValue = att.getNodeValue();
			if("format".equalsIgnoreCase(attName)) {
				image.format = attValue;
				continue;
			}
			if("source".equalsIgnoreCase(attName)) {
				image.source = attValue;
				continue;
			}
			if("trans".equalsIgnoreCase(attName)) {
				image.trans = attValue;
				continue;
			}
			if("width".equalsIgnoreCase(attName)) {
				image.width = Integer.parseInt(attValue);
				continue;
			}
			if("height".equalsIgnoreCase(attName)) {
				image.height = Integer.parseInt(attValue);
				continue;
			}
		}
		
		return image;
		
	}
	
	
	
	
}









