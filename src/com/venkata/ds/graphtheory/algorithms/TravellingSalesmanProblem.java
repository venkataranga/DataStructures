package com.venkata.ds.graphtheory.algorithms;

import com.venkata.ds.graphtheory.IllegalGraphOperation;

import java.util.*;
/**
 *
 * Problem Statement: In a <i>WEIGHTED, COMPLETE graph</i> find the shortest path from a node such that it visits every other node exactly once and return back to starting node. &nbsp
 * <Pre>
 * Lets say G = (V, E) is the given graph
 * This problem can be solved using the formulae g(i,s) = min{ cost(i,j)+g(j, {s-j}) } (refer to this link on how the formulae is deduced https://www.youtube.com/watch?v=XaXsJJh-Q5Y&ab_channel=AbdulBari)
 *      where i         = starting node (from which we have to find the shortest path)
 *            s         = s is a subset of vertices such that s = V - i (All the Nodes in the Graph except i)
 *            j         = j is a node from the subset s
 *            cost(i,j) = edge weight between i & j
 * </Pre>
 *
 * Since this is a complete(dense) graph, we should use Adjacency Matrix to represent the graph
 * @author venkataranga
 * */
public class TravellingSalesmanProblem {

    //List of nodes in the Graph
    List<Integer> nodes;

    //2D array representing Graph with their edge weights
    private Double[][] graph;

    //In the constructor initializing the array and setting the default distances between all nodes to +ve infinity
    public TravellingSalesmanProblem(Integer noOfNodes){
        nodes = new ArrayList<>();
        graph = new Double[noOfNodes][noOfNodes];
        for (int i=0; i<noOfNodes; i++){
            nodes.add(i);
            for (int j=0; j<noOfNodes; j++)
                graph[i][j] = Double.POSITIVE_INFINITY;
        }
    }

    public void addEge(Integer from, Integer to, double edgeWeight) throws IllegalGraphOperation {
        if(from >= nodes.size() || to >=nodes.size())
            throw new IllegalGraphOperation("Source or destination is out of index");
        graph[from][to] = edgeWeight;
    }

    /**
     * Find's the shortest distance using the formulae mentioned above
     * @param from: Starting node from which shortest distance to be calculated as per TSP problem statement
     * */
    public Double travellingSalesman(Integer from){
        //As per the formulae, creating subset of nodes except the starting node
        List<Integer> restOfNodesAfterRemovingStart = new ArrayList<>(nodes.size()-1);
        //using streams API to filter the starting nodes and adding remaining nodes
        nodes.stream().filter(node -> node != from).forEach(restOfNodesAfterRemovingStart::add);
        Double shortestDistance = tsp(from, from, restOfNodesAfterRemovingStart);
        return shortestDistance;
    }

    /**
     * This is a recursive method designed as per the formulae
     * @param from starting node from which we have to calculate the shortest distance (corresponds to i in the formulae)
     * @param start starting node of this recursion. ( corresponds to j in the formulae)
     * @param restOfNodesAfterRemovingStart remaining node in the recurrsion after removing start node (corresponds to {s-j})in the formulae
     *
     * */
    private  Double tsp(Integer from, Integer start, List<Integer> restOfNodesAfterRemovingStart){

        // Condition to break the recursion. If restOfNodesAfterRemovingStart is empty, then we have traversed all the nodes and need to find the
        // distance from end to the starting node (because after visiting every other node from the starting node, we have to come back to start node)
        if(restOfNodesAfterRemovingStart.isEmpty())
            return graph[start][from];

        //Using Priority queue to get the minimum of all the values in that level
        Queue<Double> distance = new PriorityQueue<>();
        for (int i : restOfNodesAfterRemovingStart){
            System.out.println("O List Size: "+restOfNodesAfterRemovingStart.size());
            List<Integer> remainingNodes = new ArrayList<>();

            restOfNodesAfterRemovingStart.stream().filter(node -> node!=i).forEach(remainingNodes::add);
           // System.out.println("R List Size: "+remainingNodes.size());

            // adding the distance to PQ
            distance.add(graph[start][i]+tsp(from,i,remainingNodes));

        }
        //Since we are using PQ, min distance is always at the end of the queue. poll() return the min of all calculated values
        return distance.poll();
    }

    public static void main(String[] args) throws IllegalGraphOperation {

        TravellingSalesmanProblem problem = new TravellingSalesmanProblem(4);
        problem.addEge(0, 0, 0);
        problem.addEge(1, 1,0);
        problem.addEge(2, 2,0);
        problem.addEge(3, 3,0);

        problem.addEge(0, 1,10);
        problem.addEge(0, 2,15);
        problem.addEge(0, 3,20);

        problem.addEge(1, 0,5);
        problem.addEge(1, 2,9);
        problem.addEge(1, 3,10);

        problem.addEge(2, 0,6);
        problem.addEge(2, 1,13);
        problem.addEge(2, 3,12);

        problem.addEge(3, 0,9);
        problem.addEge(3, 1,9);
        problem.addEge(3, 2,9);

        long timeInMillSec = System.currentTimeMillis();

        Double shortestDistance = problem.travellingSalesman(0);
        System.out.println("Min cost of TSP: "+ shortestDistance);
        System.out.println("Time taken: "+(System.currentTimeMillis() - timeInMillSec));
        
    }

}
