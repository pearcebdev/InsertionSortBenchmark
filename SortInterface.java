/*
 * Interface for implementing sort methods.  Any class using this interface 
 * Must override provided skeleton methods
 */
package insertionsort;

/**
 *
 * @author Angmar
 */
public interface SortInterface {
    //
    int recursiveSort(int maxIndex);
    void iterativeSort(int[] list);
    int getCount();
    long getTime();
}
