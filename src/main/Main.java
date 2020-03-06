package main;

import node.Node;

import java.util.ArrayList;
import java.util.List;

public class Main {

    int[][] distances;
    List<Node> inital;

    public Main() {
        this.distances = new int[6][6];
        inital = new ArrayList<>();

        distances[0] = new int[]{0, 10, 5, 9999, 3, 12};
        distances[1] = new int[]{-1, 0, 17, 9, 17, 12};
        distances[2] = new int[]{-1, -1, 0, 35, 3, 12};
        distances[3] = new int[]{-1, -1, -1, 0, 999, 12};
        distances[4] = new int[]{-1, -1, -1, -1, 0, 12};
        distances[5] = new int[]{-1, -1, -1, -1, -1, -1, 0};

        
    }

    public static void main(String[] args) {
	// write your code here
    }
}
