package com.venkata.ds.tree;

/**
 * This is for a General TreeNode NOT Binary tree
 * */
public class Tree {
	
	private TreeNode root;
	
	public Tree(TreeNode root) {
		this.root = root;
	}
	
	public void preOrderTraversal() {
		System.out.println("Preorder Traversal: ");
		this.root.printPreOrder();
		System.out.println("\n");
	}
	
	public void postOrderTraversal() {
		System.out.println("PostOrder Traversal: ");
		this.root.printPostOrder();
		System.out.println("\n");
	}
	
	public void bredthLevelTraversal() {
		System.out.println("Breadth Level Traversal: ");
		this.root.printBFS();
		System.out.println("\n");
	}
	
	@Override
	public String toString() {
		return this.root.toString(0);
	}
}
