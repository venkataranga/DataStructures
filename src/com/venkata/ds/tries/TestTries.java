package com.venkata.ds.tries;

import java.util.ArrayList;
import java.util.List;

public class TestTries {

	public static void main(String[] args) {

		MyTrie t = new MyTrie();
		
		t.insert("dood");
		t.insert("do");
		//t.insert("dummy");
		//t.insert("venkata");
		System.out.println(t);

		/*
		 * List<MyNode> myNodes = new ArrayList<>(); myNodes.add(new MyNode('A'));
		 * myNodes.add(new MyNode('B')); myNodes.add(new MyNode('D')); myNodes.add(new
		 * MyNode('C'));
		 * 
		 * 
		 * System.out.println(myNodes.contains(new MyNode('A')));
		 * System.err.println("----------------------------");
		 * System.out.println(myNodes.contains(new Character('A')));
		 * System.err.println("----------------------------");
		 * System.out.println(myNodes.contains(new MyNode('C')));
		 */
	}

}
