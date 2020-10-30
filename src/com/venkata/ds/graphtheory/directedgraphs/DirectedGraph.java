package com.venkata.ds.graphtheory.directedgraphs;

import java.util.*;

import static com.venkata.ds.graphtheory.Constants.*;

public class DirectedGraph {

    private final Map<String, Integer> nodes = new HashMap<>();
    private final List<List<Integer>> adjList = new ArrayList<>();
    private final List<Integer> indregee = new ArrayList<>();
    private int currIndex = 0;

    public synchronized void addNode(String name){
        nodes.put(name, currIndex);
        adjList.add(new LinkedList<>());
        indregee.add(0);
        currIndex++;
    }

    public void addEdge(String source, String destination){
        adjList.get(nodes.get(source)).add(nodes.get(destination));
        indregee.set(nodes.get(destination), indregee.get(nodes.get(destination))+1);
    }

    @Override
    public String toString() {
        String s = "Graph {\n";

        for (int i = 0; i < adjList.size(); i++) {
            s += "    " + i + "-->" + adjList.get(i) + "\n";
        }

        s += "\n}";
        return s;
    }

    public void topoOrderUsingIndegree(){
        System.out.println("Initial Indegree: "+indregee);

        Set<Integer> allNodes = new HashSet<>();
        allNodes.addAll(nodes.values());

        List<Integer> currentIndegree = new ArrayList<>();
        currentIndegree.addAll(indregee);

        String topoOrder = "";

        while (!allNodes.isEmpty()){

            Integer zeroIndegree = findNodeWithZeroIndegree(currentIndegree, allNodes);
            System.out.println("zeroIndegree: "+zeroIndegree);
            allNodes.remove(zeroIndegree);
            topoOrder = topoOrder+zeroIndegree+" ";
            updateIndegreeAfterRemovingNode(zeroIndegree, currentIndegree);
            System.out.println("Updated Indegree: "+currentIndegree);
        }

        System.out.println("TopoOrder using indegree: "+topoOrder);
    }


    private void updateIndegreeAfterRemovingNode(Integer zeroIndegreeNode, List<Integer> currentIndegree) {
        //System.out.println("Edge for "+zeroIndegreeNode+":"+ adjList.get(zeroIndegreeNode));
        for(Integer destEdge : adjList.get(zeroIndegreeNode)){
            currentIndegree.set(destEdge, currentIndegree.get(destEdge)-1);
        }

    }

    private Integer findNodeWithZeroIndegree(List<Integer> currentIndegree, Set<Integer> allNodes) {
        for(int i=0; i<currentIndegree.size(); i++)
            if(currentIndegree.get(i) ==0 && allNodes.contains(i))
                return i;

        return -1;
    }


    public void allTopoOrderUsingIndegree(){
        System.out.println("Initial Indegree: "+indregee);

        Set<Integer> allNodes = new HashSet<>();
        allNodes.addAll(nodes.values());

        List<Integer> currentIndegree = new ArrayList<>();
        currentIndegree.addAll(indregee);

        List<String> allTopoOrders = new ArrayList<>();
        String topoOrder = "";
        allTopoOrders.add(topoOrder);

        while (!allNodes.isEmpty()){

            List<Integer> zeroIndegree = findAllNodesWithZeroIndegree(currentIndegree, allNodes);
            List<String> newIterativeTopoOrders =  new ArrayList<>();
            for(Integer i: zeroIndegree){
                for(String tempTopo : allTopoOrders){
                    newIterativeTopoOrders.add(tempTopo+" "+i+" ");
                }
            }
            allTopoOrders.clear();
            allTopoOrders.addAll(newIterativeTopoOrders);
           // System.out.println("zeroIndegree: "+zeroIndegree);

            zeroIndegree.stream().forEach(i -> {
                updateIndegreeAfterRemovingNode(i, currentIndegree);
                allNodes.remove(i);
            });
            //System.out.println("Updated Indegree: "+currentIndegree);
        }

       allTopoOrders.forEach(order -> System.out.println(order));
    }

    private List<Integer> findAllNodesWithZeroIndegree(List<Integer> currentIndegree, Set<Integer> allNodes) {
        List<Integer> allZeroDegreeIndexes = new ArrayList<>();
        for(int i=0; i<currentIndegree.size(); i++)
            if(currentIndegree.get(i) ==0 && allNodes.contains(i))
                allZeroDegreeIndexes.add(i);

        return allZeroDegreeIndexes;
    }


    //TODO: this is incorrect implementation. This works for single topoOrder (if removed for loop) but doesn't give all topo orders
    public void allTopoOrders(){

        List<Stack<Integer>> allTopoOrders = new LinkedList<>();
        List<List<Integer>> currAdjList = new ArrayList<>();
        currAdjList.addAll(adjList);

        for(int i =0; i<currAdjList.size(); i++){

            //swap elements
            if(i > 0) {
                List<Integer> temp = currAdjList.get(i);
                currAdjList.set(i, currAdjList.get(0));
                currAdjList.set(0, temp);
            }
            allTopoOrders.add(topologicalSort(currAdjList));
        }

       // System.out.println(allTopoOrders);
        for(Stack<Integer> topoOrder : allTopoOrders){
            while (!topoOrder.isEmpty())
                System.out.print(topoOrder.pop()+" ");
            System.out.println();
        }

    }

    public Stack<Integer> topologicalSort(List<List<Integer>> currAdjList){

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> topoOrder = new Stack<>();
        for (int i=0; i< currAdjList.size(); i++){
            dfsTopo(i, currAdjList, visited, topoOrder);
            //System.out.println("Stack state: "+visited+" after Calling DFS for: "+i);
        }
       /* while (!topoOrder.isEmpty())
            System.out.print(topoOrder.pop()+" "),;*/

        System.out.println();
        return  topoOrder;
    }

    public void dfsTopo(Integer node, List<List<Integer>> currAdjList, Set<Integer> visited, Stack<Integer> topoOrder){
        if(visited.contains(node)) return;

        visited.add(node);
        for(Integer childNode : currAdjList.get(node))
            dfsTopo(childNode,currAdjList, visited, topoOrder);
        topoOrder.push(node);

    }

    public static DirectedGraph getSampleGraph(){
        DirectedGraph graph = new DirectedGraph();
        graph.addNode(A);
        graph.addNode(B);
        graph.addNode(C);
        graph.addNode(D);
        graph.addNode(E);
        graph.addNode(F);
        graph.addNode(G);
        graph.addNode(H);
        graph.addNode(I);
        graph.addNode(J);

        graph.addEdge(A, B);
        graph.addEdge(A, D);

        graph.addEdge(B, E);

        graph.addEdge(C, F);

        graph.addEdge(D, E);
        graph.addEdge(D, F);

        graph.addEdge(E, G);
        graph.addEdge(E, H);

        graph.addEdge(F, G);
        graph.addEdge(F, I);

        graph.addEdge(G, I);
        graph.addEdge(G, J);

        graph.addEdge(H, J);


        return graph;
    }

    public static void main(String[] args) {

        DirectedGraph graph = DirectedGraph.getSampleGraph();
        System.out.println(graph);
        System.out.println("******************");
        //graph.topologicalSort(adjL);
        //graph.allTopoOrders();
        //graph.topoOrderUsingIndegree();
        graph.allTopoOrderUsingIndegree();

    }
}
