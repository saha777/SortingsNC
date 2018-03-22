package nc.com.sorters.impls;

import nc.com.sorters.Sorter;
/**
 * A {@code QuickSorter} is an sub type of Sorter
 * class for realization quick sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 */
public class QuickSorter extends Sorter{

    /**
     * The public constructor calls super constructor
     *
     * @param array the array of integers.
     */
    public QuickSorter(int[] array) {
        super(array);
    }

    /**
     * This method calls quick sort method.
     *
     * @return the sorted array.
     */
    @Override
    public int[] sort() {
        if (this.array == null)
            throw new NullPointerException();

        quicksort(0, this.array.length - 1);

        return this.array;
    }

    /**
     * This method sorts array by using recursive quicksort algorithm.
     *
     * @param low the first element of array part that must be sorted
     * @param high the last element of array part that must be sorted
     */
    private void quicksort(int low, int high) {
        int i = low, j = high;
        int pivot = array[low + (high - low) / 2];

        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (low < j) quicksort(low, j);
        if (i < high) quicksort(i, high);
    }
}
