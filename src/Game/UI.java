package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import objects.SuperObject;
import objects.OBJHealth;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font Pixel;
	BufferedImage fullHealth, halfHealth, noHealth;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	int MessageCounter = 0;
	
	
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
		try {
			Pixel = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// Create HUD 
		SuperObject heart = new OBJHealth(gp);
		fullHealth = heart.image;
		halfHealth = heart.image2;
		noHealth = heart.image3;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(Pixel);
		g2.setColor(Color.white);
		
		// Message
		if (messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message,getCenteredXText(message), gp.tileSize*5);
			
			
			messageCounter++;
			
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
		
		//Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//Play State
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		//Pause State
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen(); 
			
			
		}
		
	}
	public void drawPlayerLife() {
		
//		 gp.player.life = 1;
//		
//		int x = gp.tileSize/2;
//		int y = gp.tileSize/2;
//		int i = 2;
//		//Draw full health
//		while(i == gp.player.maxLife) {
//			g2.drawImage(fullHealth, x, y, null);
//			i = -1;
//		}
//		//Reset
//		x = gp.tileSize/2;
//		y = gp.tileSize/2;
//		i = 6;
//		//Current Health
//		while(i == gp.player.life) {
//			g2.drawImage(halfHealth, x, y,null);
//		}
//		i=-1;
//		while(i < gp.player.life && i == 0) {
//			g2.drawImage(noHealth, x, y,null);
//		}
//			i++; ;
		
		
	}
	public void drawTitleScreen(){
		//Title Name
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Space Defenders";
		int x = getCenteredXText (text);
		int y  = gp.tileSize *3;
		//Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		//Text Color
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		//Player Image
		x = gp.screenWidth / 2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.ship, x, y, gp.tileSize*2, gp.tileSize *2, null );
		
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		
		text = "NEW GAME";
		x = getCenteredXText (text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
			
		}
		
		text = "LOAD GAME";
		x = getCenteredXText (text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
			
		}
		
		text = "QUIT";
		x = getCenteredXText (text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
			
		}
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getCenteredXText(text);
		int y = gp.screenHeight / 2;
		
		g2.drawString(text, x, y);
		
	}
	public int getCenteredXText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length /2;
		return x;
	}
}
