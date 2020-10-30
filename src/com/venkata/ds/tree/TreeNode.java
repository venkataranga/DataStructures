package com.venkata.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * This is for a General TreeNode NOT Binary tree
 * */
public class TreeNode {

	private String name;
	private List<TreeNode> childern = new ArrayList<>();
	
	
	public TreeNode(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public List<TreeNode> getChildern() {
		return childern;
	}
	
	public String toString(int height) {
		String print = "   ";
		for(int i=0; i< height; i++)
			print += "   ";
		print += "|--"+this.name+System.lineSeparator();
		
		for(TreeNode node: childern) {
			print += node.toString(height+1);
		}
		
		return print;
	}
	
	
	public void printPreOrder() {
		System.out.print(this.name);
		for(TreeNode childNode : this.childern) {
			childNode.printPreOrder();
		}
	}
	
	public void printPostOrder() {
		for(TreeNode childNode : this.childern) {
			childNode.printPostOrder();
		}
		System.out.print(this.name);
	}
	
	public void printBFS() {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(this);
		while(!queue.isEmpty()) {
			TreeNode tempNode = queue.poll();
			System.out.print(tempNode.getName());
			for(TreeNode node : tempNode.getChildern()) {
				queue.offer(node);
			}
		};
	}
}
