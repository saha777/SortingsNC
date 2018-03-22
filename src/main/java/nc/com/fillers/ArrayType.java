package nc.com.fillers;

/**
 * A {@code ArrayType} provides types of arrays.
 *
 * @author Oleksandr Smirnov
 */
public enum ArrayType {
    SortedArray("SortedArray"),
    SortedArrayWithRandomElement("SortedArrayWithRandomElement"),
    ReversedSortedArray("ReversedSortedArray"),
    RandomArray("RandomArray");

    private final String name;

    private ArrayType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
