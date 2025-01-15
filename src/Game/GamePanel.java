package Game;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	private BufferedImage img;
	
	//Screen Settings
	final int orginalTileSize = 16; //16x16 tile
	final int scale = 3;
	
	// World Settings
	public int tileSize = orginalTileSize * scale; //48x48 tile
	public  int maxScreenCol = 16;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol; //48*16 = 768 pixels
	public int screenHeight = tileSize * maxScreenRow; // 48*12 = 576 pixels
	
	//FPS
	int FPS = 60;
	
	// System  
	TileManager tileM = new TileManager(this);
	Input input = new Input(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public EventHandler eHandler = new EventHandler(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	// Entities and Objects
	public Player player = new Player(this,input);
	public SuperObject obj[] = new SuperObject[5];
	public Entity enemy[] = new Entity[10];
	public UI ui = new UI(this);
	Thread gameThread; // starts game timer

	// Game State
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int titleState = 0;
	
	public GamePanel() {
		
	importImg();	
		
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
	this.addKeyListener(input);
	this.setFocusable(true);
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/tiles/Background_space.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setupGame() {
	
		aSetter.setENEMY();
		playMusic(1);
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS; // Draws the screen = to FPS
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		
		while(gameThread != null) {
					
			
			// UPDATES INFORMATION
				update();
			
			
			
			
			//DRAWS SCREEN WITH UPDATED INFO
			repaint();
			
			
			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if(remainingTime< 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void update(){
		
		if (gameState == playState) {
			// player
			player.update();
//			//enemy
			for (int i = 0; i < enemy.length; i++) {
				if(enemy[i] != null) {
					enemy[i].update();
				}
			}
			
		}
		if(gameState == pauseState) {
			//placeholder
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
				
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//Background
		g2.drawImage(img, 0, 0, null);
		
		//Title
		if(gameState == titleState){
			ui.draw(g2);
		}
		//Game state
		else {
		//tiles
		tileM.draw(g2);
		//Enemy
		for (int i = 0; i < enemy.length; i++) {
			if(enemy[i]!= null) {
			enemy[i].draw(g2);
			}
		}		
		//player
		player.draw(g2);
			
		//UI
		ui.draw(g2);
				
		}
		
		g2.dispose();
		}
	public void playMusic (int i) { //music
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		
		music.stop();
	}
	public void playSE(int i) { //sound effects
		
		se.setFile(i);
		se.play();
		
	}
}
