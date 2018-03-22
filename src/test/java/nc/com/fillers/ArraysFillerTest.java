package nc.com.fillers;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ArraysFillerTest {

    @Test
    public void getArrayTest(){
        assertTrue(Arrays.equals(ArraysFiller.getArray(ArrayType.RandomArray), readArray(ArraysFiller.RANDOM_PATH)));
        assertTrue(Arrays.equals(ArraysFiller.getArray(ArrayType.ReversedSortedArray), readArray(ArraysFiller.REVERSED_PATH)));
        assertTrue(Arrays.equals(ArraysFiller.getArray(ArrayType.SortedArrayWithRandomElement), readArray(ArraysFiller.SORTED_WITH_RANDOM_ELEMENT_PATH)));
        assertTrue(Arrays.equals(ArraysFiller.getArray(ArrayType.SortedArray), readArray(ArraysFiller.SORTED_PATH)));
    }

    private int[] readArray(String filename){
        int[] array = new int[100000];
        int arraySize = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt()) {
                array[arraySize++] = scanner.nextInt();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

}
