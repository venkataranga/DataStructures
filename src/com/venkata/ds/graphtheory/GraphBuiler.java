package com.venkata.ds.graphtheory;

import com.venkata.ds.graphtheory.unweightedgraphs.UnWeightedGraph;
import com.venkata.ds.graphtheory.Graph;
import static com.venkata.ds.graphtheory.Constants.*;

public class GraphBuiler {

   public static Graph build(GraphType type){

        switch (type){
            case CYCLIC_UNWEIGHTED:
                UnWeightedGraph graph = new UnWeightedGraph();
                graph.addNode(A);
                graph.addNode(B);
                graph.addNode(C);
                graph.addNode(D);
                graph.addNode(E);
                graph.addNode(F);
                graph.addNode(G);
                graph.addNode(H);

                graph.addEdge(A, B);
                graph.addEdge(A, C);
                graph.addEdge(A, D);

                graph.addEdge(B, E);
                graph.addEdge(B, F);

                graph.addEdge(C, G);

                graph.addEdge(D, H);

                graph.addEdge(E, H);

                graph.addEdge(F, H);

                graph.addEdge(G, H);

                return graph;

            case UNDIRECTED_UNWEIGHTED:
                UnWeightedGraph ug = new UnWeightedGraph();
                ug.addNode(A);
                ug.addNode(B);
                ug.addNode(C);
                ug.addNode(D);
                ug.addNode(E);

                ug.addEdge(A, B);
                ug.addEdge(B, C);
                ug.addEdge(C, D);
                ug.addEdge(D, E);

                return ug;

            default:
                throw new RuntimeException("GraphType Not supported yet");
        }
    }

}
