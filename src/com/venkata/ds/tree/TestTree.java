package com.venkata.ds.tree;

public class TestTree {

	public static void main(String[] args) {
		
		Tree sampleTree = getSampleTree();
		System.out.println("Tree \n"+sampleTree.toString());
		sampleTree.preOrderTraversal();
		sampleTree.postOrderTraversal();
		sampleTree.bredthLevelTraversal();
		
		
	}
	
	private static Tree getSampleTree() {
		TreeNode a = new TreeNode("A");
		TreeNode b = new TreeNode("B");
		TreeNode c = new TreeNode("C");
		TreeNode d = new TreeNode("D");
		TreeNode e = new TreeNode("E");
		TreeNode f = new TreeNode("F");
		TreeNode g = new TreeNode("G");
		TreeNode h = new TreeNode("H");
		TreeNode i = new TreeNode("I");
		TreeNode j = new TreeNode("J");
		
		f.getChildern().add(i);
		
		g.getChildern().add(j);
		
		b.getChildern().add(e);
		b.getChildern().add(f);
		
		d.getChildern().add(g);
		d.getChildern().add(h);
		
		a.getChildern().add(b);
		a.getChildern().add(c);
		a.getChildern().add(d);
		
		return new Tree(a);
	}
	
}
