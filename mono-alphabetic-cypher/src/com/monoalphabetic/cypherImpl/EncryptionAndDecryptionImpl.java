package com.monoalphabetic.cypherImpl;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import com.monoalphabetic.cypher.EncryptionAndDecryption;

import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;

public class EncryptionAndDecryptionImpl implements EncryptionAndDecryption {
	private String givenInputText;
	private FileWriter output;
	private Random random;
	private String alphabetsAndNumerics;
	private String cypherText;
	private int encriptionOrDecription;
	private Map<Character, Character> plainAndCypherPairs;
	private Set<Integer> indexSet;

	public EncryptionAndDecryptionImpl(int seed, String givenInputText, FileWriter output, int encriptionOrDecription) {
		this.givenInputText = givenInputText;
		this.output = output;
		this.random = new Random(seed);
		alphabetsAndNumerics = "abcdefghijklmnopqrstuvwxyz0123456789";
		cypherText = new String();
		this.encriptionOrDecription = encriptionOrDecription;
		plainAndCypherPairs = new HashMap<Character, Character>();
		indexSet = new LinkedHashSet<Integer>(alphabetsAndNumerics.length());
	}

	@Override
	public void encriptOrDecrypt() {
		generateRandomCypherText();

		try {
			char[] plainChars = alphabetsAndNumerics.toCharArray();
			char[] cypherChars = cypherText.toCharArray();
			String text = new String();
			System.out.println(cypherChars);

			for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
				plainAndCypherPairs.put(plainChars[i], cypherChars[i]);
			}

			for (int i = 0; i < givenInputText.length(); i++) {
				if (plainAndCypherPairs.containsKey(givenInputText.charAt(i))
						|| plainAndCypherPairs.containsValue(givenInputText.charAt(i))) {
					text = encriptionOrDecription == 1 ? text + plainAndCypherPairs.get(givenInputText.charAt(i))
							: text + decription(i);
				}
			}
			output.write(text);
			output.flush();
			output.close();
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StringJoiner joiner = new StringJoiner(",");
			plainAndCypherPairs.forEach((key, value) -> joiner.add(key + "->" + value));
			System.out.print(joiner.toString());
		}
	}

	private String decription(int i) {
		String text = "";
		for (Entry<Character, Character> entry : plainAndCypherPairs.entrySet()) {
			if (entry.getValue() == givenInputText.charAt(i)) {
				text += entry.getKey();
				break;
			}
		}
		return text;
	}

	private void generateRandomCypherText() {
		while (true) {
			indexSet.add(random.nextInt(alphabetsAndNumerics.length()));
			if (indexSet.size() == alphabetsAndNumerics.length())
				break;
		}

		Integer[] arr = new Integer[indexSet.size()];
		arr = indexSet.toArray(arr);

		for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
			cypherText += alphabetsAndNumerics.charAt(arr[i]);
		}
	}
}

//ip.txt op.txt 50 1
//op.txt out.txt 50 0
//java -cp src com.monoalphabetic.driver.MonoAlphabeticCipher ip.txt op.txt 50 1
