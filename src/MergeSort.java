/**
 * Written by Kyle Busey June 6th
 *
 *
 * Referenced:
 * https://www.youtube.com/watch?v=4VqmGXwpLqc - Merge sort info
 * https://www.youtube.com/watch?v=3j0SWDX4AtU - Info + implementation
 *
 * Merge sort:
 * Time complexity - O(nlogn)
 * Space complexity - O(n)
 */


public class MergeSort extends AbstractSort {

    private int count = 0;
    private long startTime, elapsedTime;

    public void mergeSort(int[] array) {
        int length = array.length;

        if(length <= 1) return;
        incrementCount(); //critical operation

        int middle = length/2;

        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0;
        int j = 0;

        for(; i < length; i++) {
            if(i < middle) {
                incrementCount(); //critical operation
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
                incrementCount(); //critical operation
            }
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    private void merge(int[] leftArray, int[] rightArray, int[] originalArray) {
        int leftSize = originalArray.length / 2;
        int rightSize = originalArray.length - leftSize;

        int leftElements = 0, rightElements = 0, i = 0;

        while(leftElements < leftSize && rightElements < rightSize) {
            if(leftArray[leftElements] < rightArray[rightElements]) {
                originalArray[i] = leftArray[leftElements];
                i++;
                leftElements++;
                incrementCount(); //critical operation
            } else {
                originalArray[i] = rightArray[rightElements];
                i++;
                rightElements++;
                incrementCount(); //critical operation
            }
        }

        while(leftElements < leftSize) {
            originalArray[i] = leftArray[leftElements];
            i++;
            leftElements++;
            incrementCount(); //6
        }
        while(rightElements < rightSize) {
            originalArray[i] = rightArray[rightElements];
            i++;
            rightElements++;
            incrementCount(); //7
        }
    }

    @Override
    public int[] sort(int[] origArray) {
        mergeSort(origArray);
        return origArray;
    }

    @Override
    protected void startSort() {
        startTime = System.nanoTime();
    }

    @Override
    protected void endSort() {
        elapsedTime = System.nanoTime() - startTime;
    }

    @Override
    protected void incrementCount() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return elapsedTime;
    }
}
