package com.gm.maven.guava_example;

import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;
/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Multiset<String> animals = HashMultiset.create();
		animals.add("Cat");
		animals.add("Dog");
		animals.add("Parrot");
		animals.add("Parrot");
		animals.add("Parrot");
		
		animals.forEach(System.out::println);
	}
}
