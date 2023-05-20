package com.monoalphabetic.cypher.driver;

import java.io.File;
import java.io.FileWriter;

import com.monoalphabetic.cypher.FileProcessor;
import com.monoalphabetic.cypherImpl.EncryptionAndDecryptionImpl;
import com.monoalphabetic.cypherImpl.FileProcessorImpl;

public class MonoAlphabeticCipher {
	private int seed;
	private int encriptionOrDecription;
	private String givenInputText;
	private EncryptionAndDecryptionImpl encryptionAndDecription;
	private FileProcessor fileProcessor;

	public MonoAlphabeticCipher(int seed, int encriptionOrDecription) {
		this.seed = seed;
		this.encriptionOrDecription = encriptionOrDecription;
	}

	private void doStuff(String givenInput, FileWriter output) {
		fileProcessor = new FileProcessorImpl();
		givenInputText = fileProcessor.parsedInputText(givenInput);
		encryptionAndDecription = new EncryptionAndDecryptionImpl(seed, givenInputText, output, encriptionOrDecription);
		encryptionAndDecription.encriptOrDecrypt();
	}

	public static void main(String[] args) {
		if (args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 4 arguments.");
			System.exit(1);
		}

		String argOne = null;
		if (new File(args[0]).exists()) {
			if (args[0].contains(".txt"))
				argOne = args[0];
			else
				argOne = args[0].replace(".txt", "");
		} else {
			if (args[0].contains(".txt")) {
				argOne = args[0];
			} else
				argOne = args[0] + ".txt";
		}

		if (new File(argOne).exists() && (args[2]).matches("\\d+") && args[3].matches("\\d+")) {
			try {
				File input = new File(argOne);
				FileWriter output = new FileWriter(args[1]);
				int seed = Integer.parseInt(args[2]);
				int encriptionOrDecription = Integer.parseInt(args[3]);

				if (input.length() != 0 && (encriptionOrDecription == 1 || encriptionOrDecription == 0)
						&& (seed >= 50 && seed <= 10000)) {

					MonoAlphabeticCipher cipher = new MonoAlphabeticCipher(seed, encriptionOrDecription);
					cipher.doStuff(argOne, output);
				} else {
					System.err.println(
							"Either the input is empty or the argument to select for enciption and decription should be either 1 or 2 also the seed must be between 50-10000");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.err.println(
					"Input file is absent (or) seed must not have been entred as an integer value (or) 1/0 for encription and decryption must not have been entred as an integer value");
		}
	}
}
