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
        System.out.println("First node shortest()");
        Edge shortestEdge = edges.get(1);
        List<Edge> edgeList = new ArrayList<>();
        for(Edge e : edges) {
            if(e.edgeLength > 0) {
                if(e.getEdgeLength() < shortestEdge.edgeLength) {
                    shortestEdge = e;
                }
            }
        }

        for(Edge e : edges) {
            if(e.edgeLength == shortestEdge.edgeLength) {
                System.out.println("e.edgeLength = " + e.edgeLength);
                edgeList.add(e);
            }
        }
        System.out.println("\tedgeList size: " + edgeList.size());
        return edgeList;
    }

    public List<Edge> getShortestEdge(List<Edge> results) {
        System.out.println("\tChecking for cycles");
        Edge shortestEdge = edges.get(1);
        List<Edge> edgeList = new ArrayList<>();
        for(Edge e : edges) {
            if(e.edgeLength > 0) {
                // Sjekk at denne edgen ikke lager en krets
                if(e.getEdgeLength() < shortestEdge.edgeLength) {
                    boolean newShortest = nonCycle(results, e);
                    // Om den ikke lager en krets kan vi oppdatere hvilken edge som er den nye korteste
                    if(newShortest) {
                        shortestEdge = e;
                    }
                }
            }
        }

        for(Edge e : edges) {
            if(nonCycle(results, e) && !results.contains(e) && e.edgeLength == shortestEdge.edgeLength) {
                System.out.println("e.edgeLength = " + e.edgeLength);
                edgeList.add(e);
            }
        }
        System.out.print("\t\tedgeList returned: ");
        for(Edge print : edgeList) {
            System.out.print(print.start.getName() + ":" + print.end.getName());
        }
        System.out.println();
        System.out.println("\tedgeList size: " + edgeList.size());
        return edgeList;
    }

    private boolean nonCycle(List<Edge> results, Edge e) {
        System.out.println("results used in nonCycle()");
        System.out.println("\tCurrent edge: " + e.getEdgeLength());
        for(Edge e2 : results) {
            System.out.println("\t e   end: " + e.getEnd().getName());
            System.out.println("\te2 start: " + e.getStart().getName());
            if(e.getEnd() == e2.getStart()) {
                System.err.println("Cycle between: " + e.getEnd().getName() + " and " + e2.getStart().getName());
                return false;
            }
        }
        return true;
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
