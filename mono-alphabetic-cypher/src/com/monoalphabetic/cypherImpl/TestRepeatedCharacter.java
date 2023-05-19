package com.monoalphabetic.cypherImpl;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestRepeatedCharacter {

	private Set<Character> repeatedChar = new LinkedHashSet<Character>();

	private void lambdaFunct(int val) {
		if (!repeatedChar.contains((char) val))
			repeatedChar.add((char) val);
		else
			System.out.println("Repeated char is " + (char) val);
	}

	private void testRepeatedCharacter(String str) {
//		str.chars().forEach(val -> {
//			if (!repeatedChar.contains((char) val))
//				repeatedChar.add((char) val);
//			else
//				System.out.println("Repeated char is " + (char) val);
//		});
		str.chars().forEach(this::lambdaFunct);
		System.out.println(repeatedChar);
	}

	public static void main(String[] args) {
		TestRepeatedCharacter trc = new TestRepeatedCharacter();
		trc.testRepeatedCharacter("s01owjgk5vez9l43pfbu78rymcha6qdt2xin");
	}
}
