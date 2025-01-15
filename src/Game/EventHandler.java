package Game;

import java.awt.Rectangle;

public class EventHandler {
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	GamePanel gp;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
	}
	public void checkEvent() {
		//if(hit == true) {
			//eventHappens
		//}
	}
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if (gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any"));
			hit = true;
		
		}
		gp.player.solidArea.x = 32;
		gp.player.solidArea.y = 0;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;


		return hit;
	}
	
}
