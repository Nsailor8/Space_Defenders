package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

	GamePanel gp;
	public Input(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
	//Title State
	if (gp.gameState == gp.titleState) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.stopMusic();
				gp.playMusic(0);
			}
			if(gp.ui.commandNum == 1) {
				//load
			}
			if(gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
		
		
	}
	
		
	//Play State
	if (gp.gameState == gp.playState) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
			
		if(code == KeyEvent.VK_A) {
			leftPressed = true;	
				}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_0) {
			spacePressed = true;
		}
		if(code == KeyEvent.VK_P) {
			if (gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
		
	}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
			
		if(code == KeyEvent.VK_A) {
			leftPressed = false;	
				}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

}
