/* Brandon Pearce
 * UMUC CMSC 451
 * Insertion Sort Algorithms and Benchmarks
 *
 * Main method for Insertion Sort package
 * builds array of randomly generated numbers
 * Creates BenchmarkSorts object to run both iterative and recursive sorts
 * Over array, and displays the results from the benchmarks in a graphics window
 * Throws UnsortedException if Arrays not properly sorted
 */
package insertionsort;

public class InsertionSort {
    
    static int[][] arrays;
    static int[][] arrays2;
    static int[] setSizeArray;
    static long[] timeArray;
    static int[] countArray;
    
    public static void main(String[] args) throws UnsortedException {
        buildArrays();
        setSizeArray = new int[arrays.length];
        for (int i = 0; i < setSizeArray.length; i++) {
            setSizeArray[i] = arrays[i].length;
        }
        timeArray = new long[arrays.length];
        
        // Modular functionality
        BenchmarkSorts benchmarkSorts;
        benchmarkSorts = new BenchmarkSorts(setSizeArray);
        benchmarkSorts.runSorts();
        benchmarkSorts.displayReport();
           
    }
    
    public static void buildArrays() {
        // Builds array of 50 arrays with starting elements and step size
        // for each increase of element size in arrays
        int numArrays = 50;
        int elements = 50; // starting element size
        int stepSize = 50; // element increase at each step
        int counter = 1; // counter for controlling increments
        arrays = new int[numArrays][elements];
        arrays2 = new int[numArrays][elements];
        System.out.println("************ Building RNG Arrays ****************");
        System.out.println("*************************************************");
        for (int i=0; i < arrays.length; i++) {
            arrays[i] = new int[elements];
            arrays2[i] = new int[elements];
            int arraySize = arrays[i].length;
            System.out.print("Array" + i + " of size(" + arraySize + ") : [");
            
            for (int j=0; j < elements; j++) {
                int randomInt = (int) (Math.random() * 1000);
                arrays[i][j] = randomInt;
                arrays2[i][j] = randomInt;
                System.out.print(arrays[i][j] + ", ");
                
            }
            System.out.print("]");
            System.out.println();
            // modulo operator for increasing the elements @stepSize
            // for every 5 elements
            if (counter % 5 == 0)
                elements += stepSize;
            counter++;
        }
        System.out.println("_______________________________________________\n");
    }
    
}
