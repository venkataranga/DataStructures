package com.venkata.ds.graphtheory.weightedgraphs;

public class Edge {

	String name;
	int weight;
	
	
	public Edge(String name) {
		super();
		this.name = name;
	}
	public Edge(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	public int getWeight() {
		return weight;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Edge)
			return this.name.equalsIgnoreCase(((Edge)obj).getName());

		return false;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
