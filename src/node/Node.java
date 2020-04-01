package node;

import java.util.ArrayList;
import java.util.List;

public class Node {

    String name;
    boolean visited;
    boolean finished;
    List<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getShortestEdge() {
        Edge shortestEdge = edges.get(1);
        List<Edge> edgeList = new ArrayList<>();
        for (Edge e : edges) {
            if (e.edgeLength > 0) {
                if (e.getEdgeLength() < shortestEdge.edgeLength) {
                    shortestEdge = e;
                }
            }
        }

        for (Edge e : edges) {
            if (e.edgeLength == shortestEdge.edgeLength) {
                System.out.println("e.edgeLength = " + e.edgeLength);
                edgeList.add(e);
            }
        }
        return edgeList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
