import java.util.List;
import java.util.LinkedList;

// Represents a vertex in the graph.
/**
 * Represents a vertex in a graph.
 */
class Vertex {
  /** The maximum possible value for the distance of a vertex. */
  public static final double INFINITY = Double.MAX_VALUE;

  /** The name of the vertex. */
  public String name; // Vertex name

  /** A list of edges that are adjacent to the vertex. */
  public List<Edge<Vertex>> adj; // Adjacent vertices

  /** The cost of reaching the vertex from a given source vertex. */
  public double dist; // Cost

  /**
   * The previous vertex on the shortest path from the source vertex to this
   * vertex.
   */
  public Vertex prev; // Previous vertex on shortest path

  /** An extra variable used in the algorithm. */
  public int scratch;// Extra variable used in algorithm

  /**
   * Constructs a new vertex with the specified name, initializes the adjacency
   * list and resets the other fields.
   * 
   * @param nm the name of the vertex
   * 
   */
  public Vertex(String nm) {
    name = nm;
    adj = new LinkedList<Edge<Vertex>>();
    reset();
  }

  /**
   * Resets the fields of the vertex to their default values.
   */
  public void reset()
  // { dist = Vertex.INFINITY; prev = null; pos = null; scratch = 0; }
  {
    dist = Vertex.INFINITY;
    prev = null;
    scratch = 0;
  }

  // public PairingHeap.Position<Path> pos; // Used for dijkstra2 (Chapter 23)
}
