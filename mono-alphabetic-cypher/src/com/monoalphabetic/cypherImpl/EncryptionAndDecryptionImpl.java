package com.monoalphabetic.cypherImpl;

import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//	private Set<Integer> encriptOrDecriptIndexSet;
	private AtomicInteger encriptOrDecriptIndex;
	private AtomicInteger mapPairsIndex;

	public EncryptionAndDecryptionImpl(int seed, String givenInputText, FileWriter output, int encriptionOrDecription) {
		this.givenInputText = givenInputText;
		this.output = output;
		this.random = new Random(seed);
		alphabetsAndNumerics = "abcdefghijklmnopqrstuvwxyz0123456789";
		cypherText = new String();
		this.encriptionOrDecription = encriptionOrDecription;
		plainAndCypherPairs = new HashMap<Character, Character>();
//		encriptOrDecriptIndexSet = new LinkedHashSet<Integer>(alphabetsAndNumerics.length());
	}

	@Override
	public void encriptOrDecrypt() {
		encriptOrDecriptIndex = new AtomicInteger(0);
		mapPairsIndex = new AtomicInteger(0);
		StringBuilder text = new StringBuilder();

		generateRandomCypherText();

		try {
//			char[] plainChars = alphabetsAndNumerics.toCharArray();
//			char[] cypherChars = cypherText.toCharArray();

			System.out.println(cypherText);

//			for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
//				plainAndCypherPairs.put(plainChars[i], cypherChars[i]);
//			}

//			for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
//				plainAndCypherPairs.put(alphabetsAndNumerics.charAt(i), cypherText.charAt(i));
//			}
			alphabetsAndNumerics.chars().forEach(val -> {
				plainAndCypherPairs.put(alphabetsAndNumerics.charAt(mapPairsIndex.get()),
						cypherText.charAt(mapPairsIndex.get()));
				mapPairsIndex.incrementAndGet();
			});

//			for (int i = 0; i < givenInputText.length(); i++) {
//				if (plainAndCypherPairs.containsKey(givenInputText.charAt(i))
//						|| plainAndCypherPairs.containsValue(givenInputText.charAt(i))) {
//					text.append(encriptionOrDecription == 1 ? plainAndCypherPairs.get(givenInputText.charAt(i))
//							: decription(i));
//				}
//			}

			givenInputText.chars().forEach(val -> {
				if (plainAndCypherPairs.containsKey(givenInputText.charAt(encriptOrDecriptIndex.get()))
						|| plainAndCypherPairs.containsValue(givenInputText.charAt(encriptOrDecriptIndex.get()))) {
					text.append(encriptionOrDecription == 1 ? encryption(encriptOrDecriptIndex.get())
							: decription(encriptOrDecriptIndex.get()));
					// increments the atomic integer while the foreach loop is being looped.
					encriptOrDecriptIndex.incrementAndGet();
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

	private Character encryption(int i) {
		return plainAndCypherPairs.get(givenInputText.charAt(i));
	}

	private String decription(int i) {
//		StringBuilder sb = new StringBuilder();
//		plainAndCypherPairs.forEach((key, value) -> sb.append(key));
//		return sb.toString();
		String text = "";
//		for (Entry<Character, Character> entry : plainAndCypherPairs.entrySet()) {
//			if (entry.getValue() == givenInputText.charAt(i)) {
//				text += entry.getKey();
//				break;
//			}
//		}
		for (Character key : plainAndCypherPairs.keySet()) {
			if (key == givenInputText.charAt(i)) {
				text += key;
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
//			encriptOrDecriptIndexSet.add(random.nextInt(alphabetsAndNumerics.length()));
//			if (encriptOrDecriptIndexSet.size() == alphabetsAndNumerics.length())
//				break;
//		}
//
//		for (int i = 0; i < alphabetsAndNumerics.length(); i++) {
//			cypherText += alphabetsAndNumerics.charAt((int) encriptOrDecriptIndexSet.toArray()[i]);

//		}
	}
}

//ip.txt op.txt 50 1
//op.txt out.txt 50 0
//java -cp src com.monoalphabetic.driver.MonoAlphabeticCipher ip.txt op.txt 50 1
