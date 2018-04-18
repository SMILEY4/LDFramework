package com.ruegnerlukas.scenes;

import java.util.HashMap;
import java.util.Map;

import com.ruegnerlukas.scenes.transition.SceneTransition;


public class SceneManager {

	public static enum TransitionState {
		NO_TRANSITION,
		SCENE_OUT,
		SCENE_IN
	}
	
	
	private static final SceneManager SCENE_MANAGER = new SceneManager();
	
	public static SceneManager get() {
		return SCENE_MANAGER;
	}
	
	
	
	

	
	private Map<String, Scene> scenes;
	private Scene activeScene;
	private Scene staticScene;
	
	private SceneTransition transitionIn, transitionOut;
	private SceneTransition transitionActive;
	private TransitionState transitionState;
	private String nextScene;
	
	
	
	private SceneManager() {
		scenes = new HashMap<String, Scene>();
		transitionState = TransitionState.NO_TRANSITION;
	}

	
	
	
	
	public void changeScene(String name, SceneTransition transitionOut, SceneTransition transitionIn) {
		
		this.transitionOut = transitionOut;
		this.transitionIn = transitionIn;
		
		// no transition
		if(transitionOut == null && transitionIn == null) {
			setScene(name);
			
		// at least one transition
		} else {
			if(transitionOut != null) {
				transitionState = TransitionState.SCENE_OUT;
				transitionActive = transitionOut;
				nextScene = name;
				
			} else if(transitionOut == null) {
				setScene(name);
				transitionState = TransitionState.SCENE_IN;
				transitionActive = transitionIn;
			}
			transitionActive.onStart();
		}
		
	}

	
	
	
	protected void setScene(String name) {
		if(activeScene != null) {
			unloadScene(activeScene);
		}
		Scene next = getScene(name);
		if(next != null) {
			loadScene(next);
		}
		activeScene = next;
	}
	
	
	
	
	public void setStaticScene(String name) {
		if(this.staticScene != null) {
			unloadScene(staticScene);
		}
		this.staticScene = getScene(name);
		if(this.staticScene != null) {
			loadScene(staticScene);
		}
	}

	
	
	
	public Scene getStaticScene() {
		return this.staticScene;
	}
	
	
	
	
	public void addScene(String name, Scene scene) {
		scenes.put(name, scene);
	}
	
	
	
	
	public Scene getScene(String name) {
		return scenes.get(name);
	}
	
	
	
	
	public Scene getActiveScene() {
		return activeScene;
	}
	
	
	
	
	public SceneTransition getActiveTransition() {
		return this.transitionActive;
	}
	
	
	
	
	public TransitionState getTransitionState() {
		return this.transitionState;
	}
	
	
	
	
	public void update(int deltaMS) {
		
		if(transitionState == TransitionState.SCENE_OUT && transitionActive.isDone()) {
			
			if(transitionIn == null) {
				setScene(nextScene);
				transitionState = TransitionState.NO_TRANSITION;
				transitionActive = null;
				transitionIn = null;
				transitionOut = null;
				
			} else {
				setScene(nextScene);
				transitionState = TransitionState.SCENE_IN;
				transitionActive = transitionIn;
				transitionActive.onStart();
			}
			
		} if(transitionState == TransitionState.SCENE_IN && transitionActive.isDone()) {
			transitionState = TransitionState.NO_TRANSITION;
			transitionActive = null;
			transitionIn = null;
			transitionOut = null;
		}
		
		if(transitionActive != null) {
			transitionActive.update(deltaMS);
		}
		if(activeScene != null) {
			activeScene.update(deltaMS);
		}
		if(staticScene != null) {
			staticScene.update(deltaMS);
		}
		
	}
	
	
	
	public void loadScene(Scene scene) {
		if(!scene.isLoaded()) {
			scene.load();
			scene.setLoaded(true);
		}
	}
	
	
	public void unloadScene(Scene scene) {
		if(scene.isLoaded()) {
			scene.unload();
			scene.setLoaded(false);
		}
	}
	
	
	public void unloadAll() {
		for(Map.Entry<String, Scene> entry : scenes.entrySet()) {
			Scene scene = entry.getValue();
			unloadScene(scene);
		}
	}
	
	
	
	
}
