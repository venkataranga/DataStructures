package com.venkata.ds.graphtheory.unweightedgraphs;

import com.venkata.ds.graphtheory.Graph;
import com.venkata.ds.graphtheory.IllegalGraphOperation;

import java.util.*;

import static com.venkata.ds.graphtheory.Constants.*;

public class UnWeightedGraphUsingAdjacencyList extends Graph {

    private final List<List<Integer>> adjacencyList = new ArrayList<>();
    private Map<String, Integer> nodes = new HashMap<>();
    private Integer currentIndex = 0;


    public String bfs(String source, String destination) {
        String path = nodes.get(destination)+" ";
        List<Integer> parents = bfsAt(source);
        Integer i = nodes.get(destination);
        //for(Integer i = nodes.get(destination); i!=null; i=parents.get(i)){
         /*   if(parents.get(i) == null) return  null;

            path += parents.get(i)+">--";
            if(parents.get(i) == nodes.get(source))
                return reverseString(path);*/
        while (i != null) {
            path += parents.get(i) ==null? "":parents.get(i)+" ";
            i=parents.get(i);
        }

        path = path.trim();
        System.out.println(path);
        return path.substring(path.length()-1).equals(nodes.get(source))?null:this.reverseString(path);

}

    public List<Integer> bfsAt(String name) {
        List<Integer> parents = new ArrayList<>(adjacencyList.size());
        for (int i = 0; i < adjacencyList.size(); i++)
            parents.add(null);
        Set<Integer> visited = new HashSet<>();
        Integer nodeIndex = nodes.get(name);
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(nodeIndex);
        visited.add(nodeIndex);
        while (!bfsQueue.isEmpty()) {

            int currNodeIndex = bfsQueue.poll();
            System.out.print(currNodeIndex + " ");

            for (Integer childNoe : adjacencyList.get(currNodeIndex)) {
                if (!visited.contains(childNoe)) {
                    bfsQueue.offer(childNoe);
                    visited.add(childNoe);
                    parents.set(childNoe, currNodeIndex);
                }
            }

        }
        System.out.println();
        return parents;
    }

    public synchronized void addNode(String name) {
        adjacencyList.add(new LinkedList<>());
        nodes.put(name, currentIndex);
        currentIndex++;
    }

    public void addEdge(String source, String destination) {

        adjacencyList.get(nodes.get(source)).add(nodes.get(destination));
        adjacencyList.get(nodes.get(destination)).add(nodes.get(source));
    }

    public void removeEdge(String source, String destination) throws IllegalGraphOperation {
        throw new IllegalGraphOperation("Remove Edge Not supported");
    }

    public void removeNode(String name) throws IllegalGraphOperation {
        throw new IllegalGraphOperation("Remove Edge Not supported");
    }

    public void removeNodeAndEdges(String name) throws IllegalGraphOperation {
        throw new IllegalGraphOperation("Remove Edge Not supported");
    }


    public static UnWeightedGraphUsingAdjacencyList createSampleGraph() {

        UnWeightedGraphUsingAdjacencyList graph = new UnWeightedGraphUsingAdjacencyList();
        graph.addNode(A);
        graph.addNode(B);
        graph.addNode(C);
        graph.addNode(D);
        graph.addNode(E);
        graph.addNode(F);

        graph.addEdge(A, B);
        graph.addEdge(A, F);

        graph.addEdge(B, C);
        graph.addEdge(C, D);
        graph.addEdge(E, F);

        graph.addEdge(E, D);

        return graph;

    }

    public String reverseString(String input) {
        byte[] strAsByteArray = input.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        // Store result in reverse order into the
        // result byte[]
        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];
        return new String(result);
    }

    @Override
    public String toString() {
        String s = "Graph {\n";

        for (int i = 0; i < adjacencyList.size(); i++) {
            s += "    " + i + "-->" + adjacencyList.get(i) + "\n";
        }

        s += "\n}";
        return s;
    }
}
