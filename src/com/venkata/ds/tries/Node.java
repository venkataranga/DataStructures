package com.venkata.ds.tries;

import java.util.HashMap;
import java.util.Map;

public class Node {

	Map<Character, Node> childern;
	boolean isWord;
	
	public Node() {
		this.childern = new HashMap<>();
		isWord = false;
	}

	public Map<Character, Node> getChildern() {
		return childern;
	}

	public void setChildern(Map<Character, Node> childern) {
		this.childern = childern;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}
	
	public void addNode(char c) {
		Node result = childern.get(c);
		if(result == null)
			this.childern.put(c, new Node());
	}

	@Override
	public String toString() {
		return "Node [childern=" + childern + "]";
	}
	
	
}
