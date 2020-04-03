package node;

import javafx.util.Pair;

import java.util.*;

public class Node {

    String name;
    Map<Node, Edge> edges = new HashMap<>();
    boolean visited = false;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Map<Node, Edge> edges) {
        this.name = name;
        this.edges = edges;
    }

    public Pair<Node, Edge> nextMinimum() {
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Node nextVertex = this;
        Iterator<Map.Entry<Node,Edge>> it = edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Node,Edge> pair = it.next();
            if (!pair.getKey().isVisited()) {
                if (!pair.getValue().isIncluded()) {
                    if (pair.getValue().getEdgeLength() < nextMinimum.getEdgeLength()) {
                        nextMinimum = pair.getValue();
                        nextVertex = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextVertex, nextMinimum);
    }

    public String includedToString(){
        StringBuilder sb = new StringBuilder();
        if (isVisited()) {
            Iterator<Map.Entry<Node,Edge>> it = edges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Node,Edge> pair = it.next();
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isPrinted()) {
                        sb.append(getName());
                        sb.append(" --- ");
                        sb.append(pair.getValue().getEdgeLength());
                        sb.append(" --> ");
                        sb.append(pair.getKey().getName());
                        sb.append("\n");
                        pair.getValue().setPrinted(true);
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    public void addEdge(Node node, Edge edge) {
        edges.put(node, edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
