package nc.com.sorters.impls;

import nc.com.sorters.Sorter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SorterTest {
    private Sorter sorter;

    private final int[][] arrays = {
            {6, 3, 6, 2, 9, 4, 39, 1},
            {8, 33, 4, 0, 2, 5, 34, 23, 6, 43, 5, 1, 433, 3, 5, 88, 7},
            {0, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {1, 2, 3, 4, 5, 6, 7, 0},
            null
    };

    private final int[][] sortedArrays = {
            {1, 2, 3, 4, 6, 6, 9, 39},
            {0, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 23, 33, 34, 43, 88, 433},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {0, 1, 2, 3, 4, 5, 6, 7},
            null
    };

    @Test(expected = NullPointerException.class)
    public void testArraysSorter() {
        sorter = new ArraysSorter(arrays[0]);
        for (int i = 0; i < arrays.length; i++) {
            sorter.setArray(arrays[i]);
            assertTrue(Arrays.equals(sorter.sort(), sortedArrays[i]));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testMergeSorter() {
        sorter = new MergeSorter(arrays[0]);
        for (int i = 0; i < arrays.length; i++) {
            sorter.setArray(arrays[i]);
            assertTrue(Arrays.equals(sorter.sort(), sortedArrays[i]));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testQuickSorter() {
        sorter = new QuickSorter(arrays[0]);
        for (int i = 0; i < arrays.length; i++) {
            sorter.setArray(arrays[i]);
            assertTrue(Arrays.equals(sorter.sort(), sortedArrays[i]));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testDirectedBundleSorter() {
        sorter = new DirectedBundleSorter(arrays[0]);
        for (int i = 0; i < arrays.length; i++) {
            sorter.setArray(arrays[i]);
            assertTrue(Arrays.equals(sorter.sort(), sortedArrays[i]));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testReversedBundleSorter() {
        sorter = new ReversedBundleSorter(arrays[0]);
        for (int i = 0; i < arrays.length; i++) {
            sorter.setArray(arrays[i]);
            assertTrue(Arrays.equals(sorter.sort(), sortedArrays[i]));
        }
    }
}
