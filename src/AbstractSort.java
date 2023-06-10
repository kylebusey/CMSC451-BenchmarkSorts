public abstract class AbstractSort {
    public abstract int[] sort(int[] origArray);
    protected abstract void startSort();
    protected abstract void endSort();
    protected abstract void incrementCount();
    public abstract int getCount();
    public abstract long getTime();


}
