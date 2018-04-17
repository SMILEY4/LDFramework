package com.ruegnerlukas.scenes.transition;

import com.ruegnerlukas.simplemath.MathUtils;
import com.ruegnerlukas.simplemath.interpolation.IInterpolation;
import com.ruegnerlukas.simplemath.vectors.vec4.Vector4f;

public class ColorFadeTransition extends SceneTransition {

	
	private int duration;
	private IInterpolation interpolation;
	private Vector4f colorStart;
	private Vector4f colorEnd;
	private Vector4f colorCurrent;

	private long startMS = 0;
	private boolean done = false;
	
	
	
	
	
	public ColorFadeTransition(int duration, Vector4f colorStart, Vector4f colorEnd, IInterpolation interpolation) {
		this.duration = duration;
		this.colorStart = colorStart;
		this.colorEnd = colorEnd;
		this.colorCurrent = new Vector4f();
		this.interpolation = interpolation;
	}
	
	
	
	
	
	
	public Vector4f getCurrentColor() {
		return this.colorCurrent;
	}
	
	
	
	
	public void onStart() {
		startMS = System.currentTimeMillis();
		colorCurrent.set(colorStart);
	}

	
	
	
	@Override
	public void update(int deltaMS) {
		long time = System.currentTimeMillis() - startMS;
		if(time >= duration) {
			done = true;
		}
		float x = MathUtils.clamp((float)time/(float)duration, 0f, 1f);
		
		colorCurrent.x = interpolation.interpolate(colorStart.x, colorEnd.x, x);
		colorCurrent.y = interpolation.interpolate(colorStart.y, colorEnd.y, x);
		colorCurrent.z = interpolation.interpolate(colorStart.z, colorEnd.z, x);
		colorCurrent.w = interpolation.interpolate(colorStart.w, colorEnd.w, x);
	}
	
	
	
	
	@Override
	public boolean isDone() {
		return this.done;
	}

}
