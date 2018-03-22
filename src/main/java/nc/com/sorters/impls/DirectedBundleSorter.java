package nc.com.sorters.impls;

import nc.com.sorters.Sorter;

/**
 * A {@code DirectedBundleSorter} is an sub type of Sorter
 * abstract class for realization bundle sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 * @see BundleSorter
 */
public class DirectedBundleSorter extends BundleSorter{
    /**
     * The public constructor calls super constructor {@link BundleSorter}
     *
     * @param array the array of integers.
     */
    public DirectedBundleSorter(int[] array) {
        super(array);
    }

    /**
     * This method calls direct bundle sort method.
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
     * This method sorts array by using direct bundle sort algorithm.
     */
    private void bundleSort() {
        for (int i = 0; i < this.array.length - 1; i += 1)
            for (int j = i + 1; j < this.array.length; j += 1)
                if (array[i] > array[j])
                    this.swap(i, j);
    }
}
