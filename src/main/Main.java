package main;

import node.Edge;
import node.Node;

import java.util.ArrayList;
import java.util.List;

public class Main {

    int[][] distances;
    List<Node> inital;

    public Main() {
        this.distances = new int[6][6];
        inital = new ArrayList<>();
        String[] names = {"A", "B", "C", "D", "E", "F"};

        distances[0] = new int[]{0, 10, 5, 9999, 3, 12};
        distances[1] = new int[]{-1, 0, 17, 9, 17, 12};
        distances[2] = new int[]{-1, -1, 0, 35, 3, 12};
        distances[3] = new int[]{-1, -1, -1, 0, 999, 12};
        distances[4] = new int[]{-1, -1, -1, -1, 0, 12};
        distances[5] = new int[]{-1, -1, -1, -1, -1, 0};

        for (int i = 0; i < distances[0].length; i++) {
            inital.add(new Node(names[i]));
        }

        System.out.println("inital.size() = " + inital.size());

        for (int y = 0; y < distances[0].length; y++) { // først  -> x[0] ->  ned y[] -> x[1] -> ned y[]
            for (int x = 0; x < distances[0].length; x++) {
                if (distances[x][y] == -1) {
                    break;
                } else {
                    Edge edge = new Edge(inital.get(x), inital.get(y), distances[x][y]);
                    Node node = inital.get(x);
                    node.addEdge(edge);
                }
            }
        }

        for (int x = distances[0].length - 1; x >= 0; x--) {
            for (int y = distances[0].length - 1; y >= 0; y--) {
                if (distances[x][y] == -1) {
                    break;
                } else {
                    int distance = distances[x][y];
                    if (distance == 0) {
                        break;
                    } else {
                        Edge edge = new Edge(inital.get(y), inital.get(x), distances[x][y]);
                        Node node = inital.get(y);
                        node.addEdge(edge);
                    }
                }
            }
        }

        /*
            step one: pick a node.
            step two: set visited true

            step three: for (Node n : initial) {
                if (n.visited && !n.finished) {
                       step four: getShortestEdge()
                       step five: getToString();
                       step six: set end visited true
                       step six: loop over each node to find shortest path from A to remaining.
                }
            }



         */


    }

    public Main(boolean testing) {
        Node node = new Node("A");
        node.addEdge(new Edge(node, node, 12));
        node.addEdge(new Edge(node, node, 12));
        node.addEdge(new Edge(node, node, 10));
        node.addEdge(new Edge(node, node, 5));
        node.addEdge(new Edge(node, node, 3));

        List<Edge> testList = node.getSmallestEdge();
        System.out.println("testList.size() = " + testList.size());

    }

    public static void main(String[] args) {
        new Main();
    }

    private void printSetup() {
        for (Node n : inital) {
            System.out.print(n.getName() + ": ");
            for (Edge e : n.getEdges()) {
                System.out.println("\t" + e);
            }
            System.out.println();
        }
    }

    /**
     * Forsøk på å sammenligne alle edges i en Node med en annen Node.
     */
    public List<Double> shortestpath(List<Node> nodes) {
        int y = 0;

        List<Double> sumAllEdges = new ArrayList<>();
        while (y < 6) {
            double sum = 0;
            Node n = nodes.get(y);  //henter node y
            for (Edge e : n.getEdges()) { //henter edge list
                System.out.println("e.toString() = " + e.toString());
                sum = e.getEdgeLength(); //summerer alle edges
                System.out.println("sum = " + sum);
                sumAllEdges.add(sum);

                System.out.println("y = " + y);
            }
            y++;
        }
        for (double i : sumAllEdges) {
            System.out.println(i);
        }
        return sumAllEdges;
    }

    public void checkSums(List<Integer> list) {
        //todo compare value of all sums.
        // if sum < sum1. sum is faster
    }
}


