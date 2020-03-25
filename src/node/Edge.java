package node;

public class Edge {
    Node start;
    Node end;
    double edgeLength;

    public Edge(Node start, Node end, double edgeLength) {
        this.start = start;
        this.end = end;
        this.edgeLength = edgeLength;
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    @Override
    public String toString() {

        return "start: " + start.getName() + ", end: " + end.getName() + ", dist: " + edgeLength;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
