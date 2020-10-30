package com.venkata.ds.graphtheory.weightedgraphs;

import com.venkata.ds.graphtheory.Graph;

import java.util.*;

import static com.venkata.ds.graphtheory.Constants.*;

/***
 *
 *
 * trying out my own implementation of graph data structure.
 * Currently works for unweighted graphs. Will need to modify for weighted graphs  
 * @author 202677
 *
 */
public class WeightedGraph extends Graph {

    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public WeightedGraph() {

    }

    public synchronized void addNode(String name) {
        graph.put(name, new HashMap<>());
    }

    public void addEdge(String source, String destination, Integer edgeWeight) {
        graph.get(source).put(destination, edgeWeight);
    }

    /**
     * Finds the shortest path from source node to all other nodes using topological sort algorithm
     * PRO's:
     * - Simple to implement
     * - works for both +ve and -ve edge weights
     * - takes linear time of O(V+E)
     * CON's:
     * - Doesn't work for undirected graphs since Topo order doesn't exist
     * - Doesn't work for Directed Graphs with Cycles since Topo order doesn't exist for Directed Graph's with Cycles
     */

    //TODO: SingleSourceShortestPath using Topo is not working for disconnected graphs. need to look into it

    public void findSingleSourceShortestPathUsingTopoSort(String node) {

        Stack<String> topoOder = topSort();
        Map<String, Integer> shortestDistances = new LinkedHashMap<>();
        while (!topoOder.isEmpty())
            shortestDistances.put(topoOder.pop(), null);
        shortestDistances.put(node, 0);
        shortestDistances.keySet().forEach(n -> {
            if (shortestDistances.get(node) != null) findShortestDistance(n, shortestDistances);
        });
        System.out.println(shortestDistances);
    }

    private void findShortestDistance(String node, Map<String, Integer> shortestDistances) {

        for (String edge : graph.get(node).keySet()) {

            int newDistance = shortestDistances.get(node) + graph.get(node).get(edge);
            if (shortestDistances.get(edge) == null) {
                shortestDistances.put(edge, newDistance);
            } else {
                shortestDistances.put(edge, Math.min(newDistance, shortestDistances.get(edge)));
            }
        }

    }


    public Stack<String> topSort() {

        Set<String> visited = new HashSet<>();
        Stack<String> topoOrder = new Stack<>();
        for (String currNode : graph.keySet())
            dfsTopo(currNode, visited, topoOrder);

       /* while (!topoOrder.isEmpty())
            System.out.print(topoOrder.pop() + " ");*/

        return topoOrder;
    }

    private void dfsTopo(String node, Set<String> visited, Stack<String> topoOrder) {

        if (visited.contains(node)) return;

        visited.add(node);
        for (String childNode : graph.get(node).keySet())
            dfsTopo(childNode, visited, topoOrder);

        topoOrder.push(node);

    }


    public void findSingleSourceShortestPathUsingDijkstraAlgo(String name) {

        Set<String> visited = new HashSet<>();
        Map<String, Integer> nodeAndDistance = new TreeMap<>();
        graph.keySet().forEach(node -> nodeAndDistance.put(node, null));
        Queue<DijkstraDist> currDistances = new PriorityQueue<>((dt1, dt2) -> dt1.distance.compareTo(dt2.distance));
        currDistances.offer(new DijkstraDist(name, 0));
        nodeAndDistance.put(name,0);
        while (!currDistances.isEmpty()) {
            DijkstraDist currNode = currDistances.poll();
            if (visited.contains(currNode.node)) continue;
            for (String child : graph.get(currNode.node).keySet()) {
                int newDist = nodeAndDistance.get(child) == null ?
                        currNode.distance + graph.get(currNode.node).get(child) :
                        Math.min(nodeAndDistance.get(child), currNode.distance + graph.get(currNode.node).get(child));
                nodeAndDistance.put(child, newDist);
                currDistances.offer(new DijkstraDist(child, newDist));
            }
            visited.add(currNode.node);
        }
        System.out.println("Shortest Distance from Node '"+name+"' : "+nodeAndDistance);
    }

    public static WeightedGraph getSampleGraph() {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode(A);
        graph.addNode(B);
        graph.addNode(C);
        graph.addNode(D);
        graph.addNode(E);
        graph.addNode(F);
        graph.addNode(G);
        graph.addNode(H);


        graph.addEdge(A, B, 3);
        graph.addEdge(A, C, 6);

        graph.addEdge(B, C, 4);
        graph.addEdge(B, D, 4);
        graph.addEdge(B, E, 11);

        graph.addEdge(C, D, 8);
        graph.addEdge(C, G, 11);

        graph.addEdge(D, E, -4);
        graph.addEdge(D, F, 5);
        graph.addEdge(D, G, 2);

        graph.addEdge(E, H, 9);

        graph.addEdge(F, H, 1);

        graph.addEdge(G, H, 2);

        return graph;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("[\n");
        for (String node : graph.keySet()) {
            s.append("    " + node + "-->" + graph.get(node) + "\n");
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
		/*WeightedGraph graph = WeightedGraph.getSampleGraph();
		System.out.println(graph);
		graph.topSort();
		graph.findSingleSourceShortestPath(A);*/

        WeightedGraph graph = new WeightedGraph();
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("7");


        graph.addEdge("0", "1", 3);
        graph.addEdge("0", "1", 3);
        graph.addEdge("0", "1", 3);
        graph.addEdge("0", "1", 3);
        graph.addEdge("0", "2", 2);
        graph.addEdge("0", "5", 3);
        graph.addEdge("1", "3", 1);
        graph.addEdge("1", "2", 6);
        graph.addEdge("2", "3", 1);
        graph.addEdge("2", "4", 10);
        graph.addEdge("3", "4", 5);
        graph.addEdge("5", "4", 7);

        graph.addEdge("6", "7", 7);

        System.out.println(graph.topSort());
       // graph.findSingleSourceShortestPathUsingTopoSort("0");
        graph.findSingleSourceShortestPathUsingDijkstraAlgo("0");
    }


    private static class DijkstraDist {
        public String node;
        public Integer distance;

        public DijkstraDist(String node, Integer distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}

