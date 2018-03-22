package nc.com.fillers;
/**
* Signals that an small size of array exception of some sort has occurred. This
* class is the general class of exceptions produced by getting smaller array {@link ArraysFiller#readArray(String)}.
*
* @author Oleksandr Smirnov
*/
public class SizeOfArrayException extends Exception {
    /**
     * This constructor is useful for small size of array exceptions.
     */
    public SizeOfArrayException() {
        super(new Throwable("Arrays must have least than 100001 and more than 99 elements."));
    }
}
