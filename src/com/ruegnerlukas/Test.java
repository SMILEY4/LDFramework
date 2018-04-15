package com.ruegnerlukas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ruegnerlukas.tilemap.Cell;
import com.ruegnerlukas.tilemap.Layer;
import com.ruegnerlukas.tilemap.Tile;
import com.ruegnerlukas.tilemap.Tilemap;
import com.ruegnerlukas.tilemap.TilemapLoader;
import com.ruegnerlukas.tileset.Tileset;
import com.ruegnerlukas.tmxloader.TMXLoader;
import com.ruegnerlukas.tmxloader.TMXMap;

public class Test {

	
	private static JFrame frame;
	private static JPanel jpanel;
	private static boolean running = true;
	
	private static Tilemap map;
	private static Tileset set;
	
	private static int offx, offy;
	
	
	public static void main(String[] args) {

		Random random = new Random();
		
		
		
		TMXMap tmxMap = TMXLoader.loadTMX(new File("res/unbenannt3.tmx"));
		tmxMap.prettyPrint(0);
		
		
		
		set = new Tileset();
		try {
			set.setImage(ImageIO.read(new File("res/tmw_desert_spacing.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		set.setTilewidth(32);
		set.setTileheight(32);
		set.setMargin(0);
		set.setSpacing(1);
		set.setColumns(8);
		
		
		map = TilemapLoader.createTilemap(tmxMap);
		

		
		
		setupDisplay();
		
		while(running) {
			frame.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	private static void draw(Graphics2D g) {
		Renderer.render(offx, offy, g, map, set);
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
