package dijkstra;

import dijkstra.graph.Graph;
import dijkstra.nodes.Node;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class Run {

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        addDestinations(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);

        Dijkstra dijkstra = new Dijkstra();
        Graph graph = getGraph(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);
        graph = Dijkstra.calculateShortestPathFromSource(dijkstra.getSettledNodes(), graph, nodeC);
        printShortestPathForGraph(dijkstra);











    }

    /**
     * Prints the max cost of the settled nodes, based  entry node.
     * Max cost here, is the cheapest path from entry node to all.
     * @param dijkstra is parsed with its settledNodes HashSet.
     */
    private static void printShortestPathForGraph(Dijkstra dijkstra) {
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        dijkstra.getSettledNodes().iterator().forEachRemaining(i -> System.out.println( i.getName() + " " + i.getDistance()));
        dijkstra.getSettledNodes().iterator().forEachRemaining(i -> sum.updateAndGet(v -> v + i.getDistance()));
        System.out.println("Max cost of path: " + dijkstra.getSettledNodes().stream().max(Comparator.comparing(Node::getDistance)).orElseThrow(NoSuchElementException::new).getDistance());
        System.out.println("sum total of path: " + sum);
    }

    /**
     * adds nodes to Graphs nodes list.
     * @param nodeA
     * @param nodeB
     * @param nodeC
     * @param nodeD
     * @param nodeE
     * @param nodeF
     * @return graph with added nodes
     */
    private static Graph getGraph(Node nodeA, Node nodeB, Node nodeC, Node nodeD, Node nodeE, Node nodeF) {
        Graph graph = new Graph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        return graph;
    }

    private static void addDestinations(Node nodeA, Node nodeB, Node nodeC, Node nodeD, Node nodeE, Node nodeF) {
        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 5);
        nodeA.addDestination(nodeD, 9999);
        nodeA.addDestination(nodeE, 3);
        nodeA.addDestination(nodeF, 12);

        nodeB.addDestination(nodeC, 17);
        nodeB.addDestination(nodeD, 9);
        nodeB.addDestination(nodeE, 17);
        nodeB.addDestination(nodeF, 12);

        nodeC.addDestination(nodeD, 35);
        nodeC.addDestination(nodeE, 3);
        nodeC.addDestination(nodeF, 12);

        nodeD.addDestination(nodeE, 9999);
        nodeD.addDestination(nodeF, 12);

        nodeF.addDestination(nodeA, 12);
    }

    private static void printShortest(Graph graph) {
        int sum = 0;
        for (Node n : graph.getNodes()
        ) {
            System.out.println(n.getName() + n.getDistance());
            sum += n.getDistance();

        }
        System.out.println(sum);
    }
}
