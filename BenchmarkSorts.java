/*
 * This class creates Sort objects to run the sorts over data and calculates
 * benchmarks and metrics on those sorts
 * creating BenchmarkSorts object allows to use the runSorts() & 
 * displayReport() methods on array of data 
 */
package insertionsort;

import static insertionsort.InsertionSort.arrays;
import static insertionsort.InsertionSort.arrays2;
import static insertionsort.InsertionSort.setSizeArray;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BenchmarkSorts {
    int[] sizes;
    int[] list;
    int[] list2;
    Sort sort;
    Sort sort2;
    int count;
    long time;
    int[] countArray;
    int[] rCountArray;
    long[] timeArray;
    long[] rTimeArray;
    float[] avgCOp = new float[10];
    float[] rAvgCOp = new float[10];
    long[] avgTime = new long[10];
    long[] rAvgTime = new long[10];
    double[] sDevCount = new double[10];
    double[] rSDevCount = new double[10];
    double[] sDevTime = new double[10];
    double[] rSDevTime = new double[10];
    int[] dataSize = new int[10];
    
    // Constructor for BenchmarkSorts
    BenchmarkSorts(int[] sizes) {
        this.sizes = sizes;
        countArray = new int[sizes.length];
        timeArray = new long[sizes.length];
        rCountArray = new int[sizes.length];
        rTimeArray = new long[sizes.length];
    }
    // Method runs both iterative and recursive sorts from Sort class
    public void runSorts() throws UnsortedException {
        System.out.println("************** Iterative Sort *******************");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("---------------------------------------------");
            list = arrays[i];
            System.out.print("Pre-Sorted Array" + i + ": [");
            for (int j=0; j < arrays[i].length; j++) {
                System.out.print(list[j] + ", "); 
            }
            System.out.println("]");
            sort = new Sort(list);
            sort.iterativeSort(list);
            System.out.print("Sorted Array" + i + "    : [");
            for (int j=0; j < arrays[i].length; j++) {
                System.out.print(list[j] + ", "); 
            }
            // Exception
            for (int j=1; j < list.length; j++) {
                if (list[j] < list[j-1]) {
                    throw new UnsortedException("Array not properly sorted!");
                }
            }
            System.out.println("]");
            count = sort.getCount();
            time = sort.getTime();
            System.out.println("COp Count: " + count);
            System.out.println("Sort Time: " + time);
            countArray[i] = count;
            timeArray[i] = time;

        }  // recursive run
        System.out.println("************** Recursive Sort *******************");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("---------------------------------------------");
            list2 = arrays2[i];
            sort2 = new Sort(list2);

            System.out.print("Pre-Sorted Array" + i + ": [");
            for (int j=0; j < arrays2[i].length; j++) {
                System.out.print(list2[j] + ", "); 
            }       
            System.out.println("]");
            sort2.recursiveSort(list2.length);
            System.out.print("Sorted Array" + i + "    : [");
            for (int j=0; j < arrays2[i].length; j++) {
                System.out.print(list2[j] + ", "); 
            }
            // Exception
            for (int j=1; j < list2.length; j++) {
                if (list2[j] < list2[j-1]) {
                    throw new UnsortedException("Array not properly sorted!");
                }
            }
            System.out.println("]");
            count = sort2.getCount();
            time = sort2.getTime();
            System.out.println("COp Count: " + count);
            System.out.println("Sort Time: " + time);
            rCountArray[i] = count;
            rTimeArray[i] = time;
        }
    }
    // Method calculates benchmarks and displays report in new window
    public void displayReport() {
        // Report math and display
        System.out.println("-------------------------------------------------");
        int totalCount = 0;
        int rTotalCount = 0;
        long totalTime = 0;
        long rTotalTime = 0;
        int j = 0;
        int k = 0;
        double varianceCount = 0;
        double rVarianceCount = 0;
        double varianceTime = 0;
        double rVarianceTime = 0;
        // Calculate benchmarks
        for (int i=0; i < arrays.length; i++) {
            while (j < arrays.length && (setSizeArray[i] == arrays[j].length)) {
                // Calculate Averages of data
                totalCount += countArray[j];
                rTotalCount += rCountArray[j];
                totalTime += timeArray[j];
                rTotalTime += rTimeArray[j];
                dataSize[k] = setSizeArray[i];
                avgCOp[k] = totalCount / 5;
                rAvgCOp[k] = rTotalCount / 5;
                avgTime[k] = totalTime / 5;
                rAvgTime[k] = rTotalTime / 5;
                // Calculate variances in data
                varianceCount += Math.pow(countArray[j], 2);
                rVarianceCount += Math.pow(rCountArray[j], 2);
                varianceCount = varianceCount/5;
                rVarianceCount = rVarianceCount/5;
                varianceTime += Math.pow(timeArray[j], 2);
                rVarianceTime += Math.pow(rTimeArray[j], 2);
                varianceTime = varianceTime/5;
                rVarianceTime = rVarianceTime/5;
                // Calculate Standard Deviations from Variances
                sDevCount[k] = Math.sqrt(varianceCount);
                rSDevCount[k] = Math.sqrt(rVarianceCount);
                sDevTime[k] = Math.sqrt(varianceTime);
                rSDevTime[k] = Math.sqrt(rVarianceTime);
                
                j++;
                if (j % 5 == 0) {
                    k++;
                    // Reset values
                    totalCount = 0;
                    rTotalCount = 0;
                    totalTime = 0;
                    rTotalTime = 0;
                    varianceCount = 0;
                    rVarianceCount = 0;
                    varianceTime = 0;
                    rVarianceTime = 0;
                }
            }
        }
        System.out.println("*********** Iterative Insertion Sort ************");
        for (int i=0; i < avgCOp.length;i++) {
            System.out.println("---------------------------------------------");
            System.out.println(">>> For Data Set Size n = "+ dataSize[i]+" <<<");
            System.out.println("avgCOp Count         : " + avgCOp[i]);
            System.out.println("avgTime              : " + avgTime[i]);
            System.out.println("Standard Dev of Count: " + sDevCount[i]);
            System.out.println("Stand Dev of Time    : " + sDevTime[i]);
        }
        System.out.println("*************************************************");
        System.out.println("*********** Recursive Insertion Sort ************");
        for (int i=0; i < 10; i++) {
            System.out.println("---------------------------------------------");
            System.out.println(">>> For Data Set Size n = "+ dataSize[i]+" <<<");
            System.out.println("r AvgCOp Count        : " + rAvgCOp[i]);
            System.out.println("r AvgTime             : " + rAvgTime[i]);
            System.out.println("Standard Dev of rCount: " + rSDevCount[i]);
            System.out.println("Stand Dev of rTime    : " + rSDevTime[i]);
        }
        // JFrame for Graphical Display of Data
        JFrame jfrm = new JFrame("Benchmark Results for Insertion Sort");
        jfrm.setLayout(new GridLayout(2,0));
        jfrm.setAlwaysOnTop(true);
        jfrm.setSize(1900, 600);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Seperate Panel for top part
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,3));
        JLabel jlabIter = new JLabel("Iterative");
        JLabel jlabRec = new JLabel("Recursive");
        JLabel jlabData = new JLabel("Data Set Size n");
        // Labels for column descriptions
        JLabel jlabBlank = new JLabel(" ");
        JLabel jlabACOp = new JLabel("Avg. Critical Operation Count");
        JLabel jlabSDevCo = new JLabel("Standard Deviation of Count");
        JLabel jlabAXT = new JLabel("Avg. Execution Time");
        JLabel jlabSDevT = new JLabel("Standard Deviation of Time");
        JLabel jlabACOp2 = new JLabel("Avg. Critical Operation Count");
        JLabel jlabSDevCo2 = new JLabel("Standard Deviation of Count");
        JLabel jlabAXT2 = new JLabel("Avg. Execution Time");
        JLabel jlabSDevT2 = new JLabel("Standard Deviation of Time");
        // add labels for panel 1
        panel1.add(jlabData);
        panel1.add(jlabIter);
        panel1.add(jlabRec);
        panel1.setVisible(true);
        jfrm.add(panel1); // add panel 1 to JFrame
        // New panel for data for proper alignment
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(11,9));
        panel2.add(jlabBlank);
        panel2.add(jlabACOp);
        panel2.add(jlabSDevCo);
        panel2.add(jlabAXT);
        panel2.add(jlabSDevT);
        panel2.add(jlabACOp2);
        panel2.add(jlabSDevCo2);
        panel2.add(jlabAXT2);
        panel2.add(jlabSDevT2);
        // Label array for jLabels of data
        JLabel[][] jLabel = new JLabel[10][9];
        // loop thru array to create labels and fill with data
        for (int i = 0; i < jLabel.length; i++) {
            jLabel[i][0] = new JLabel(String.valueOf(dataSize[i]));
            jLabel[i][1] = new JLabel(String.valueOf(avgCOp[i]));
            jLabel[i][2] = new JLabel(String.valueOf(sDevCount[i]));
            jLabel[i][3] = new JLabel(String.valueOf(avgTime[i]));
            jLabel[i][4] = new JLabel(String.valueOf(sDevTime[i]));
            jLabel[i][5] = new JLabel(String.valueOf(rAvgCOp[i]));
            jLabel[i][6] = new JLabel(String.valueOf(rSDevCount[i]));
            jLabel[i][7] = new JLabel(String.valueOf(rAvgTime[i]));
            jLabel[i][8] = new JLabel(String.valueOf(rSDevTime[i]));
            // add labels
            for (int z = 0; z < jLabel[i].length; z++) {
                panel2.add(jLabel[i][z]);
            }
            
        }
        panel2.setVisible(true);
        jfrm.add(panel2);
        jfrm.setVisible(true);
        // This GUI took forever, I hope you enjoyed it Prof. haha
    }
    
}
