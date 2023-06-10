/**
 * Written by Kyle Busey June 6th
 *
 *
 * Selection sort:
 *
 * Time complexity - O(n^2)
 * Space complexity - O(1)
 */

public class SelectionSort extends AbstractSort {

    public static void selectionSort(int[] array) {

        int length = array.length;
        int sortedIndex = 0;

        for(int i = sortedIndex; i < length - 1; i++) {
            int minIndex = 0;
            int currentMinimum = array[sortedIndex+1];

            for(int j = sortedIndex + 1; j < length; j++) {
                if(array[j] < currentMinimum) {
                    currentMinimum = array[j];
                    minIndex = j;
                }
            }

            array[sortedIndex] = currentMinimum;
            array[minIndex] = array[sortedIndex];
        }
    }

    @Override
    public int[] sort(int[] origArray) {
        selectionSort(origArray);
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
