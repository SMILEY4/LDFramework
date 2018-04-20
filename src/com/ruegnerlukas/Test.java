package com.ruegnerlukas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ruegnerlukas.scenes.Scene;
import com.ruegnerlukas.scenes.SceneManager;
import com.ruegnerlukas.scenes.transition.ColorFadeTransition;
import com.ruegnerlukas.simplemath.interpolation.InterpolationSine;
import com.ruegnerlukas.simplemath.vectors.vec4.Vector4f;
import com.ruegnerlukas.tilemap.Map;
import com.ruegnerlukas.tilemap.TilemapLoader;
import com.ruegnerlukas.tileset.Tileset;
import com.ruegnerlukas.tileset.TilesetLoader;
import com.ruegnerlukas.tmxloader.TMXLoader;
import com.ruegnerlukas.tmxloader.TMXMap;

import javafx.animation.Interpolator;

public class Test {

	
	private static JFrame frame;
	private static JPanel jpanel;
	private static boolean running = true;
	
	private static Map map;
	private static Tileset set;
	private static BufferedImage tilesetImg;
	
	private static int offx, offy;
	
	
	public static void main(String[] args) {

		
//		Scene sceneA = new Scene() {
//			@Override public void load() {
//				System.out.println("Scene A: load");
//			}
//
//			@Override public void update(int deltaMS) {
//				System.out.println("Scene A: update " + deltaMS + "ms");
//			}
//
//			@Override public void unload() {
//				System.out.println("Scene A: unload");
//			}
//		};
//		
//		Scene sceneB = new Scene() {
//			@Override public void load() {
//				System.out.println("Scene B: load");
//			}
//			@Override public void update(int deltaMS) {
//				System.out.println("Scene B: update " + deltaMS + "ms");
//			}
//			@Override public void unload() {
//				System.out.println("Scene B: unload");
//			}
//		};
//		
//		ColorFadeTransition transitionA = new ColorFadeTransition(2000, new Vector4f(0,0,0,1), new Vector4f(1,1,1,0), new InterpolationSine());
//		ColorFadeTransition transitionB = new ColorFadeTransition(2000, new Vector4f(1,1,1,0), new Vector4f(0,0,0,1), new InterpolationSine());
//
//		
//		SceneManager.get().addScene("sceneA", sceneA);
//		SceneManager.get().addScene("sceneB", sceneB);
//		SceneManager.get().changeScene("sceneA", null, null);
//		
//		long time = 0;
//		boolean changed = false;
//		
//		while(running) {
//			
//			System.out.println();
//			
//			if(time >= 3000 && !changed) {
//				SceneManager.get().changeScene("sceneB", transitionA, transitionB);
//				changed = true;
//			}
//			
//			SceneManager.get().update(50);
//			
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			time += 50;
//		}
//		
		
		TMXLoader loader = new TMXLoader() {
			@Override
			public String onRequestFileContent(String strFile) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("res/"+strFile));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				StringBuilder sb = new StringBuilder();
			    String line;
				try {
					line = br.readLine();
				    while (line != null) {
				        sb.append(line);
				        sb.append(System.lineSeparator());
				        line = br.readLine();
				    }
				} catch (IOException e) {
					e.printStackTrace();
				}

			    String everything = sb.toString();
				return everything;
			}
		};
		TMXMap tmxMap = loader.loadTMX(new File("res/unbenannt3.tmx"));
		tmxMap.prettyPrint(0);
		
		
		
		set = TilesetLoader.createTileset(tmxMap.tilesets.get(0));
		map = TilemapLoader.createTilemap(tmxMap);
		
		try {
			tilesetImg = ImageIO.read(new File("res/"+tmxMap.tilesets.get(0).image.source));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		
//		setupDisplay();
//		
//		while(running) {
//			frame.repaint();
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
	}
	
	
	
	
	private static void draw(Graphics2D g) {
		Renderer.render(offx, offy, g, map, set, tilesetImg);
	} 
	
	
	
	private static void keyDown(KeyEvent e) {
		int speed = 10;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { offx += speed; }
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 	{ offx -= speed; }
		if(e.getKeyCode() == KeyEvent.VK_UP) 	{ offy -= speed; }
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 	{ offy += speed; }
	}
	
	
	
	private static void setupDisplay() {
		
		// setup frame
		frame = new JFrame("LDFramework - Lukas Ruegner (2018)");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(false);
		
		// exit with escape-key
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { System.exit(0); }
			}
			@Override public void keyReleased(KeyEvent e) {}
			@Override public void keyTyped(KeyEvent e) {}
		});
		
		// events on exit
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		
		
		// setup jpanel
		jpanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics gr) {
				super.paintComponent(gr);
				
				Graphics2D g = (Graphics2D)gr;
				
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				
				draw(g);
				
			}
		};
		frame.add(jpanel);
		frame.setVisible(true);
		
		
		KeyListener keyListener = new KeyListener() {
			@Override public void keyReleased(KeyEvent e) {}
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {
				keyDown(e);
			}
		};
		frame.addKeyListener(keyListener);
		
	}
	
}
