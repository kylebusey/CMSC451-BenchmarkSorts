/**
 * Written by Kyle Busey June 6th
 *
 * Referenced:
 * https://www.youtube.com/watch?v=g-PGLbMth_g - Info
 * https://www.youtube.com/watch?v=EwjnF7rFLns - Info + implementation
 * https://javarevisited.blogspot.com/2012/04/how-to-measure-elapsed-execution-time.html#:~:text=There%20are%20two%20ways%20to,calls%20or%20events%20in%20Java.
 *
 * Selection sort:
 *
 * Time complexity - O(n^2)
 * Space complexity - O(1)
 */

public class SelectionSort extends AbstractSort {

    private int count = 0;
    private long startTime, elapsedTime;

    private void selectionSort(int[] array) {
        int length = array.length;

        for(int i = 0; i < length - 1; i++) {
            int minIndex = i;

            for(int j = minIndex + 1; j < length; j++) {
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                    incrementCount(); //critical operation (set index of smaller number)
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
            incrementCount(); //critical operation (swap)
        }

    }

    @Override
    public int[] sort(int[] origArray) {
        selectionSort(origArray);

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
