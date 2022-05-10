package calculator.util;

import java.util.Arrays;

public class SplitUtils {
    public static int[] splitToInt(String string, String separator) {
        try {
            return Arrays.stream(string.split(separator))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
