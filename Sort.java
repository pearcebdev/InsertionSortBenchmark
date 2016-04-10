/*
 * Iterative Insertion Sort
 * 
 * Recursive Insertion Sort
 * 
 * This class implements the SortInterface interface
 * Includes methods for Iterative insertion sort and recursive insertion sort &
 * getter methods for retrieving operation count and time required for sorting
 * These methods are available after constructing a Sort object
 */
package insertionsort;

public class Sort implements SortInterface {
    int[] list;
    long[] timeList;
    int count;
    long time;
    
    Sort(int[] list) {
        this.list = list;
        count = 0;
        time = 0;
    }
    @Override
    public void iterativeSort(int[] list) {
        // Iterative Insertion Sort algorithm using 
        // shifting to find correct position of the element to sort
        long startTime = java.lang.System.nanoTime(); // start nano timer
        count = 0;
        for (int i = 1; i < list.length; i++) {
            int valueToSort = list[i];
            int j = i;
            while (j > 0 && list[j - 1] > valueToSort) {
                list[j] = list[j-1]; // element shifted left
                j--;
                count++; // Counting number of critical operations in sort
            }
            list[j] = valueToSort;
        }
        long endTime = java.lang.System.nanoTime(); // end nano timer
        time = endTime - startTime; // time taken to complete sorting
    }
    @Override
    public int recursiveSort(int maxIndex) {
        long startTime = java.lang.System.nanoTime();
        if (maxIndex <= 1) {
            return maxIndex;
        }
        maxIndex = recursiveSort(maxIndex -1);
        int key = list[maxIndex];
        int i = maxIndex - 1;
        while ( (i >= 0) && (list[i] > key)) {
            list[i+1] = list[i];
            i--;
            count ++;
        }
        list[i+1] = key;
        long endTime = java.lang.System.nanoTime();
        time = endTime - startTime;
        return maxIndex + 1;

    }
    @Override
    public int getCount() {
        return count;
    }
    @Override
    public long getTime() {
        return time;
    }
    
}
