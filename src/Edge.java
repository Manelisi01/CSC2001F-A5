// Represents an edge in the graph.
/**
 * Represents an edge in a graph.
 * 
 * @param <G> the type of the second vertex in the edge
 */
class Edge<G> {

    /** Second vertex in Edge */
    public G dest;

    /** Edge cost */
    public double cost; // Edge cost

    /**
     * Constructs a new edge with the specified destination and cost.
     * 
     * @param d the destination vertex of the edge
     * @param c the cost of the edge
     */
    public Edge(G d, double c) {
        dest = d;
        cost = c;
    }
}
