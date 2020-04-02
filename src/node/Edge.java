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
        return "[" + start.getName() + " " + end.getName() + "]";
       // return "start: " + start.getName() + ", end: " + end.getName() + ", dist: " + edgeLength;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        return equals((Edge) obj);
    }

    private boolean equals(Edge e) {
        return e.start.equals(this.start) &&
                e.end.equals(this.end) &&
                e.edgeLength == this.edgeLength;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
