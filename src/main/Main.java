package main;

import node.Edge;
import node.Node;

import java.util.ArrayList;
import java.util.List;

public class Main {

    int[][] distances;
    List<Node> inital;
    boolean finished = false;
    ArrayList<ArrayList<Edge>> finalResults;
    ArrayList<Node> visitedNodes;

    public Main() {
        this.distances = new int[6][6];
        inital = new ArrayList<>();
        finalResults = new ArrayList<>();
        visitedNodes = new ArrayList<>();
        String[] names = {"A", "B", "C", "D", "E", "F"};

        distances[0] = new int[]{0, 10, 5, 9999, 3, 12};
        distances[1] = new int[]{-1, 0, 17, 9, 17, 12};
        distances[2] = new int[]{-1, -1, 0, 35, 3, 12};
        distances[3] = new int[]{-1, -1, -1, 0, 999, 12};
        distances[4] = new int[]{-1, -1, -1, -1, 0, 12};
        distances[5] = new int[]{-1, -1, -1, -1, -1, 0};

        for (int i = 0; i < distances[0].length; i++) {
            inital.add(new Node(names[i]));
        }

        System.out.println("inital.size() = " + inital.size());

        for (int y = 0; y < distances[0].length; y++) { // først  -> x[0] ->  ned y[] -> x[1] -> ned y[]
            for (int x = 0; x < distances[0].length; x++) {
                if (distances[x][y] <= 0) {
                    break;
                } else {
                    Edge edge = new Edge(inital.get(x), inital.get(y), distances[x][y]);
                    Node node = inital.get(x);
                    node.addEdge(edge);
                }
            }
        }

        for (int x = distances[0].length - 1; x >= 0; x--) {
            for (int y = distances[0].length - 1; y >= 0; y--) {
                if (distances[x][y] == -1) {
                    break;
                } else {
                    int distance = distances[x][y];
                    if (distance == 0) {
                        break;
                    } else {
                        Edge edge = new Edge(inital.get(y), inital.get(x), distances[x][y]);
                        Node node = inital.get(y);
                        node.addEdge(edge);
                    }
                }
            }
        }

        /*      0: ArrayList<ArrayList<Edge>> finalResultList = new ArrayList<>();
                1: pick a node.
                2: set visited true
                3: loop over each node to find shortest path from n to remaining.
                    for (Node n : initial) {
                        if (n.visited && !n.finished) {
                       4: List<Edge> e = getShortestEdge()
                            if (e.size() > 1) {
                                ArrayList<Edge> resultList = new ArrayList<>();
                              for (Edge inner : e) {
                                   Edge temp =  inner.end() // F -> 12 A
                                                   F -> 12 B
                                                   f -> 12 C
                                                   f-> 12 D
                                                   f-> 12 E
                                   temp.setVisited(true);
                                   resultList.add(temp);
                                   for (Edge edge : resultList) {
                                        if (edge.start) {

                                        }

                                   }


                              }
                            }
                       5: ArrayList<Edge> resultList = new ArrayList<>();
                       6: resultList.add(e)
                       6: ArrayList<String> results = new ArrayList<>();
                                    results.add(e.toString())
                       7: set end visited true
                                e.getEnd().setVisited(true);
                       8:
                }
            }

            public void prims(List<Node> initial) {
                List<Node> visited = new ArrayList<>();
                for(Node n : initial) {
                    Node startingNode = n;
                    n.setVisited(true);
                    visited.add(n);
                        if(n.getVisited() && !n.getFinished()) {
                            List<Edge> shortest = n.getShortestEdge();
                            if(shortest.size > 1) {
                                for(Edge e : shortest) {
                                    // Node F
                                    Node temp = new Node(n.getName());
                                    // 12 -> A, 12 -> B ...
                                    temp.addEdge(e);
                                    List<Node> n2 = new ArrayList<>();
                                    for(Node newNode : initial) {
                                        // Ikke ta med F på nytt, "F" med kun 1 edge
                                        if(n3 != n) {
                                            n2.add(newNode);
                                        }
                                    }
                                    prims(n2);
                                }
                            } else {
                                e.getEnd().setVisited(true);
                                if(!visited.contains(shortest.getStart()) {
                                    // velge korteste igjen
                                }
                            }
                        }
                }
            }
         */
        // teste på F som første node
        inital.get(0).setVisited(true);
        visitedNodes.add(inital.get(0));
        prims(inital);
        // Reset etter hver runde, eller håndter alt i prims?
    }

    public Main(boolean testing) {
        Node node = new Node("A");
        node.addEdge(new Edge(node, node, 12));
        node.addEdge(new Edge(node, node, 12));
        node.addEdge(new Edge(node, node, 10));
        node.addEdge(new Edge(node, node, 5));
        node.addEdge(new Edge(node, node, 3));

        //List<Edge> testList = node.getShortestEdge();
        //System.out.println("testList.size() = " + testList.size());

    }

    public static void main(String[] args) {
        new Main();
    }

    public void prims(List<Node> nodes) {
        printSetup();
        System.out.println("##############################");
        ArrayList<Edge> temp = new ArrayList<>();
        int times = 0;
        // Fortsett til alle noder er visited
        while(shouldContinue()) {
            System.out.println("run #" + times++);
            for(Node n : nodes) {
                System.out.println("Current node from initial: " + n.getName());
                if(n.isVisited()) {
                    System.out.println(n.getName() + " is visited.");
                    List<Edge> shortest;
                    // Finn korteste edge blant alle noder som er visited
                    if(visitedNodes.size() > 1) {
                        shortest = n.getShortestEdge(temp);
                        List<Edge> tempShortest;
                        for (Node a : visitedNodes) {
                            // Denne må ta hensyn til å ikke lage kretser
                            tempShortest = a.getShortestEdge(temp);
                            // Funnet ny kortest
                            if(tempShortest.get(0).getEdgeLength() < shortest.get(0).getEdgeLength()) {
                                shortest = tempShortest;
                            }
                        }
                    } else {
                        // Denne kjøres den første gangen, når det kun er 1 visited node
                        shortest = n.getShortestEdge();
                    }
                    // Hvis det er duplikater av den korteste, må vi teste hver duplikat da hvem vi velger kan forandre
                    // utfallet.
                    if(shortest.size() > 1) {

                        // Kun 1 kortest edge
                    } else {
                        Edge add = shortest.get(0);
                        System.out.println("\tAdding to temp: " + add.getEnd().getName());
                        add.getEnd().setVisited(true);
                        visitedNodes.add(add.getEnd());
                        //n.getEdges().remove(add);
                        temp.add(shortest.get(0));
                    }
                }
            }

            if (visitedNodes.size() == nodes.size()) {
                finished = true;
            }
            for (Edge e : temp) {
                System.out.println(e.toString());
            }
        }

    }

    public boolean shouldContinue() {
        for(Node n : inital) {
            if(!n.isVisited()) {
                return true;
            }
        }
        return false;
    }

    private void printSetup() {
        for (Node n : inital) {
            System.out.print(n.getName() + ": ");
            for (Edge e : n.getEdges()) {
                System.out.println("\t" + e);
            }
            System.out.println();
        }
    }



}


