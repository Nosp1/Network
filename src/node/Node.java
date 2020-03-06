package node;

import java.util.List;

public class Node {

    boolean visited;
    List<Node> adjacent;

    public Node(List<Node> adjacent) {
        this(false, adjacent);
    }

    public Node(boolean visited, List<Node> adjacent) {
        this.visited = visited;
        this.adjacent = adjacent;
    }
}
