package com.monoalphabetic.cypherImpl;

import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.monoalphabetic.cypher.EncryptionAndDecryption;

public class EncryptionAndDecryptionImpl implements EncryptionAndDecryption {
	private String givenInputText;
	private FileWriter output;
	private Random random;
	private String alphabetsAndNumerics;
	private String cypherText;
	private int encriptionOrDecription;
	private Map<Character, Character> plainAndCypherPairs;
//	private Set<Integer> indexSet;

	public EncryptionAndDecryptionImpl(int seed, String givenInputText, FileWriter output, int encriptionOrDecription) {
		this.givenInputText = givenInputText;
		this.output = output;
		this.random = new Random(seed);
		alphabetsAndNumerics = "abcdefghijklmnopqrstuvwxyz0123456789";
		cypherText = new String();
		this.encriptionOrDecription = encriptionOrDecription;
		plainAndCypherPairs = new HashMap<Character, Character>();
//		indexSet = new LinkedHashSet<Integer>(alphabetsAndNumerics.length());
	}

	@Override
	public void encriptOrDecrypt() {
		AtomicInteger index = new AtomicInteger(0);
		StringBuilder text = new StringBuilder();

		generateRandomCypherText();

		try {
//			char[] plainChars = alphabetsAndNumerics.toCharArray();
//			char[] cypherChars = cypherText.toCharArray();

			System.out.println(cypherText);

//			for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
//				plainAndCypherPairs.put(plainChars[i], cypherChars[i]);
//			}

			for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
				plainAndCypherPairs.put(alphabetsAndNumerics.charAt(i), cypherText.charAt(i));
			}

//			for (int i = 0; i < givenInputText.length(); i++) {
//				if (plainAndCypherPairs.containsKey(givenInputText.charAt(i))
//						|| plainAndCypherPairs.containsValue(givenInputText.charAt(i))) {
//					text.append(encriptionOrDecription == 1 ? plainAndCypherPairs.get(givenInputText.charAt(i))
//							: decription(i));
//				}
//			}

			givenInputText.chars().forEach(val -> {
				if (plainAndCypherPairs.containsKey(givenInputText.charAt(index.get()))
						|| plainAndCypherPairs.containsValue(givenInputText.charAt(index.get()))) {
					text.append(encriptionOrDecription == 1 ? plainAndCypherPairs.get(givenInputText.charAt(index.get()))
							: decription(index.get()));
					index.incrementAndGet();
				}
			});

			output.write(text.toString());
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
		List<Character> characters = alphabetsAndNumerics.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

		Collections.shuffle(characters, random);

		StringBuilder sb = new StringBuilder();
		characters.forEach(sb::append);

		cypherText = sb.toString();
	}

	public void generateRandomCypherText_____() {
//		while (true) {
//			indexSet.add(random.nextInt(alphabetsAndNumerics.length()));
//			if (indexSet.size() == alphabetsAndNumerics.length())
//				break;
//		}
//
//		for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
//			cypherText += alphabetsAndNumerics.charAt((int) indexSet.toArray()[i]);
//		}
	}
}

//ip.txt op.txt 50 1
//op.txt out.txt 50 0
//java -cp src com.monoalphabetic.driver.MonoAlphabeticCipher ip.txt op.txt 50 1
