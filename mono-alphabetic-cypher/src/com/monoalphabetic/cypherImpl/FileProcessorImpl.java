package com.monoalphabetic.cypherImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.monoalphabetic.cypher.FileProcessor;

public class FileProcessorImpl implements FileProcessor {

	private String parsedInputText;
	private Scanner scanner;

	@Override
	public String parsedInputText(String inputText) {
		try {
			scanner = new Scanner(new File(inputText));
			while (scanner.hasNext()) {
				parsedInputText = scanner.nextLine();
//				cipher.setGivenInputText(parsedInputText);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return parsedInputText;
	}

}
