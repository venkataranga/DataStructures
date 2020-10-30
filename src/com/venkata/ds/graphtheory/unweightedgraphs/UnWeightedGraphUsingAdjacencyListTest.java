package com.venkata.ds.graphtheory.unweightedgraphs;

import static com.venkata.ds.graphtheory.Constants.A;
import static com.venkata.ds.graphtheory.Constants.E;

public class UnWeightedGraphUsingAdjacencyListTest {

    public static void main(String[] args) {
        UnWeightedGraphUsingAdjacencyList graphUsingAdjacencyList = UnWeightedGraphUsingAdjacencyList.createSampleGraph();
        System.out.println(graphUsingAdjacencyList);
        System.out.println(graphUsingAdjacencyList.bfsAt(A));
        System.out.println(graphUsingAdjacencyList.bfs(A,E));
    }

}
