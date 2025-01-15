package tile;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Game.GamePanel;
import Game.UtilityTool;
import entity.Player;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap();
	}
	public void getTileImage(){
try {
		setup(0, "Background_space", false);
		
		tile[1] = new Tile();
		tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png"));
		tile[1].collision = true;
		
		tile[2] = new Tile();
		tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png"));
		
		
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize,gp.tileSize);
			tile[index].collision = collision;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/map/map1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					String number[] = line.split(" ");
					
					int num = Integer.parseInt(number[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e){
			
		}
		
		
	}
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			int worldX = col * gp.tileSize;
			int worldY = row * gp.tileSize;
			int screenX = worldX - gp.player.x + gp.player.x;
			int screenY = worldY - gp.player.y + gp.player.y;

			if(worldX > gp.player.x - gp.player.x 
					&& worldX < gp.player.x + gp.player.x 
					&& worldY > gp.player.y - gp.player.y 
					&& worldY < gp.player.y + gp.player.y) {
				
			}
			
			
			g2.drawImage(tile[tileNum].image, screenX,screenY, gp.tileSize, gp.tileSize, null);
			col++;
			x+=gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y+= gp.tileSize;
			}
		}
		
		
	}
	
	
	
}


