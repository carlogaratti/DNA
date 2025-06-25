package com.carlogaratti.dna.command.receiver;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JAvaClip extends Receiver{
	
	Clip clip = null;
	
	String _loopCount;
	
	public void loopCount(String aLoopCount) {
		_loopCount = aLoopCount;
	}
	

	@Override
	public String value() {
		//if (!clip.isRunning())
			clip.close();
		System.out.println("sto cercando di chiudere");
		return "Close";
	}

	@Override
	public void value(String soundFileName) {

		
		URL url = this.getClass().getClassLoader().getResource(soundFileName);
        AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(url);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        // Get a sound clip resource.
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
        // Open audio clip and load samples from the audio input stream.
        try {
			clip.open(audioIn);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("_loopCount: " + _loopCount);
        clip.loop(Integer.parseInt(_loopCount));
        clip.drain();
      
		
	}

	@Override
	public void on(Object aWidget) {
		// TODO Auto-generated method stub
		
	}
}
