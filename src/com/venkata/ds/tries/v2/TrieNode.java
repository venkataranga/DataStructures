package com.venkata.ds.tries.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TrieNode {
	
	private Character name;
	private boolean isRoot = false;
	private boolean isWord = false;
	private Map<Character, TrieNode> childern = new HashMap<>();
	
	public TrieNode(Character name) {
		super();
		this.name = name;
	}

	public TrieNode(Character name,boolean isWord) {
		super();
		this.name = name;
		this.isWord = isWord;
	}
	
	public TrieNode(Character name, boolean isRoot, boolean isWord) {
		super();
		this.name = name;
		this.isRoot = isRoot;
		this.isWord = isWord;
	}

	
	
	public void add(Queue<Character> word) {
		
		Character currChar = word.poll();
		if(currChar != null) {
			if(!childern.containsKey(currChar)) {
				childern.put(currChar, new TrieNode(currChar));
			}
			TrieNode trieNode = childern.get(currChar);
			if(word.isEmpty())
				trieNode.setWord(true);
			
			trieNode.add(word);
		}
	}
	
	
	public List<String> wordsWithPrefix(String prefixWord, Queue<Character> prefix) {
		TrieNode subtree = getSubTree(prefix);
		if(subtree == null) return null;
		List<String> words  = subtree.getWords();
		for(int i=0; i<words.size(); i++) {
			words.set(i, prefixWord.substring(0, prefixWord.length()-1)+words.get(i));
		}
		return words;
	}
	
	public List<String> getWords(){
	
		List<String> words = new ArrayList<String>();
		if(this.isWord) {
			words.add(""+name);
		}
		for(TrieNode node : childern.values()) {
			
			List<String> childWords = node.getWords();
			for(String s : childWords)
				words.add(name+s);
			
		}
		return words;
	}
	
	private TrieNode getSubTree(Queue<Character> word) {
		
		Character currChar = word.poll();
		
		if(word.isEmpty()) {
			return childern.get(currChar);
		}
		
		if(childern.containsKey(currChar))
			return childern.get(currChar).getSubTree(word);
		else
			return null;
		
	}
	
	
	
	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public Character getName() {
		return name;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public Map<Character, TrieNode> getChildern() {
		return childern;
	}	
	
	@Override
	public String toString() {
		return "TrieNode [childern=" + childern + "]";
	}
	
	
	public String toString(int height) {
		String s = "    ";
		for(int i = 1; i <height; i++) {
			s += "    ";
		}
		s = s+ "|--";
		if(isWord)
			s = s+name +"*"+ System.lineSeparator();
		else
			s = s+name + System.lineSeparator();
		for(TrieNode node : childern.values()) {
			
			s = s + node.toString(height+1);
		}
		
		return s;
	}
}
