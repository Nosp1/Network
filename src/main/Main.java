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

        distances[0] = new int[]{ 0, 10,  5, 9999,   3,   12};
        distances[1] = new int[]{-1,  0, 17,    9,  17,   12};
        distances[2] = new int[]{-1, -1,  0,   35,   3,   12};
        distances[3] = new int[]{-1, -1, -1,    0, 999,   12};
        distances[4] = new int[]{-1, -1, -1,   -1,   0,   12};
        distances[5] = new int[]{-1, -1, -1,   -1,  -1,    0};

        for(int i = 0; i < distances[0].length; i++) {
            inital.add(new Node(names[i]));
        }

        System.out.println("inital.size() = " + inital.size());

        for(int y = 0; y < distances[0].length; y++) { // først  -> x[0] ->  ned y[] -> x[1] -> ned y[]
            for(int x = 0; x < distances[0].length; x++) {
                if(distances[x][y] == -1) {
                    break;
                } else {
                    int distance = distances[x][y];
                    if(distance == 0) {
                        break;
                    } else {
                        Edge edge = new Edge(inital.get(x), inital.get(y), distances[x][y]);
                        Node node = inital.get(x);
                        node.addEdge(edge);
                    }
                }
            }
        }

        for(int y = distances[0].length-1; y > 0; y--) {
            for(int x = distances[0].length-1; x > 0; x--) {
                if(distances[x][y] == -1) {
                    break;
                }
                Edge edge = new Edge(inital.get(x), inital.get(y), distances[x][y]);
                Node node = inital.get(x);
                node.addEdge(edge);
            }
        }

        for(Node n : inital) {
            System.out.print(n.getName() + ": ");
            for(Edge e : n.getEdges()) {
                System.out.println("\t" + e);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
	    new Main();
    }
}
