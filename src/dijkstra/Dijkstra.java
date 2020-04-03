package dijkstra;

import dijkstra.graph.Graph;
import dijkstra.nodes.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {
    private Set<Node> settledNodes;

    public Dijkstra() {
        this.settledNodes = new HashSet<>();
    }

    /**
     * @param settledNodes is the nodes that settles during runtime
     * @param graph        is the initial graph with all the nodes.
     * @param source       is the targeted start node.
     * @return graph with list of shortest path from source.
     */
    public static Graph calculateShortestPathFromSource(Set<Node> settledNodes, Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
     * returns the node with the lowest distance among the unsettled nodes
     *
     * @param unsettledNodes are the unvisited nodes in the graph
     * @return the node with the lowest distance.
     */
    public static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * Calculates the minDistance from sourceNode.
     *
     * @param evaluationNode
     * @param edgeWeight
     * @param sourceNode
     */
    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
        }
        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
        shortestPath.add(sourceNode);
        evaluationNode.setShortestPath(shortestPath);
    }

    public Set<Node> getSettledNodes() {
        return settledNodes;
    }
}
