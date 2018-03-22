package nc.com.analyzer;

import nc.com.fillers.ArrayType;
import nc.com.sorters.SortType;

import java.util.Arrays;
import java.util.List;
/**
 * A {@code Statistic} contains information about type of sort,
 * type of array and list of each of times that means sorting
 * array of 10, 100, 1000 or 10000 elements.
 *
 * @author Oleksandr Smirnov
 * @see SortType
 * @see ArrayType
 * @see List
 */
public class Statistic {
    private SortType sortType;
    private ArrayType arrayType;
    private List<Long> times;

    /**
     * Public constructor with type of sort, type of array and times params.
     * Using for creating the instance of this class.
     *
     * @param sortType the type of sort from enum nc.com.sorters.SortType.
     * @param arrayType the type of array from enum nc.com.fillers.ArrayType.
     * @param times the list of times of sorting for 10, 100, 1000 and 10000 elements.
     */
    public Statistic(SortType sortType, ArrayType arrayType, List<Long> times) {
        this.sortType = sortType;
        this.arrayType = arrayType;
        this.times = times;
    }

    /**
     * Getter for sortType field.
     *
     * @return the type of sort.
     */
    public SortType getSortType() {
        return sortType;
    }

    /**
     * Setter for sortType field.
     *
     * @param sortType the type of sort.
     */
    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Getter for arrayType field.
     *
     * @return the type of array.
     */
    public ArrayType getArrayType() {
        return arrayType;
    }

    /**
     * Setter for arrayType field.
     *
     * @param arrayType the type of array.
     */
    public void setArrayType(ArrayType arrayType) {
        this.arrayType = arrayType;
    }


    /**
     * Getter for times field.
     *
     * @return the list of times that arrays was sorted.
     */
    public List<Long> getTimes() {
        return times;
    }

    /**
     * Setter for times field.
     *
     * @param times the list of times.
     */
    public void setTimes(List<Long> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "sortType=" + sortType +
                ", arrayType=" + arrayType +
                ", times=" + Arrays.toString(times.toArray()) +
                '}';
    }
}
