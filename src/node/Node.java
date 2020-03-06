package node;

import java.util.List;

public class Node {

    String name;
    boolean visited;
    List<Edge> edges;

    public Node(String name) {
        this.name = name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
