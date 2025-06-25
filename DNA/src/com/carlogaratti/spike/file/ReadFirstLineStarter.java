package com.carlogaratti.spike.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFirstLineStarter {
	public static void main(String[] args) throws IOException {
		String filename1 = "pioneer-savings.csv";
		File pioneer_savings = new File (".\\resources\\" + filename1);
		System.out.println(pioneer_savings.canRead());
		System.out.println(pioneer_savings.exists());
		
		BufferedReader reader = new BufferedReader(new FileReader(pioneer_savings));
		String line = reader.readLine();
		System.out.println(line);
		String secondLine = reader.readLine();
		System.out.println(secondLine);
	}

}
