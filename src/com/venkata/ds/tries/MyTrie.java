package com.venkata.ds.tries;

import java.util.ArrayList;
import java.util.List;

public class MyTrie {

	List<MyNode> nodes = new ArrayList<>();

	public MyTrie() {

	}

	public void insert(String word) {
		char[] chars = word.toCharArray();
		MyNode currChar = null;
		List<MyNode> lookUpNodes = nodes;
		for(int i=0; i < chars.length; i++) {			
			currChar = (i != chars.length -1) ? new MyNode(chars[i]) : new MyNode(chars[i], true);
			
			int nodeIndex = lookUpNodes.indexOf(currChar);
			//System.out.println("Index of "+currChar+" is:"+nodeIndex);
			if(lookUpNodes.isEmpty()|| nodeIndex == -1) {
				lookUpNodes.add(currChar);
			}
			
			MyNode myNode = lookUpNodes.get(lookUpNodes.indexOf(currChar));
			myNode.setWord(currChar.isWord);
			lookUpNodes = myNode.getChildern();
		}
		
		//System.out.println(currChar);
	}

	
	public List<String> getWordsWithPrefix(String word){
		
		List<String> words = new ArrayList<>();
		char[] chars = word.toCharArray();
		List<MyNode> currNodes = nodes;
		for (char c: chars) {
			int i = currNodes.indexOf(new MyNode(c));
			if(i != -1) {
				currNodes = currNodes.get(i).getChildern();
			}else {
				return words;
			}
		}
		
		for(MyNode node : currNodes) {
			String wordWithPrefix = word+node.getName();
			List<MyNode> tempChildNodes = node.getChildern();
			while (!tempChildNodes.isEmpty()) {
				
			}
			
		}
		
		return words;
		
	}
	
	
//	public String 
	
	@Override
	public String toString() {
		return "nodes=" + nodes;
	}
	
	
}
