package com.venkata.ds.graphtheory.weightedgraphs;

import java.util.Objects;

public class Edge {

	private String from;
	private String to;
	private Integer weight;
	
	
	public Edge(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}
	public Edge(String from, String to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public String getFrom(){ return from; }
	public String getTo() {
		return to;
	}
	public Integer getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return weight.intValue() == edge.weight.intValue() &&
				from.equals(edge.from) &&
				to.equals(edge.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to, weight);
	}

	@Override
	public String toString() {
		return "Edge{" +
				"from='" + from + '\'' +
				", to='" + to + '\'' +
				", weight=" + weight +
				'}';
	}
}
