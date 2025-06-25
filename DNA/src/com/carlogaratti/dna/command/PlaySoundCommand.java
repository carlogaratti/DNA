package com.carlogaratti.dna.command;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.carlogaratti.dna.command.receiver.JAvaClip;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

public class PlaySoundCommand extends Command {
	
	private JAvaClip _receiver;
	
	public PlaySoundCommand(Map<String, String> result) {
		super(result);
		// TODO Auto-generated constructor stub
	}
	
	public PlaySoundCommand(Bag aBag) {
		super(aBag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		_receiver.loopCount(_bag.get("times").asString());
		System.out.println("playSooundCommand _bag.get(_pullFromKey.get(0)).asString()" + _bag.get(_pullFromKey.get(0)).asString());
		_receiver.value(_bag.get(_pullFromKey.get(0)).asString());
		
		
	}

	
	public void receiver(Receiver aReceiver) {
		_receiver = (JAvaClip) aReceiver;

	}


}
