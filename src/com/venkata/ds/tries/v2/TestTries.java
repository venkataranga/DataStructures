package com.venkata.ds.tries.v2;

public class TestTries {

	public static void main(String[] args) {

		PrefixTree t = new PrefixTree();

		/*
		 * t.insert("do"); t.insert("dood"); t.insert("dog"); t.insert("done");
		 * t.insert("dinesh"); t.insert("dine");
		 */
		
		t.insert("aardvark");
		t.insert("ant");
		t.insert("anteater");
		t.insert("angel");
		t.insert("anchovy");
		t.insert("anthony");
		t.insert("baylor");
		t.insert("q");
		
		System.out.println(t.wordsWithPrefix("ant"));
		System.out.println(t);

	}

}
