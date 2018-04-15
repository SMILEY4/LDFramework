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
				// TODO
				continue;
			}
			
			if("imagelayer".equalsIgnoreCase(child.getNodeName())) {
				// TODO
				continue;
			}
			
			if("group".equalsIgnoreCase(child.getNodeName())) {
				// TODO
				continue;
			}
			
		}		
		
		return map;
	}

	
	
	
	private static TMXLayer parseLayer(Node ndLayer) {
		
		TMXLayer layer = new TMXLayer();
		layer.visible = true;
		
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









