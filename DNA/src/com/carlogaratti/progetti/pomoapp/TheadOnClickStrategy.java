package com.carlogaratti.progetti.pomoapp;
import com.carlogaratti.spike.swing.PomoThread;

public class TheadOnClickStrategy extends OnClickStrategy {

	PomoThread worker;
	
	@Override
	public void runOn(SubmitListener aSubmitListener) {
		worker = new PomoThread(aSubmitListener);
		worker.start();
		
	}
	
	public void stop() {
		worker.interrupt();
	}

	

	

}
