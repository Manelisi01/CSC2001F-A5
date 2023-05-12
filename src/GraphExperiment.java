
/**
 * GraphExperiment Class runs a main method which generates and stores new 50
 * non-sparse graphs in text files in the "data" folder automatically
 * It also then stores automatically the number of Vertices and Edges of each
 * graph. Also number of operations on Vertices, number of operations of Edges
 * and The number of operations in the priority queue of Dijkstra's algorithm
 * and Theoretical time complexity of Dijkstra's algorithm of each graph in a
 * text file called "Data for complexity Comparison" in the same "data" folder.
 *
 */

public class GraphExperiment {

    /**
     * Main Method
     */
    public static void main(String[] args) {

        GFGRandomGraph generateGraph = new GFGRandomGraph();
        Graph dijskatraAlgo = new Graph();

        for (int b = 0; b < 50; b++) {
            generateGraph.main();
        }

        dijskatraAlgo.main();
    }

}
