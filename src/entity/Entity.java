package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;

public class Entity {
	GamePanel gp;
	public int x, y;
	public int speed;
	public BufferedImage ship;
	public String direction;
	public Rectangle solidArea = new Rectangle(32,0,0,8);
	public boolean collisionOn = false;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int actionLock;
	
	
	public Entity (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction(){}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		if(collisionOn == false) {
			switch(direction) {
			case "up": y -= speed; break;
			case "down": y += speed; break;
			case "left": x -= speed; break;
			case "right": x += speed; break;
			}
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = ship;
		int screenX = x - gp.player.x + gp.player.x;
		int screenY = y - gp.player.y + gp.player.y;
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
		}
}
