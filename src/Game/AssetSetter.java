package Game;

import enemy.EnemyShip;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp){
		this.gp = gp;
	}
	

	public void setENEMY(){
		gp.enemy[0] = new EnemyShip(gp);
		gp.enemy[0].x = gp.tileSize*3;
		gp.enemy[0].y = gp.tileSize*3;
	}
}
