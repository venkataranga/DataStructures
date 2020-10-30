package com.venkata.ds.tries;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	Map<Character, Node> root;
	
	public Trie() {
		root = new HashMap<>();
	}
	
	public void insert(String wordToInsert) {
		char[] chars = wordToInsert.toCharArray();
		Map<Character, Node> current =  root; 
		int index=0;
		for(char c : chars) {
			if(!current.containsKey(c))
				current.put(c,new Node());
			current = current.get(c).getChildern();
			if(index == chars.length -1)
			index++;
		}
		
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
	
}
