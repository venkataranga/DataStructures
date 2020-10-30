package com.venkata.ds.graphtheory.unweightedgraphs;

import com.venkata.ds.graphtheory.GraphBuiler;
import com.venkata.ds.graphtheory.GraphType;
import com.venkata.ds.graphtheory.IllegalGraphOperation;
import static com.venkata.ds.graphtheory.Constants.*;

public class UnWeightedGraphTest {

    public static void main(String[] args) throws IllegalGraphOperation {

        UnWeightedGraph graph = (UnWeightedGraph) GraphBuiler.build(GraphType.CYCLIC_UNWEIGHTED);

        System.out.println(graph + "\n********************");

        graph.performDFS();
        graph.performDFSMaintainingVisitedNodes();
        graph.dfs();
        System.out.println("********************");

        System.out.print("DFS starting at A: ");
        graph.dfsAt(A);
        System.out.print("DFS starting at B: ");
        graph.dfsAt(B);
        System.out.print("DFS starting at C: ");
        graph.dfsAt(C);
        System.out.print("DFS starting at D: ");
        graph.dfsAt(D);
        System.out.print("DFS starting at E: ");
        graph.dfsAt(E);
        System.out.print("DFS starting at F: ");
        graph.dfsAt(F);
        System.out.print("DFS starting at G: ");
        graph.dfsAt(G);
        System.out.print("DFS starting at H: ");
        graph.dfsAt(H);

        System.out.println("*************");
        // adding disconnected nodes
        graph.addNode(I);
        graph.addNode(J);
        graph.addNode(K);
        graph.findConnectedComponents();

        System.out.println("Cycle Detected: "+graph.detectCycle());

        UnWeightedGraph noCycleGraph = (UnWeightedGraph) GraphBuiler.build(GraphType.UNDIRECTED_UNWEIGHTED);

        System.out.println("Cycle Detected: "+noCycleGraph.detectCycle());
        //graph.removeNodeAndEdges(H);


    }


}
