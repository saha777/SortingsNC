package nc.com.sorters;
/**
 * A {@code Sorter} is an abstract class for sorters.
 *
 * @author Oleksandr Smirnov
 * @see nc.com.sorters.impls.ArraysSorter
 * @see nc.com.sorters.impls.BundleSorter
 * @see nc.com.sorters.impls.DirectedBundleSorter
 * @see nc.com.sorters.impls.ReversedBundleSorter
 * @see nc.com.sorters.impls.MergeSorter
 * @see nc.com.sorters.impls.QuickSorter
 * @see SortType
 */
public abstract class Sorter {

    protected int[] array;

    /**
     * The protected constructor that set the array
     * field by using param array {@code int[]}.
     *
     * @param array the array of integers.
     */
    protected Sorter(int[] array){
        this.array = array;
    }

    /**
     * Getter for array field.
     *
     * @return array.
     */
    public int[] getArray() {
        return array;
    }

    /**
    * Setter for array field.
    *
    * @param array the list of times.
    */
    public void setArray(int[] array) {
        this.array = array;
    }

    /**
     * The method for swap array elements.
     *
     * @param i the index of first element in array.
     * @param j the index of second element in array.
    */
    protected void swap(int i, int j){
        int var = array[i];
        array[i] = array[j];
        array[j] = var;
    }

    /**
     * The abstract method for sorting array elements.
     * @return sorted array.
     */
    public abstract int[] sort();
}
