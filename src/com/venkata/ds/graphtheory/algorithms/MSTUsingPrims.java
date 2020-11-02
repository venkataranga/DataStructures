package com.venkata.ds.graphtheory.algorithms;

import com.venkata.ds.graphtheory.IllegalGraphOperation;
import com.venkata.ds.graphtheory.weightedgraphs.Edge;

import java.util.*;

import static com.venkata.ds.graphtheory.Constants.*;

/**
 * Prim's algorithm to find the Minimum Spanning Tree(MST) for Undirected and weighted graphs
 * <pre>
 * MST: Minimum Spanning Tree of a weighted and undirected Graph is a subset G'=(V', E') of the graph G=(V, E) that meets following conditions
 *          1) V' = V and E' = V-1
 *          2) All nodes in the MST are connected without cycles
 *          3) Total cost of the all the edges is minimum of all Spanning Trees
 * </pre>
 * NOTE: A Graph can contain multiple MST's that has same minimum total edge cost
 * This implementation of Graph DOES NOT support PARALLEL edges since you cannot add key with same name
 *
 * @author venkataranga
 * */
public class MSTUsingPrims {

    //This Map represents adjacency List. outter Map is the node and Inner Map is list of edges connected to that node and their weights
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public  void addNode(String name){
        if(!graph.containsKey(name))
            graph.put(name, new HashMap<>());
    }

    public void addEdge(String from, String to, Integer edgeWeight) throws IllegalGraphOperation {

        if(!graph.containsKey(from) || !graph.containsKey(to))
            throw new IllegalGraphOperation("from node or to node is not added to graph. add the nodes first using addNode method");

        // Since this is a undirected graph adding edge to both from and to nodes
        graph.get(from).put(to, edgeWeight);
        graph.get(to).put(from, edgeWeight);
    }


    //You can pass any arbitrary node as the startNode
    /**
     * <pre>
     * Step 1: Start Prims's algorithm with any arbitrary node (this works because MST contains all the nodes from original graph)
     * Step 2: add the node to visited and then add its edges (sorted by lowest weight first) to the priority queue. skip any edge to the node that is already visited
     * Step 3: poll the queue to pick the lowest cost edge and add it to the MST. if the 'to'/'destination' node of the edge is visited, poll again
     * Step 4: Repeat Step 2 & 3 until the queue is empty or you added N-1 edges (N is no of vertices in the Graph)
     * </pre>
     * */
    public void findMSTUsingPrims(String startingNode){

        Queue<Edge> edgesToPickFrom = new PriorityQueue<Edge>((e1, e2)-> e1.getWeight().compareTo(e2.getWeight()));
        Set<String> visited = new HashSet<>();
        processNode(startingNode,visited,edgesToPickFrom);
        int numOfEdgesAdded =0;
        List<Edge> mst = new ArrayList<>();
        while (!edgesToPickFrom.isEmpty() && numOfEdgesAdded != graph.size()-1){
            System.out.println("Current Priority Queue: "+edgesToPickFrom);
            Edge nextEdge = edgesToPickFrom.poll();
            System.out.println("Next Edge: "+nextEdge);
            while (visited.contains(nextEdge.getTo()) && !edgesToPickFrom.isEmpty())
                nextEdge = edgesToPickFrom.poll();
            //processNode(nextEdge.getFrom(), visited, edgesToPickFrom);
            processNode(nextEdge.getTo(), visited, edgesToPickFrom);
            mst.add(nextEdge);
            numOfEdgesAdded++;
            System.out.println("Current Priority Queue: "+edgesToPickFrom);
            System.out.println("Current MST: "+mst);
            System.out.println("****************************************************");
        }

        System.out.println("MST: "+mst.toString());

    }

    public void processNode(String node, Set<String>visited, Queue<Edge> edgesToPickForm){
        visited.add(node);

        for(String toEdgeNode : graph.get(node).keySet()){
            Edge edge = new Edge(node, toEdgeNode, graph.get(node).get(toEdgeNode));
            if(visited.contains(toEdgeNode))
                continue;
            System.out.println("Adding Edge: "+edge+" to priority queue");
            edgesToPickForm.add(edge);
        }
    }

    public static void main(String[] args) throws IllegalGraphOperation {

        MSTUsingPrims graph = new MSTUsingPrims();
        graph.addNode(_1);
        graph.addNode(_2);
        graph.addNode(_3);
        graph.addNode(_4);
        graph.addNode(_5);
        graph.addNode(_6);
        graph.addNode(_7);

        graph.addEdge(_1, _2, 28);
        graph.addEdge(_2, _3, 16);
        graph.addEdge(_3, _4, 12);
        graph.addEdge(_4, _5, 22);
        graph.addEdge(_5, _6, 25);
        graph.addEdge(_6, _1, 10);
        graph.addEdge(_5, _7, 24);
        graph.addEdge(_4, _7, 18);
        graph.addEdge(_2, _7, 14);

        graph.findMSTUsingPrims(_7);

    }

}
