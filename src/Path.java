/**
 * Represents an entry in the priority queue for Dijkstra's algorithm.
 */
class Path implements Comparable<Path> {

    /** The destination vertex in the path. */
    public Vertex dest;

    /** The cost of the path to the destination vertex. */
    public double cost;

    /**
     * Constructs a new path with the specified destination vertex and cost.
     * 
     * @param d the destination vertex in the path
     * @param c the cost of the path to the destination vertex
     */
    public Path(Vertex d, double c) {
        dest = d;
        cost = c;
    }

    /**
     * Compares this path with another path based on their costs.
     * 
     * @param rhs the other path to compare with
     * @return -1 if this path has a lower cost, 1 if it has a higher cost, or 0 if
     *         the costs are equal
     */
    public int compareTo(Path rhs) {
        double otherCost = rhs.cost;
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}