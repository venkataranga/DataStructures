package com.venkata.ds.graphtheory.unweightedgraphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.venkata.ds.graphtheory.CycleDetectedException;
import com.venkata.ds.graphtheory.Graph;
import com.venkata.ds.graphtheory.IllegalGraphOperation;

/***
 * 
 * 
 * trying out my own implementation of graph data structure. Currently works for
 * unweighted graphs. Will need to modify for weighted graphs
 * 
 * This is a UnWeighted and Undirected Graph
 * 
 * @author 202677
 *
 */
public class UnWeightedGraph extends Graph {

	private final Map<String, HashSet<String>> edges = new HashMap<>();

	public UnWeightedGraph() {

	}

	public void addNode(String name) {

		if (edges.containsKey(name))
			return;

		edges.put(name, new HashSet<>());

	}

	public void addEdge(String source, String destination) {

		if (!edges.containsKey(source) || !edges.containsKey(destination))
			throw new IllegalArgumentException("Invalid Source or destination. AddNode before adding Edge");

		if (edges.get(source).contains(destination))
			return;

		edges.get(source).add(destination);
		edges.get(destination).add(source);

	}

	public void removeEdge(String source, String destination) {

		if (!edges.containsKey(source) || !edges.containsKey(destination))
			throw new IllegalArgumentException("Invalid Source or destination. AddNode before adding Edge");

		if (!edges.get(source).contains(destination))
			return;

		edges.get(source).remove(destination);
		edges.get(destination).remove(source);

	}

	public void removeNode(String name) throws IllegalGraphOperation {

		if (!edges.containsKey(name))
			return;

		if (!edges.get(name).isEmpty())
			throw new IllegalGraphOperation("Remove all edges before removing node");

		edges.remove(name);
	}

	public void removeNodeAndEdges(String name) throws IllegalGraphOperation {

		if (!edges.containsKey(name))
			return;

		if (!edges.get(name).isEmpty()) {
			Iterator<String> iterator = edges.get(name).iterator();
			while (iterator.hasNext()) {
				String dest = iterator.next();
				edges.get(dest).remove(name);
			}
		}

		edges.remove(name);
	}
	
	
	public void performDFS() {
		Set<String> unvisited = new HashSet<>();
		unvisited.addAll(edges.keySet());
		Stack<String> dfsStack = new Stack<>();
		
		while(!unvisited.isEmpty()) {
			String curr = unvisited.iterator().next();
			dfsStack.add(curr);
			while(!dfsStack.isEmpty()) {
				//System.out.println("\nStack: "+dfsStack);
				String temp = dfsStack.pop();
				System.out.print(temp+" ");
				unvisited.remove(temp);
				//edges.get(temp).stream().filter(s -> unvisited.contains(s)).forEach(s-> dfsStack.add(s));
				for(String s : edges.get(temp)) {
					if(unvisited.contains(s) && !dfsStack.contains(s))
						dfsStack.push(s);
				}
				
			}
		}
		
		System.out.println();
		
	}

	public void performDFSMaintainingVisitedNodes(){

		Set<String> visited = new HashSet<>();
		Set<String> nodes = new HashSet<>();
		nodes.addAll(edges.keySet());
		for(String node : nodes){
			if(visited.contains(node))
				continue;
			Stack<String> visitOder = new Stack<>();
			visitOder.push(node);
			while (!visitOder.isEmpty()){
				String currNode = visitOder.pop();
				System.out.print(currNode+" ");
				visited.add(currNode);
				edges.get(currNode).forEach(s->{
					if(!visited.contains(s) && !visitOder.contains(s))
						visitOder.push(s);
				});
			}
		}

		System.out.println();

	}

	public void dfs(){
		Set<String> visited = new HashSet<>();
		for(String node : edges.keySet()){
			performDFSUsingRecursion(node, visited);
		}
		System.out.println();
	}

	public void dfsAt(String node){
		Set<String> visited = new HashSet<>();
		performDFSUsingRecursion(node,visited);
		System.out.println();
	}

	private void performDFSUsingRecursion(String node, Set<String> visited){

		if (visited.contains(node))
			return;
		System.out.print(node +" ");
		visited.add(node);
		for (String child : edges.get(node))
			performDFSUsingRecursion(child, visited);
	}

	public void findConnectedComponents(){
		Set<String> visited = new HashSet<>();
		Map<String, Integer> connectedComponents = new HashMap<>();
		int componentId = 0;
		for(String node : edges.keySet()){
			if(!visited.contains(node)){
				componentId++;
				markConnectedComponents(node,visited, connectedComponents, componentId);
			}
		}
		System.out.println("connectedComponents: \n"+connectedComponents);
	}

	private void markConnectedComponents(String node, Set<String> visited,Map<String, Integer> connectedComponents, int componentId) {

		if (visited.contains(node))
			return;
		connectedComponents.put(node, componentId);
		visited.add(node);
		for (String child : edges.get(node))
			markConnectedComponents(child,visited,connectedComponents,componentId);

	}



	public boolean detectCycle(){

		boolean cycleExists = false;

		Set<String> visited = new HashSet<>();

		for (String node : edges.keySet()){
			if(visited.contains(node)) continue;

			try {
				checkIfCycleExist(node, visited);
			} catch (CycleDetectedException e) {
				return true;
			}
		}

		return cycleExists;

	}

	//TODO: this will only work for directed graphs, need to revist for undirected graphs
	public void checkIfCycleExist(String node, Set<String> visited) throws CycleDetectedException {
		if(visited.contains(node))
			throw new CycleDetectedException();
		System.out.print(node+" ");
		visited.add(node);
		for(String child : edges.get(node))
			checkIfCycleExist(child,visited);
	}


	@Override
	public String toString() {
		
		StringBuffer print =new StringBuffer("");
		for(String k : edges.keySet()) {
			
			print.append(k);
			print.append("-->");
			print.append(edges.get(k).toString());
			print.append("\n");
			
			
		}
		return print.toString();
	}
}
