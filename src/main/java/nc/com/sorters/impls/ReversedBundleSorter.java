package nc.com.sorters.impls;

import nc.com.sorters.Sorter;

/**
 * A {@code ReversedBundleSorter} is an sub type of Sorter
 * abstract class for realization bundle sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 * @see BundleSorter
 */
public class ReversedBundleSorter extends BundleSorter {
    /**
     * The public constructor calls super constructor {@link BundleSorter}
     *
     * @param array the array of integers.
     */
    public ReversedBundleSorter(int[] array) {
        super(array);
    }

    /**
     * This method calls reverse bundle sort method.
     *
     * @return the sorted array.
     */
    @Override
    public int[] sort() {
        if (this.array == null)
            throw new NullPointerException();

        this.bundleSort();

        return this.array;
    }

    /**
     * This method sorts array by using reverse bundle sort algorithm.
     */
    private void bundleSort() {
        for (int i = 0; i < this.array.length - 1; i += 1)
            for (int j = this.array.length - 1; j > i; j -= 1)
                if (array[i] > array[j])
                    this.swap(i, j);
    }
}
