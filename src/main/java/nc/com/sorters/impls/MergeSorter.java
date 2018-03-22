package nc.com.sorters.impls;

import nc.com.sorters.Sorter;
/**
 * A {@code MergeSorter} is an sub type of Sorter
 * class for realization merge sorter.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 */
public class MergeSorter extends Sorter {

    /**
     * The public constructor calls super constructor
     *
     * @param array the array of integers.
     */
    public MergeSorter(int[] array) {
        super(array);
    }

    /**
     * This method calls merge sort method.
     *
     * @return the sorted array.
     */
    @Override
    public int[] sort() {
        if (this.array == null)
            throw new NullPointerException();

        this.recMergeSort(new int[this.array.length], 0, this.array.length - 1);

        return this.array;
    }

    /**
     * This method sorts array by using recursive mergesort algorithm.
     * @param workSpace the part that must be sorted
     * @param lowerBound the first element of array part that must be sorted
     * @param upperBound the last element of array part that must be sorted
     */
    private void recMergeSort(int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) return;

        int middle = (lowerBound + upperBound) / 2;
        recMergeSort(workSpace, lowerBound, middle);
        recMergeSort(workSpace, middle + 1, upperBound);
        merge(workSpace, lowerBound, middle + 1, upperBound);
    }

    /**
     * This method merge array and workSpace.
     * @param workSpace the part that must be sorted
     * @param lowIndex the first element of array part that must be sorted
     * @param upperBound the mid element of array part that must be sorted
     * @param highIndex the last element of array part that must be sorted
     */
    private void merge(int[] workSpace, int lowIndex,
                       int highIndex, int upperBound) {
        int j = 0;
        int lowerBound = lowIndex;
        int middle = highIndex - 1;
        int n = upperBound - lowerBound + 1;

        while (lowIndex <= middle && highIndex <= upperBound)
            if (this.array[lowIndex] < this.array[highIndex])
                workSpace[j++] = this.array[lowIndex++];
            else
                workSpace[j++] = this.array[highIndex++];

        while (lowIndex <= middle)
            workSpace[j++] = this.array[lowIndex++];

        while (highIndex <= upperBound)
            workSpace[j++] = this.array[highIndex++];

        for (j = 0; j < n; j++)
            this.array[lowerBound + j] = workSpace[j];
    }
}
