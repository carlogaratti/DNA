package com.carlogaratti.spike.swing;


import com.carlogaratti.progetti.pomoapp.SubmitListener;

public class PomoThread extends Thread {
	 
	
	private SubmitListener _submitListener;

	public PomoThread(SubmitListener aSubmitListener) {
		_submitListener = aSubmitListener;
	}

	@Override
	public void run() {
		
		_submitListener.setupCommand().execute();
		while(true)	{
			if (Thread.interrupted()) {
	          break;
	        }
			_submitListener.coreCommand().execute();
		}
	}

	

	
}
