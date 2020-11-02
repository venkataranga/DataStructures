package com.venkata.ds.graphtheory.algorithms;

import com.venkata.ds.graphtheory.IllegalGraphOperation;
import java.util.*;
import static com.venkata.ds.graphtheory.Constants.*;

/**
 * Strongly Connected Component (SCC) of a graph is subset of nodes and edges from Original Graph such that you can reach from one node to every other node in SCC.
 * Strongly Connected Graph: Strongly Connected Graph is a Graph in which a node in the Graph can reach to every other node. (We can also say that a Graph with only 1 SCC is a Strongly Connected Graph)
 *
 * In an undirected graph, number of strongly connected components is equal to the number of disconnected components in the graph (because every edge is a bidirectional edge)
 *
 * NOTE: A single node itself can be considered as SCC
 *
 * @author venkataranga
 * */
public class StronglyConnectedComponentsUsingKosaraju {

    private Map<String, HashSet<String>> graph = new HashMap<>();

    public void addEdge(String from, String to) throws IllegalGraphOperation {
        if(!graph.containsKey(from) || !graph.containsKey(to))
            throw new IllegalGraphOperation("Either from node or to node is not in the graph. Please add the node using addNode method");
        graph.get(from).add(to);
    }

    public void addNode(String nodeName){
        if(!graph.containsKey(nodeName))
            graph.put(nodeName, new HashSet<>());
    }

    //Step 1: Performs DFS and add the node to stock after visiting all child nodes
    //Step 2: transpose the graph
    //Step 3: perform dfs again by popping each node from stack created in step 1. Number of time you called DFS will give u number of connected components
    public void findSCC(){

        //Variables initialization
        Set<String> visited = new HashSet<>();
        Stack<String> dfsNodes = new Stack<>();

        //Step 1: performing initial DFS
        for(String node : graph.keySet()){
            if(!visited.contains(node))
                dfs(node, dfsNodes, visited, graph);
        }

        //Step 2: Transposing graph
        Map<String, HashSet<String>> transposedGraph = this.transposeGraph();

        //Step 3: Performing DFS in the order of elements returned from stack in step 1.
        visited.clear();
        int numOfSCC = 0;
        while (!dfsNodes.isEmpty()){
            String currNode = dfsNodes.pop();
            if(visited.contains(currNode))
                continue;

            dfs(currNode,null,visited, transposedGraph);
            numOfSCC++;
        }

        System.out.println("Total Number of Strongly Connected Components using Kosaraju's Algorithm: "+numOfSCC);
    }

    //Inorder to reuse the same dfs method for step 1 & 3 added graph (4th) parameter to dfs method. In step 1, we will pass original graph and in step 3 we will pass transposed graph
    // Also in step 3, for the nodes(2nd) parameter, we pass null, so that it will skip adding the nodes to stack
    public void dfs(String node, Stack<String> nodes, Set<String> visited,Map<String, HashSet<String>> graph){

        if(visited.contains(node))
            return;

        visited.add(node);
        for(String child : graph.get(node)){
            dfs(child,nodes,visited, graph);
        }
        if(nodes != null)
            nodes.add(node);
    }

    private Map<String, HashSet<String>> transposeGraph(){

        Map<String, HashSet<String>> transpose = new HashMap<>(graph.size());
        for (String node : graph.keySet()){
            for (String child: graph.get(node)){
                if(!transpose.containsKey(child))
                    transpose.put(child, new HashSet<>());
                transpose.get(child).add(node);
            }
        }
        return transpose;

    }

    public static void main(String[] args) throws IllegalGraphOperation {

        StronglyConnectedComponentsUsingKosaraju graph = new StronglyConnectedComponentsUsingKosaraju();
        graph.addNode(_0);
        graph.addNode(_1);
        graph.addNode(_2);
        graph.addNode(_3);
        graph.addNode(_4);
        graph.addNode(_5);
        graph.addNode(_6);
        graph.addNode(_7);

        graph.addEdge(_0, _1);
        graph.addEdge(_1, _2);
        graph.addEdge(_2, _0);

        graph.addEdge(_2, _3);

        graph.addEdge(_3, _4);

        graph.addEdge(_4, _5);
        graph.addEdge(_4, _7);

        graph.addEdge(_5, _6);
        graph.addEdge(_6, _7);

        graph.addEdge(_6, _4);

        graph.findSCC();

    }
}
