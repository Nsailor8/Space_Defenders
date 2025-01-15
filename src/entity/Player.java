package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Game.Input;
import Game.UtilityTool;
import Game.GamePanel;

public class Player extends Entity{

	Input input;
	
	//private boolean isShooting = false;
   //private ArrayList<Rectangle> bullets = new ArrayList<>();

	
	public Player(GamePanel gp, Input input) {
		
		super(gp);
		this.input = input;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues(){
		x = 768 / 2;
		y = 500;
		speed = 6;
		direction = "down";
		
		//Player status
//		maxLife = 2;
//		life = maxLife;
	}
	
	public void getPlayerImage() {
		ship = setup("/player/Gray1_Player");
	}
	
	int entityLeftX = this.x + 8;
	int entityRightX = this.x + 8 + 32;
	int entityTopY = this.y + 16;
	int entityBottomY = this.y + 16 + 32;
	
	
	public void update() {
		
		if(input.upPressed == true || input.downPressed == true || input.leftPressed == true || input.rightPressed == true) {
		
		if(input.upPressed == true) {
			direction = "up";
			
			//if (entityTopY < 0) entityTopY = 0;
		}
		else if (input.downPressed == true) {
			direction = "down";
			
			//if (entityBottomY < 576) entityBottomY = 576 - 32;
		}
		else if (input.leftPressed== true) {
			direction = "left";
			//if (entityLeftX < 0) entityLeftX = 0;
			
		}
		else if (input.rightPressed == true) {
			direction = "right";
			
			//if (entityRightX > 768) entityLeftX = 768 - 32;
		}
		/*
		else if (input.spacePressed == true && !isShooting) {
			shootbullet();
		}
		*/
		
		//Check Tile Collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//Check Enemy Collision
		int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
		
		 // If collision false, player can move
		if(collisionOn == false) {
			switch(direction) {
			case "up": y -= speed; break;
			case "down": y += speed; break;
			case "left": x -= speed; break;
			case "right": x += speed; break;
			}
		}
		
	}
}

	/*
	private void shootbullet() {
		bullets.add(new Rectangle(this.x + 32 / 2 - 2, this.y - 10, 5, 10));
        isShooting = true;
		
	}
	*/
	public void draw(Graphics2D g2) {
		
		
//		  g2.setColor(Color.YELLOW);
//	        for (Rectangle bullet : bullets) {
//	            g2.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
//	        }
		BufferedImage image = ship;
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		
	}
	
}





