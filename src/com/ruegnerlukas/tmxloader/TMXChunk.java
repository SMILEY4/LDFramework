package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXChunk {

	public int x;
	public int y;
	public int width;
	public int height;
	
	public List<Integer> tileGIDs = new ArrayList<Integer>();

	
public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"CHUNK");
		System.out.println(spacing+"-pos  = " + x + "," + y);
		System.out.println(spacing+"-size = " + width + "x" + height);

		if(!tileGIDs.isEmpty()) {
			System.out.println(spacing+"  "+"TILE_GIDS");
			for(int id : tileGIDs) {
				System.out.print(spacing+"  "+id + " ");
			}
			System.out.println();
		}
		
	}
	
	
}
