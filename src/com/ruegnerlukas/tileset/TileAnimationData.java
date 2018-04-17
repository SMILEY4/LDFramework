package com.ruegnerlukas.tileset;


public class TileAnimationData {

	private int frameCount = 0;
	private int[] localIDs;
	private int[] durations;
	
	
	
	
	public TileAnimationData() {

	}
	
	
	
	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
		this.localIDs = new int[frameCount];
		this.durations = new int[frameCount];
	}
	
	
	
	
	public int getFrameCount() {
		return this.frameCount;
	}
	
	
	
	
	public void setLocalID(int frame, int id) {
		localIDs[frame%frameCount] = id;
	}
	
	
	
	
	public void setFrameDuration(int frame, int duration) {
		durations[frame%frameCount] = duration;
	}
	
	
	
	
	public int getLocalID(int frame) {
		return localIDs[frame%frameCount];
	}
	
	
	
	
	public int getFrameDuration(int frame) {
		return durations[frame%frameCount];
	}
	
	
	
}
