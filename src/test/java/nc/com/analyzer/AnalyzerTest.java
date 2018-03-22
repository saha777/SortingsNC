package nc.com.analyzer;


import nc.com.fillers.ArrayType;
import nc.com.sorters.SortType;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AnalyzerTest {

    @Test
    public void analyzeTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Analyzer analyzer = new Analyzer();
        List<Statistic> statistics = analyzer.analyze(new Reflection());

        assertFalse(statistics == null);
        assertFalse(statistics.isEmpty());
        assertEquals(SortType.values().length * ArrayType.values().length, statistics.size());

        Iterator<Statistic> statisticIterator = statistics.iterator();

        int[] arrayTypesCount = new int[ArrayType.values().length];
        int[] sortTypesCount = new int[SortType.values().length];

        for (int i = 0; i < arrayTypesCount.length; i++)
            arrayTypesCount[i] = 0;

        for (int i = 0; i < sortTypesCount.length; i++)
            sortTypesCount[i] = 0;

        while (statisticIterator.hasNext()) {
            Statistic statistic = statisticIterator.next();
            switch (statistic.getArrayType()) {
                case SortedArrayWithRandomElement:
                    arrayTypesCount[0] += 1;
                    break;
                case ReversedSortedArray:
                    arrayTypesCount[1] += 1;
                    break;
                case SortedArray:
                    arrayTypesCount[2] += 1;
                    break;
                case RandomArray:
                    arrayTypesCount[3] += 1;
                    break;
            }
            switch (statistic.getSortType()) {
                case MergeSort:
                    sortTypesCount[0] += 1;
                    break;
                case QuickSort:
                    sortTypesCount[1] += 1;
                    break;
                case ArraysSort:
                    sortTypesCount[2] += 1;
                    break;
                case DirectedBundleSort:
                    sortTypesCount[3] += 1;
                    break;
                case ReversedBundleSort:
                    sortTypesCount[4] += 1;
                    break;
            }
        }

        int arrayTypeMin = 100;

        for(int i : arrayTypesCount)
            if (arrayTypeMin > i)
                arrayTypeMin = i;


        int sortTypeMin = 100;

        for(int i : sortTypesCount)
            if (sortTypeMin > i)
                sortTypeMin = i;

        assertEquals(arrayTypeMin, SortType.values().length);
        assertEquals(sortTypeMin, ArrayType.values().length);
    }
}
