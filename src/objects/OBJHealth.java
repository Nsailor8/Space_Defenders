package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJHealth extends SuperObject {

	GamePanel gp;
	public OBJHealth(GamePanel gp) {
		this.gp = gp;
		
		name = "Health";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health_10.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/health_05.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/health_00.png"));
			image = uTool.scaleImage(image, gp.tileSize * 2, gp.tileSize/2);
			image2 = uTool.scaleImage(image2, gp.tileSize * 2, gp.tileSize/2);		
			image3 = uTool.scaleImage(image3, gp.tileSize * 2, gp.tileSize/2);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
