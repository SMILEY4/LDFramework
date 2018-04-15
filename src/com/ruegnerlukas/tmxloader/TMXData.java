package com.ruegnerlukas.tmxloader;

import java.util.ArrayList;
import java.util.List;

public class TMXData {

	public String encoding;
	public String compression;
	public String data;
	
	public List<Integer> tileGIDs = new ArrayList<Integer>();
	public List<TMXChunk> chunks = new ArrayList<TMXChunk>();

	
	
	
	public void prettyPrint(int layer) {
		
		String spacing = "";
		for(int i=0; i<layer; i++) {
			spacing += "  ";
		}
		
		System.out.println(spacing+"DATA");
		System.out.println(spacing+"-encoding    = " + encoding);
		System.out.println(spacing+"-compression = " + compression);
		
		if(data.length() < 60) {
			System.out.println(spacing+"-data        = " + data);
		} else {
			System.out.println(spacing+"-data        = " + data.substring(0, 60) + "...");
		}

		if(!tileGIDs.isEmpty()) {
			System.out.println(spacing+"  "+"TILE_GIDS");
			System.out.print("-");
			for(int id : tileGIDs) {
				System.out.print(spacing+"  "+id + " ");
			}
			System.out.println();
		}
		
		for(TMXChunk c : chunks) {
			c.prettyPrint(layer+1);
		}
		
	}
	
}
