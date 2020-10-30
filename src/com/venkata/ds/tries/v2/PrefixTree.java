package com.venkata.ds.tries.v2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 202677
 *
 */
public class PrefixTree {

	TrieNode root = new TrieNode(null, true, false);

	public void insert(String word) {
		
		root.add(stringToQueue(word));

	}
	
	public List<String> wordsWithPrefix(String prefix) {
		List<String> words = root.wordsWithPrefix(prefix,stringToQueue(prefix));
		return words;
	}

	private Queue<Character> stringToQueue(String word) {
		Queue<Character> queue = new LinkedList<Character>();
		for (char c : word.toCharArray()) {
			queue.offer(c);
		}
		return queue;
	}

	@Override
	public String toString() {
		return "ROOT" + System.lineSeparator() + root.toString(0);
	}
}
