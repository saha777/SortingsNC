package nc.com.fillers;

import nc.com.analyzer.annotations.Filler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A {@code ArraysFiller} provides array data from file.
 *
 * @author Oleksandr Smirnov
 * @see ArrayType
 * @see Scanner
 */
public class ArraysFiller {
    private static final int MIN_ARRAY_SIZE = 100;
    private static final int MAX_ARRAY_SIZE = 100000;
    public static int minArraySize = 0;

    public static final String RANDOM_PATH = "src/main/resources/RandomArray.txt";
    public static final String REVERSED_PATH = "src/main/resources/ReversedSortedArray.txt";
    public static final String SORTED_PATH = "src/main/resources/SortedArray.txt";
    public static final String SORTED_WITH_RANDOM_ELEMENT_PATH = "src/main/resources/SortedArrayWithRandomElement.txt";

    /**
     * The public method that recognizes the
     * type and returns the corresponding array.
     *
     * @param type the type of array.
     * @return the array according to type.
     */
    @Filler
    public static int[] getArray(ArrayType type){
        int[] array = null;
        switch (type){
            case RandomArray:
                array = readArray(RANDOM_PATH);
                break;
            case SortedArray:
                array = readArray(SORTED_PATH);
                break;
            case ReversedSortedArray:
                array = readArray(REVERSED_PATH);
                break;
            case SortedArrayWithRandomElement:
                array = readArray(SORTED_WITH_RANDOM_ELEMENT_PATH);
                break;
        }
        return array;
    }

    /**
     * The private method returns array int[] that contains
     * file with the same name as in param.
     * Throws exception SizeOfArrayException if file fas small array.
     *
     * @param filename the name of file.
     */
    private static int[] readArray(String filename){
        int[] array = new int[MAX_ARRAY_SIZE];
        int arraySize = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt() && arraySize < MAX_ARRAY_SIZE) {
                array[arraySize++] = scanner.nextInt();
            }
            scanner.close();

            if(arraySize > minArraySize) minArraySize = arraySize;

            if(arraySize < MIN_ARRAY_SIZE) throw new SizeOfArrayException();
        } catch (IOException | SizeOfArrayException e) {
            e.printStackTrace();
        }

        return array;
    }
}
