package nc.com.sorters.impls;

import nc.com.sorters.Sorter;

import java.util.Arrays;
/**
 * A {@code ArraysSorter} is an sub type of Sorter
 * class for realization merge sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 */
public class ArraysSorter extends Sorter {

    /**
     * The public constructor calls super constructor
     *
     * @param array the array of integers.
     */
    public ArraysSorter(int[] array) {
        super(array);
    }

    /**
     * This method calls Arrays.sort method.
     *
     * @return the sorted array.
     */
    @Override
    public int[] sort() {
        if (this.array == null)
            throw new NullPointerException();

        Arrays.sort(this.array);

        return this.array;
    }
}
