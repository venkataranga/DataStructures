package com.venkata.ds.tries;

public class TempNode {

	Character c;
	
	public TempNode(char c) {
		this.c = c;
	}
	
	@Override
	public String toString() {
		return new String(new char[] {c});
	}
	
	public char value() {
		return this.c;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TempNode && this.c == ((TempNode) obj).value())
			return true;
		else 
			return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return c.hashCode();
	}
	
}
