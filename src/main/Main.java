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
    List<Node> inital;
    ArrayList<String> finalResults;
    ArrayList<Node> visitedNodes;

    public Main() {
        this.distances = new int[graphSize][graphSize];
        inital = new ArrayList<>();
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
            inital.add(new Node(names[i]));
        }

        System.out.println("inital.size() = " + inital.size());

        Edge ab = new Edge(10);
        inital.get(0).addEdge(inital.get(1), ab);
        inital.get(1).addEdge(inital.get(0), ab);

        Edge ac = new Edge(5);
        inital.get(0).addEdge(inital.get(2), ac);
        inital.get(2).addEdge(inital.get(0), ac);

        Edge ad = new Edge(9999);
        inital.get(0).addEdge(inital.get(3), ad);
        inital.get(3).addEdge(inital.get(0), ad);

        Edge ae = new Edge(3);
        inital.get(0).addEdge(inital.get(4), ae);
        inital.get(4).addEdge(inital.get(0), ae);

        Edge af = new Edge(12);
        inital.get(0).addEdge(inital.get(5), af);
        inital.get(5).addEdge(inital.get(0), af);

        Edge bc = new Edge(17);
        inital.get(1).addEdge(inital.get(2), bc);
        inital.get(2).addEdge(inital.get(1), bc);

        Edge bd = new Edge(9);
        inital.get(1).addEdge(inital.get(3), bd);
        inital.get(3).addEdge(inital.get(1), bd);

        Edge be = new Edge(17);
        inital.get(1).addEdge(inital.get(4), bc);
        inital.get(4).addEdge(inital.get(1), bc);

        Edge bf = new Edge(12);
        inital.get(1).addEdge(inital.get(5), bf);
        inital.get(5).addEdge(inital.get(1), bf);

        Edge cd = new Edge(35);
        inital.get(2).addEdge(inital.get(3), cd);
        inital.get(3).addEdge(inital.get(2), cd);

        Edge ce = new Edge(3);
        inital.get(2).addEdge(inital.get(4), ce);
        inital.get(4).addEdge(inital.get(2), ce);

        Edge cf = new Edge(12);
        inital.get(2).addEdge(inital.get(5), cf);
        inital.get(5).addEdge(inital.get(2), cf);

        Edge de = new Edge(9999);
        inital.get(3).addEdge(inital.get(4), de);
        inital.get(4).addEdge(inital.get(3), de);

        Edge df = new Edge(12);
        inital.get(3).addEdge(inital.get(5), df);
        inital.get(5).addEdge(inital.get(3), df);

        Edge ef = new Edge(12);
        inital.get(4).addEdge(inital.get(5), ef);
        inital.get(5).addEdge(inital.get(4), ef);

        printSetup();

        ArrayList<Node> copy1 = copy(inital);
        ArrayList<Node> copy2 = copy(inital);
        ArrayList<Node> copy3 = copy(inital);
        ArrayList<Node> copy4 = copy(inital);
        ArrayList<Node> copy5 = copy(inital);
        ArrayList<Node> copy6 = copy(inital);

        int[] results = new int[6];
        copy1.get(0).setVisited(true);
        results[0] = prims(copy1);

        copy2.get(1).setVisited(true);
        results[1] = prims(copy2);

        copy3.get(2).setVisited(true);
        results[2] = prims(copy3);

        copy4.get(3).setVisited(true);
        results[3] = prims(copy4);

        copy5.get(4).setVisited(true);
        results[4] = prims(copy5);

        copy6.get(5).setVisited(true);
        results[5] = prims(copy6);

        for(int i = 0; i < results.length; i++) {
            System.out.println("Total : " + results[i]);
        }
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

    public int prims(List<Node> nodes) {
        System.out.println("##############################");
        ArrayList<Node> temp = new ArrayList<>();
        temp.add(inital.get(0));
        // Totale kostnaden å koble alle noder
        int total = 0;
        int times = 0;
        // Fortsett til alle noder er visited
        if(nodes.size() > 0 ) {
            nodes.get(0).setVisited(true);
        }
        while(shouldContinue()) {
            System.out.println("run #" + times++);
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Node nextNode = nodes.get(0);
            for(Node node : nodes) {
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
            temp.add(nextNode);
            total += nextMinimum.getEdgeLength();
        }
        for(Node n : temp) {
            System.out.println(n.getName());
        }

        for(String s : finalResults) {
            System.out.println(s);
        }
        System.out.println("Total: " + total);
        return total;
    }

    public boolean shouldContinue() {
        for(Node n : inital) {
            if(!n.isVisited()) {
                return true;
            }
        }
        return false;
    }

    private void printSetup() {
        for (Node n : inital) {
            System.out.print(n.getName() + ": ");
            Map<Node, Edge> edgeMap = n.getEdges();
            for(Node n2 : n.getEdges().keySet()) {
                System.out.println("\t" + n2.getName() + " : " + edgeMap.get(n2).getEdgeLength());
            }
            System.out.println();
        }
    }
}


