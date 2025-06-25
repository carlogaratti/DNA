package com.carlogaratti.spike;

import com.carlogaratti.spike.swing.PomoThread;

public class StarterThread {
	public static void main(String[] args) {
		PomoThread thread = new PomoThread();
		thread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
