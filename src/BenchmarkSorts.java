import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Written by Kyle Busey on June 8th
 *
 * Project 1 CMSC 451 - Using Selection Sort and Merge Sort
 *
 * Referenced:
 * https://stackoverflow.com/questions/36198278/why-does-the-jvm-require-warmup
 * https://stackoverflow.com/questions/40058707/understanding-critical-operations-counting-for-merge-sort-algorithm-in-java
 * https://www.baeldung.com/java-new-custom-exception
 *
 *
 */
public class BenchmarkSorts {

    public static void main(String[] args) throws UnsortedException, IOException {

        //12 data sets evenly spread out for purpose of graph
        int[] dataSetSizes = {100, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500};
        Random randomNum = new Random();


        File selectionFile = new File("selectiondata.txt");
        FileWriter selectionWriter = new FileWriter("selectiondata.txt");

        File mergeFile = new File("mergedata.txt");
        FileWriter mergeWriter = new FileWriter("mergedata.txt");

        warmupJVM(); //Start JVM warmup before starting main benchmarks

        for (int dataSetSize : dataSetSizes) {

            SelectionSort selectionSort = new SelectionSort();
            MergeSort mergeSort = new MergeSort();

            int[] selectionData = new int[dataSetSize];
            int[] mergeSortData = new int[dataSetSize];

            double[] selectionRunTime = new double[dataSetSize];
            double[] mergeRunTime = new double[dataSetSize];

            int[] selectionCount = new int[dataSetSize];
            int[] mergeSortCount = new int[dataSetSize];

            //40 data sets for each data set size
            for (int runNum = 0; runNum < 40; runNum++) {
                for (int j = 0; j < dataSetSize; j++) {
                    int data = randomNum.nextInt(0, 50000);
                    selectionData[j] = data;
                    mergeSortData[j] = data;
                }

                mergeSort.startSort();
                mergeSort.sort(mergeSortData);
                mergeSort.endSort();

                try {
                    isArraySorted(mergeSortData);
                } catch (UnsortedException e) {
                    e.printStackTrace();
                }

                mergeSortCount[runNum] = mergeSort.getCount();
                mergeRunTime[runNum] = mergeSort.getTime();


                selectionSort.startSort();
                selectionSort.sort(selectionData);
                selectionSort.endSort();

                try {
                    isArraySorted(selectionData);
                } catch (UnsortedException e) {
                    e.printStackTrace();
                }

                selectionCount[runNum] = selectionSort.getCount();
                selectionRunTime[runNum] = selectionSort.getTime();
            }
            //avg of 40 runs for run time and critical operation count
            double selectionTimeAvg = calculateTimeAverage(selectionRunTime);
            double mergeTimeAvg = calculateTimeAverage(mergeRunTime);

            double selectionCountAvg = calculateCountAverage(selectionCount);
            double mergeCountAvg = calculateCountAverage(mergeSortCount);


            //write averages to two respective files
            try {
                selectionWriter.write(dataSetSize + " "
                        + selectionCountAvg + " "
                        + selectionTimeAvg + "\n");
            } catch (IOException e) {
                System.out.println("An error has occurred.");
            }

            try {
                mergeWriter.write(dataSetSize + " "
                        + mergeCountAvg + " "
                        + mergeTimeAvg + "\n");
            } catch (IOException e) {
                System.out.println("An error has occurred.");
            }
        }
        selectionWriter.close();
        mergeWriter.close();
    }


    /**
     * Creates algorithm simulation in order to warm up JVM.
     */
    private static void warmupJVM() {
        System.out.println("Starting JVM Warmup");

        SelectionSort selectionSort = new SelectionSort();
        MergeSort mergeSort = new MergeSort();
        Random randomNum = new Random();

        int[] selectionData = new int[500];
        int[] mergeSortData = new int[500];

        for (int runNum = 0; runNum < 500; runNum++) {
            for (int j = 0; j < 500; j++) {
                int data = randomNum.nextInt(0, 50000);
                selectionData[j] = data;
                mergeSortData[j] = data;
            }

            mergeSort.startSort();
            mergeSort.sort(mergeSortData);
            mergeSort.endSort();

            selectionSort.startSort();
            selectionSort.sort(selectionData);
            selectionSort.endSort();

        }

        System.out.println("JVM Warmup Completed");
    }

    /**
     * Iterates through an array and ensures it is sorted.
     * @param array the array being checked for sorting validation
     * @throws UnsortedException
     */
    private static void isArraySorted(int[] array) throws UnsortedException {

        for(int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]) {
                throw new UnsortedException("Array is not sorted.");
            }
        }
    }

    /**
     * Calculates average of data set passed in. Used to average run time arrays.
     * @param dataSet the array passed in, either selection run time or merge run time.
     * @return the average run time for the given array.
     */
    private static double calculateTimeAverage(double[] dataSet) {
        double total = 0;

        for (double v : dataSet) {
            total += v;
        }

        return (total/dataSet.length);
    }

    /**
     * Calculates average of data set passed in. Used to average critical operation count arrays.
     * @param dataSet the array passed in, either the selection sort critical operation array
     *                or merge sort critical operation array.
     * @return the average critical operation count for 40 runs.
     */
    private static double calculateCountAverage(int[] dataSet) {
        int total = 0;

        for (int j : dataSet) {
            total += j;
        }

        return ((double) total /dataSet.length);
    }

}
