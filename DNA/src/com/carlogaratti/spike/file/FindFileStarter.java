package com.carlogaratti.spike.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FindFileStarter {
	
	public static void main(String[] args) {
		String filePath = ".\\resources";
		File dir = new File(filePath);
		System.out.println(" nome  " + dir.getName());
		File[] files = dir.listFiles();
		List<File> content = Arrays.asList(files);
		for (File each : content) {
			System.out.println(each.getName());
		}
		
		
	}

}
