package nc.com.sorters;

/**
 * A {@code ArrayType} provides types of sorts.
 *
 * @author Oleksandr Smirnov
 * @see Sorter
 * @see nc.com.sorters.impls.ArraysSorter
 * @see nc.com.sorters.impls.BundleSorter
 * @see nc.com.sorters.impls.MergeSorter
 * @see nc.com.sorters.impls.QuickSorter
 */
public enum SortType {
    ArraysSort ("ArraysSorter"),
    ReversedBundleSort ("ReversedBundleSorter"),
    DirectedBundleSort ("DirectedBundleSorter"),
    MergeSort ("MergeSorter"),
    QuickSort ("QuickSorter");

    private final String name;

    private SortType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
