package fg.hazmateasiermanagement;

import java.lang.reflect.Array;

/**
 * Utility class to hold static methods that doesn't belong to a specific part of the program.
 *
 * @author Stromner, David
 * @version 2014-10-19
 */

public class Utility {
    /**
     * Generic method to extends an array with one extra space.
     *
     * @param original to be extended.
     * @return array in inparam but with one extra space.
     */
    public static <T> T[] extendArray(T[] original, Class<T> cls){
        T copy[] = original.clone();
        original = (T[]) Array.newInstance(cls, copy.length + 1);
        System.arraycopy(copy, 0, original, 0, copy.length);
        return original;
    }
}
