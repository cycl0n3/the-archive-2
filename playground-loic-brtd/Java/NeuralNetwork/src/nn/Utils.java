package nn;

import java.util.Random;

public class Utils {

    private static final Random rand = new Random();

    public static float randomFloat() {
        return rand.nextFloat() * 2 - 1;
        // return rand.nextFloat();
    }
}
