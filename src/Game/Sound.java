package Game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	java.net.URL soundURL[] = new URL[30];
	
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/Journey_in_Space.wav");
		soundURL[1] = getClass().getResource("/sound/menu_music.wav");
		soundURL[2] = getClass().getResource("/sound/enemy_death.wav");
		soundURL[3] = getClass().getResource("/sound/enemylaser.wav");
		soundURL[4] = getClass().getResource("/sound/game_start.wav");
		soundURL[5] = getClass().getResource("/sound/laser_shoot.wav");
		soundURL[6] = getClass().getResource("/sound/player_death.wav");
	}
	
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
		}
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		clip.stop();
		
	}
	
}
