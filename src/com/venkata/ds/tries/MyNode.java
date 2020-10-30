package com.venkata.ds.tries;

import java.util.ArrayList;
import java.util.List;

/***
 * this is my own implementation of the node classs
 */
public class MyNode {

	Character name;
	List<MyNode> childern;
	boolean isWord;

	public MyNode() {
		this.childern = new ArrayList<>();
	}

	public MyNode(Character name) {
		this.name = name;
		this.childern = new ArrayList<>();
	}

	public MyNode(Character name, boolean isWord) {
		this.name = name;
		this.isWord = isWord;
		this.childern = new ArrayList<>();
	}

	public Character getName() {
		return this.name;
	}

	public void addChild(MyNode child) {
		childern.add(child);
	}
	
	public List<MyNode> getChildern(){
		return this.childern;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	@Override
	public boolean equals(Object obj) {
		boolean myNodeInstance=  ((obj instanceof MyNode) && (this.getName() == ((MyNode) obj).getName()));
		boolean myCharacterInstance = ((obj instanceof Character) && (this.getName() == (Character) obj));
		/*
		 * System.out.println(obj.getClass().getName());
		 * System.out.println("myNodeInstance: "+myNodeInstance+", "
		 * +"myCharacterInstance: "+myCharacterInstance);
		 */
		
		return myCharacterInstance || myNodeInstance;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return  name + "= [ childern=" + childern + ", isWord=" + isWord + "]";
	}
	
	
}
