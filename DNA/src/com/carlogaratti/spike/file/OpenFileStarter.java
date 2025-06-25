package com.carlogaratti.spike.file;

import java.io.File;

public class OpenFileStarter {
	public static void main(String[] args) {
		String filename1 = "acme-bank.csv";
		File acme_bank = new File (".\\resources\\" + filename1);
		System.out.println(acme_bank.canRead());
		System.out.println(acme_bank.exists());
	}
}
