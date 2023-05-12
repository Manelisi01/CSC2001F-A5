/**
 * Signals violations of preconditions for various shortest path algorithms.
 * This is a runtime exception and extends the RuntimeException class.
 */
class GraphException extends RuntimeException {

    /**
     * Serial version UID for serialization interoperability.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new GraphException with the specified error message.
     * 
     * @param name the error message describing the violation of precondition
     */
    public GraphException(String name) {
        super(name);
    }
}