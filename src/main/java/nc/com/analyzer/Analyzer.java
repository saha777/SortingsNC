package nc.com.analyzer;

import nc.com.analyzer.annotations.Filler;
import nc.com.fillers.ArrayType;
import nc.com.fillers.ArraysFiller;
import nc.com.sorters.SortType;
import nc.com.sorters.Sorter;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
/**
 * A {@code Analyzer} provides information about sorts
 * by using Reflections. This class contains a {@link Analyzer#analyze()}
 * that collects data in a list of statistics using reflections.
 *
 * @author Oleksandr Smirnov
 * @see Filler
 * @see ArrayType
 * @see SortType
 * @see Sorter
 * @see nc.com.sorters.impls.ArraysSorter
 * @see nc.com.sorters.impls.BundleSorter
 * @see nc.com.sorters.impls.MergeSorter
 * @see nc.com.sorters.impls.QuickSorter
 *
 * @see List
 * @see LinkedList
 * @see Set
 *
 * @see Reflections
 * @see Method
 * @see Class
 * @see ClasspathHelper
 * @see SubTypesScanner
 * @see ConfigurationBuilder
 * @see ResourcesScanner
 * @see MethodAnnotationsScanner
 * @see FilterBuilder
 * @see System
 */
public class Analyzer {

    /**
     * Public constructor without params.
     */
    public Analyzer() {}



    /**
     * This method returns the array,
     * which is a part from the array passed to it.
     *
     * @param      size        the size of array that will be returned.
     * @param      array       the full array
     */
    private int[] getArrayPart(int[] array, int size) {
        int[] newArray = new int[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    /**
     * This method returns statistics about each of sorts.
     * @return the list of statistics.
     * @exception NoSuchMethodException if a method with annotation {@link Filler} cannot be found.
     * @exception InvocationTargetException checked exception that wraps
     * an exception thrown by an invoked method or constructor.
     * @exception IllegalAccessException if an application tries
     * to reflectively create an instance of {@link Sorter}, but the currently
     * executing method does not have access to the definition of
     * the specified class.
     * @exception InstantiationException if an application tries to create an instance of a class
     * using the {@code newInstance} method in class
     * {@code Class}, but the specified class object cannot be
     * instantiated.
     */
    public List<Statistic> analyze(Reflection reflection)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException{

        if(reflection == null)
            return null;

        List<Statistic> statistics = new ArrayList<>();

        for (ArrayType arrayType : ArrayType.values()) {
            int[] array = reflection.getArray(arrayType);

            for (SortType sortType : SortType.values()) {

                Class<? extends Sorter> sorter = reflection.getSorter(sortType);

                List<Long> times = new ArrayList<>();

                for (int size = 10; size <= ArraysFiller.minArraySize; size *= 10) {
                    times.add(reflection.sort(sorter, getArrayPart(array, size)));
                }

                statistics.add(new Statistic(sortType, arrayType, times));
            }
        }

        return statistics;
    }
}
