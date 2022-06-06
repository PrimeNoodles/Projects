package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	URL soundFile[] = new URL[10];
	
	public Sound() {
		//Background music from Project Moon's Lobotomy Corporation - No Warning OST
		soundFile[0] = getClass().getResource("/sound/BGM_01.wav");
		soundFile[1] = getClass().getResource("/sound/FootStep.wav");
//		soundFile[2] = getClass().getResource("/sound/FootStep");
	}
	
	public void setFile(int index) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile[index]);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
			//Controls the volume of the audio
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//Right now it is lowered by 20 decibels
			gainControl.setValue(-20.0f);
		} catch(Exception e) {
			System.out.println("Sound error");
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);;
	}
	
	public void stop() {
		clip.stop();
	}
}

/*
For some reason, javax sound only supports wav files, not mp3. For mp3 javaFX media works, but you need to download and install the package as it can't be imported
wow wow wow how annoying i guess it's time to convert the files before hand
*/