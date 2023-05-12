
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.Collection;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Graph class: evaluate shortest paths.
 * 
 * CONSTRUCTION: with no parameters.
 * 
 * PUBLIC OPERATIONS
 * void addEdge( String v, String w, double cvw )
 * Add additional edge
 * void printPath( String w ) Print path after alg is run
 * void dijkstra( String s ) Single-source weighted
 * ERRORS
 * Some error checking is performed to make sure graph is ok,
 * and to make sure graph satisfies properties needed by each
 * algorithm. Exceptions are thrown if errors are detected.
 */

public class Graph {

    // Counting operations of Dijkstra's algorithim
    /**
     * priority queue operations
     */
    int operations_pq = 0;
    /**
     * vertex operations
     */
    int operations_v = 0;
    /**
     * edge operations
     */
    int operations_e = 0;

    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    private int vertices_;

    /**
     * Add a new edge to the graph.
     */
    public void addEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        v.adj.add(new Edge<Vertex>(w, cost));
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null)
            throw new NoSuchElementException("Destination vertex not found");
        else if (w.dist == Vertex.INFINITY)
            System.out.println(destName + " is unreachable");
        else {
            System.out.print("(Cost is: " + w.dist + ") ");
            printPath(w);
            System.out.println();
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath(Vertex dest) {
        if (dest.prev != null) {
            printPath(dest.prev);
            System.out.print(" to ");
        }
        System.out.print(dest.name);
    }

    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll() {
        for (Vertex v : vertexMap.values())
            v.reset();
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra)
     * using priority queues based on the binary heap
     */
    public void dijkstra(String startName) {
        PriorityQueue<Path> pq = new PriorityQueue<Path>();

        Vertex start = vertexMap.get(startName);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");

        clearAll();
        pq.add(new Path(start, 0));
        start.dist = 0;

        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {

            // Priority queue proccessed
            operations_pq += (int) (Math.log(pq.size()) / Math.log(2));

            Path vrec = pq.remove();
            Vertex v = vrec.dest;
            if (v.scratch != 0) // already processed v
                continue;

            // Vertex being processed
            operations_v++;

            v.scratch = 1;
            nodesSeen++;

            for (Edge<Vertex> e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (cvw < 0)
                    throw new GraphException("Graph has negative edges");

                // Edge being proccessed
                operations_e++;

                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    pq.add(new Path(w, w.dist));

                    // Priority queue processed
                    operations_pq += (int) (Math.log(pq.size()) / Math.log(2));
                }
            }
        }
    }

    /**
     * A main routine that:
     * 1. Read files containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     * runs the shortest path algorithm.
     * The data files is a sequence of lines of the format
     * source destination cost
     *
     * Except for the first line which is number of Vertices, Edges and Theroretical
     * time complexity of Dijkstra's algorithm of the graph
     *
     * Main then stores the number of Vertices and Edges of each graph. Also number
     * of operations on Vertices, number of operations of Edges and The number of
     * operations in the priority queue of Dijkstra's algorithm
     * and Theoretical time complexity of Dijkstra's algorithm of each graph in a
     * text file called "Data for complexity Comparison". This is all stored in this
     * order.
     *
     * This text file will be used to compare the theoretical time complexity with
     * the actual time it takes Dijkstra's algorithm to complete.
     */
    public void main() {

        try {

            File folder = new File("data"); // replace with the path to your folder
            File[] files = folder.listFiles();

            // Edges,Vertices, time comp for random Graph
            String Edges_g = null;
            String Vertices_g = null;
            String Theoretical_Complexity_g = null;
            String startNode = null;

            String filename = "Data for complexity Comparison";

            File directory = new File("data");
            File file__ = new File(directory, filename);

            FileWriter fwr = new FileWriter(file__);
            BufferedWriter bwr = new BufferedWriter(fwr);

            for (File file : files) {

                if (compareFiles(file, file__)) {
                    continue;
                }

                try {

                    Graph g = new Graph();

                    FileReader fin = new FileReader(file);
                    Scanner graphFile = new Scanner(fin);

                    // Read the edges and insert
                    String line;

                    int i = 0;

                    while (graphFile.hasNextLine()) {
                        line = graphFile.nextLine();
                        StringTokenizer st = new StringTokenizer(line);

                        if (i == 0) {
                            Vertices_g = st.nextToken();
                            Edges_g = st.nextToken();
                            Theoretical_Complexity_g = st.nextToken();

                        } else if (i > 0) {

                            try {
                                if (st.countTokens() != 3) {
                                    System.err.println("Skipping ill-formatted line " + line);
                                    continue;
                                }
                                String source = st.nextToken();
                                String dest = st.nextToken();
                                int cost = Integer.parseInt(st.nextToken());
                                g.addEdge(source, dest, cost);

                                if (i == 1) {
                                    startNode = source;
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Skipping ill-formatted line " + line);
                            }
                        }

                        i += 1;

                    }
                    g.dijkstra(startNode);

                    bwr.write(Vertices_g + " " + Edges_g + " " + g.operations_v + " " + g.operations_e + " "
                            + g.operations_pq + " " + Theoretical_Complexity_g + "\n");

                } catch (IOException e) {
                    System.err.println(e);
                }

            }
            bwr.close();

        } catch (IOException e) {
            System.err.println(e);
        } // Catch

    }

    /**
     * Helper function to main() which compares if two files are the same or not
     */
    public static boolean compareFiles(File file1, File file2) throws IOException {
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);

        int b1, b2;
        do {
            b1 = fis1.read();
            b2 = fis2.read();
            if (b1 != b2) {
                return false;
            }
        } while (b1 != -1 && b2 != -1);

        fis1.close();
        fis2.close();

        return true;
    }

}
