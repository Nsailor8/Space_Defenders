package enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;
import entity.Entity;

public class EnemyShip extends Entity{
		
	public EnemyShip(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 2;
	//	maxLife =  1;
		//life = maxLife;
		//solidArea =  new Rectangle(0,0,48,48);
		getImage();
	}
	public void getImage() {
		ship = setup("/enemy/green");
	}
	public void setAction() {
		actionLock ++;
		
		if (actionLock == 120) {
		Random random = new Random();
		int i = random.nextInt(100)+1;
		
		if(i <= 25) {
			direction = "up";
		}
		if(i > 25 && i <= 50) {
			direction = "down";
		}
		if(i > 50 && i <= 75) {
			direction = "left";
		}
		if(i > 75 && i <= 100) {
			direction = "right";
			}
			actionLock = 0;
		}
	}	
		
//		  g2.setColor(Color.YELLOW);
//	        for (Rectangle bullet : bullets) {
//	            g2.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
//	        }
//		actionLock1++;
//		
//		if (actionLock1 == 120) {
//			direction = "right";
//			actionLock1 = 0;
//		}	
//	}
//	public void draw(Graphics2D g2) {
//		BufferedImage image = ship;
//		g2.drawImage(image, 100, 100, gp.tileSize, gp.tileSize, null);
//	}
		
//		  g2.setColor(Color.YELLOW);
//	        for (Rectangle bullet : bullets) {
//	            g2.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
//	        }
		
		
	
}
