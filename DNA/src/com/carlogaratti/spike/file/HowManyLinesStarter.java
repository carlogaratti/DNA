package com.carlogaratti.spike.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HowManyLinesStarter   {
	public static void main(String[] args) throws IOException {
		String filename1 = ".\\resources\\pioneer-savings.csv";
		Path path = Paths.get(filename1);
		long lineCount;
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		  System.out.println(lineCount);
		}
	}

}
