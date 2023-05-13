package com.monoalphabetic.cypherImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.monoalphabetic.cypher.FileProcessor;

public class FileProcessorImpl implements FileProcessor {

	private String parsedInputText;

	@Override
	public String parsedInputText(String inputText) {
		try (Scanner scanner = new Scanner(new File(inputText))) {
			while (scanner.hasNext()) {
				parsedInputText = scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return parsedInputText;
	}
}
