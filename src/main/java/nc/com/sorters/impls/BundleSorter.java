package nc.com.sorters.impls;

import nc.com.sorters.Sorter;
/**
 * A {@code BundleSorter} is an sub type of Sorter
 * abstract class for realization bundle sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 * @see DirectedBundleSorter
 * @see ReversedBundleSorter
 */
public abstract class BundleSorter extends Sorter {

    /**
     * The public constructor calls super constructor {@link Sorter}
     *
     * @param array the array of integers.
     */
    public BundleSorter(int[] array) {
        super(array);
    }
}
