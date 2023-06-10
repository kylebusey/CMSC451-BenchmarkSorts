/**
 * Written by Kyle Busey June 6th
 *
 *
 * Merge sort:
 *
 * Time complexity - O(nlogn)
 * Space complexity - O(n)
 */


public class MergeSort extends AbstractSort {

    public static void mergeSort(int[] array) {
        int length = array.length;

        if(length <= 1) return;

        int middle = length/2;

        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0;
        int j = 0;

        for(; i < length; i++) {
            if(i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] leftArray, int[] rightArray, int[] originalArray) {
        int leftSize = originalArray.length / 2;
        int rightSize = originalArray.length - leftSize;

        int leftElements = 0, rightElements = 0, i = 0;

        while(leftElements < leftSize && rightElements < rightSize) {
            if(leftArray[leftElements] < rightArray[rightElements]) {
                originalArray[i] = leftArray[leftElements];
                i++;
                leftElements++;
            } else {
                originalArray[i] = rightArray[rightElements];
                i++;
                rightElements++;
            }
        }

        while(leftElements < leftSize) {
            originalArray[i] = leftArray[leftElements];
            i++;
            leftElements++;
        }
        while(rightElements < rightSize) {
            originalArray[i] = rightArray[rightElements];
            i++;
            rightElements++;
        }
    }

    @Override
    public int[] sort(int[] origArray) {
        mergeSort(origArray);
        return origArray;
    }

    @Override
    protected void startSort() {

    }

    @Override
    protected void endSort() {

    }

    @Override
    protected void incrementCount() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }
}
