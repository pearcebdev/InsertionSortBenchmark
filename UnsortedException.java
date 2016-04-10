/*
 * This class extends Exception.  Used to throw a new exception with custom
 * Error message
 */
package insertionsort;

public class UnsortedException extends Exception {

    /**
     * Creates a new instance of <code>UnsortedException</code> without detail
     * message.
     */
    public UnsortedException() {
    }

    /**
     * Constructs an instance of <code>UnsortedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnsortedException(String msg) {
        super(msg);
    }
}
