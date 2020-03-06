package node;

public class Edge {
    Node node1;
    Node node2;
    double edgeLength;

    public Edge(Node node1, Node node2, double edgeLength) {
        this.node1 = node1;
        this.node2 = node2;
        this.edgeLength = edgeLength;
    }

    @Override
    public String toString() {
        return "node1: " + node1 + ", node2: " + node2 + ", dist: " + edgeLength;
    }
}
