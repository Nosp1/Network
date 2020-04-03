package main;

import javafx.util.Pair;
import node.Edge;
import node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    int graphSize = 6;
    int[][] distances;
    ArrayList<Node> initial;
    ArrayList<String> finalResults;
    ArrayList<Node> visitedNodes;

    public Main() {
        this.distances = new int[graphSize][graphSize];
        initial = new ArrayList<>();
        finalResults = new ArrayList<>();
        visitedNodes = new ArrayList<>();
        String[] names = {"A", "B", "C", "D", "E", "F"};

        distances[0] = new int[]{0, 10, 5, 9999, 3, 12};
        distances[1] = new int[]{-1, 0, 17, 9, 17, 12};
        distances[2] = new int[]{-1, -1, 0, 35, 3, 12};
        distances[3] = new int[]{-1, -1, -1, 0, 999, 12};
        distances[4] = new int[]{-1, -1, -1, -1, 0, 12};
        distances[5] = new int[]{-1, -1, -1, -1, -1, 0};

        for (int i = 0; i < graphSize; i++) {
            initial.add(new Node(names[i]));
        }

        System.out.println("inital.size() = " + initial.size());

        Edge ab = new Edge(10);
        initial.get(0).addEdge(initial.get(1), ab);
        initial.get(1).addEdge(initial.get(0), ab);

        Edge ac = new Edge(5);
        initial.get(0).addEdge(initial.get(2), ac);
        initial.get(2).addEdge(initial.get(0), ac);

        Edge ad = new Edge(9999);
        initial.get(0).addEdge(initial.get(3), ad);
        initial.get(3).addEdge(initial.get(0), ad);

        Edge ae = new Edge(3);
        initial.get(0).addEdge(initial.get(4), ae);
        initial.get(4).addEdge(initial.get(0), ae);

        Edge af = new Edge(12);
        initial.get(0).addEdge(initial.get(5), af);
        initial.get(5).addEdge(initial.get(0), af);

        Edge bc = new Edge(17);
        initial.get(1).addEdge(initial.get(2), bc);
        initial.get(2).addEdge(initial.get(1), bc);

        Edge bd = new Edge(9);
        initial.get(1).addEdge(initial.get(3), bd);
        initial.get(3).addEdge(initial.get(1), bd);

        Edge be = new Edge(17);
        initial.get(1).addEdge(initial.get(4), be);
        initial.get(4).addEdge(initial.get(1), be);

        Edge bf = new Edge(12);
        initial.get(1).addEdge(initial.get(5), bf);
        initial.get(5).addEdge(initial.get(1), bf);

        Edge cd = new Edge(35);
        initial.get(2).addEdge(initial.get(3), cd);
        initial.get(3).addEdge(initial.get(2), cd);

        Edge ce = new Edge(3);
        initial.get(2).addEdge(initial.get(4), ce);
        initial.get(4).addEdge(initial.get(2), ce);

        Edge cf = new Edge(12);
        initial.get(2).addEdge(initial.get(5), cf);
        initial.get(5).addEdge(initial.get(2), cf);

        Edge de = new Edge(9999);
        initial.get(3).addEdge(initial.get(4), de);
        initial.get(4).addEdge(initial.get(3), de);

        Edge df = new Edge(12);
        initial.get(3).addEdge(initial.get(5), df);
        initial.get(5).addEdge(initial.get(3), df);

        Edge ef = new Edge(12);
        initial.get(4).addEdge(initial.get(5), ef);
        initial.get(5).addEdge(initial.get(4), ef);

        printSetup();
        System.out.println(prims());
        // Reset etter hver runde, eller håndter alt i prims?
    }

    private ArrayList<Node> copy(List<Node> input) {
        ArrayList<Node> list = new ArrayList<>();
        for(Node n : input) {
            Node copy = n;
            list.add(copy);
        }
        return list;
    }

    public static void main(String[] args) {
        new Main();
    }

    public int prims() {
        System.out.println("##############################");
        ArrayList<Node> temp = new ArrayList<>();
        // Totale kostnaden å koble alle noder
        int total = 0;
        int times = 0;
        // Fortsett til alle noder er visited
        if(initial.size() > 0 ) {
            // Vi starter i node A
            Node start = initial.get(0);
            start.setVisited(true);
            temp.add(start);
            System.out.println("Starting with node: " + start.getName());
        }
        while(shouldContinue()) {
            System.out.println("run #" + times++);
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Node nextNode = initial.get(0);
            for(Node node : initial) {
                if(node.isVisited()) {
                    Pair<Node, Edge> candidate = node.nextMinimum();
                    if(candidate.getValue().getEdgeLength() < nextMinimum.getEdgeLength()) {
                        nextMinimum = candidate.getValue();
                        nextNode = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextNode.setVisited(true);
            total += nextMinimum.getEdgeLength();
        }
        for(int i = 0; i < temp.size()-1; i++) {
            System.out.println("[" + temp.get(i).getName() + " " + temp.get(i+1).getName() + "]");
        }

        System.out.println("Total: " + total);

        System.out.println(getResultAsString());
        return total;
    }

    public boolean shouldContinue() {
        for(Node n : initial) {
            if(!n.isVisited()) {
                return true;
            }
        }
        return false;
    }

    private void printSetup() {
        for (Node n : initial) {
            System.out.print(n.getName() + ": ");
            Map<Node, Edge> edgeMap = n.getEdges();
            for(Node n2 : n.getEdges().keySet()) {
                System.out.println("\t" + n2.getName() + " : " + edgeMap.get(n2).getEdgeLength());
            }
            System.out.println();
        }
    }

    private String getResultAsString() {
        StringBuilder sb = new StringBuilder();
        for(Node node : initial) {
            sb.append(node.includedToString());
        }
        return sb.toString();
    }
}


