import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

/**
 * GFGRandomGraph is a class for generating a random graph and writing it to a
 * file for testing purposes.
 * The graph is represented as an adjacency list and can have a random number of
 * vertices and edges with a limit
 * set for the maximum number of vertices and edge cost.
 * 
 * It creates the a connected graph
 * in the experiment to ensure no sparse graphs are in the experiment
 */

public class GFGRandomGraph {

    /**
     * The number of vertices in the graph.
     */
    public int vertices;

    /**
     * The number of edges in the graph.
     */
    public int edges;

    /**
     * Set a maximum limit to the vertices.
     */
    final int MAX_LIMIT = 350;

    /**
     * Set limit for edge cost.
     */
    final static int MAX_COST = 50;

    /**
     * A random number generator.
     */
    Random random = new Random();

    /**
     * An adjacency list to represent the graph.
     */
    public List<List<Integer>> adjacencyList;

    /**
     * Computes the maximum number of possible edges for a given number of vertices.
     * 
     * @param numOfVertices The number of vertices in the graph.
     * @return The maximum number of possible edges.
     */
    int computeMaxEdges(int numOfVertices) {
        return numOfVertices * ((numOfVertices - 1) / 2);
    }

    /**
     * Constructor for the GFGRandomGraph class.
     * Generates a connected random graph and creates an adjacency list
     * representation for the
     * graph.
     */
    public GFGRandomGraph() {
        this.vertices = Math.max(2, random.nextInt(MAX_LIMIT));
        this.edges = Math.max(vertices - 1, random.nextInt(Math.max(2, computeMaxEdges(vertices) + 1)));

        // Creating an adjacency list representation for the random graph
        adjacencyList = new ArrayList<>(vertices);

        for (int a = 0; a < vertices; a++) {
            adjacencyList.add(new ArrayList<>());
        }

        // generate all possible edges
        List<int[]> edgesList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                edgesList.add(new int[] { i, j });
            }
        }

        Collections.shuffle(edgesList);

        // Tracking number of edges added to graph. Along with the degrees of vertices
        int edge_count = 0;
        int[] degree = new int[vertices];

        // Tracking vertices added to graph
        Set<Integer> visited = new HashSet<>();
        int u = 0;
        int b = 0;
        // add edges to the graph
        for (int[] edge : edgesList) {
            u = edge[0];
            b = edge[1];

            if ((!visited.contains(u)) || (!visited.contains(b))) {

                addEdge(u, b);
                degree[u] += 1;
                degree[b] += 1;

                if (!visited.contains(u)) {
                    visited.add(u);
                }

                if (!visited.contains(b)) {
                    visited.add(b);
                }

                edge_count += 1;
                if (edge_count == edges) {
                    break;
                }
            }

            else if ((degree[u] <= vertices - 1) && (degree[b] <= vertices - 1) && visited.size() >= vertices) {

                if ((!adjacencyList.get(u).contains(b)) || (!adjacencyList.get(b).contains(u))) {
                    addEdge(u, b);
                    degree[u] += 1;
                    degree[b] += 1;
                    edge_count += 1;
                    if (edge_count == edges) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method to add an edge between two vertices in the adjacency list.
     * 
     * @param v The first vertex.
     * @param w The second vertex.
     */
    void addEdge(int v, int w) {
        adjacencyList.get(v).add(w);
    }

    /**
     * Main randomly generates a graph and then stores the maximal fully connected
     * subgraph of that graph in a text file called dataset.Vertices.Edges
     * "Vertices" and "Edges" being the actual numbers of vertices and edges that
     * subgraph graph has.
     *
     * The first line of the text file has the number of Vertices, Edges and
     * Theoretical time complexity of Dijkstra's algorithm of the subgraph
     */
    public void main() {

        GFGRandomGraph randomGraph = new GFGRandomGraph();
        int count = 1;

        String filename = "dataset." + randomGraph.vertices + "." + randomGraph.edges;
        File directory = new File("data"); // Directory to put the files in

        while (new File(directory, filename).exists()) {
            count++;
            filename = "dataset." + randomGraph.vertices + "." + randomGraph.edges + "(" + count + ")";
        }

        File file = new File(directory, filename);

        try {

            FileWriter fw = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(randomGraph.vertices + " " + randomGraph.edges + " "
                    + ((int) (randomGraph.edges * Math.log(vertices))) + "\n");

            // Print the graph
            for (int i = 0; i < randomGraph.adjacencyList.size(); i++) {

                List<Integer> list = randomGraph.adjacencyList.get(i);

                if (!list.isEmpty()) {
                    int size = list.size();
                    for (int j = 0; j < size; j++) {

                        bw.write("Node" + i + " Node" + list.get(j) + " " + (randomGraph.random.nextInt(MAX_COST) + 1)
                                + "\n");
                    }
                }

            }

            bw.close();

            // Graph written to file.

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }
}