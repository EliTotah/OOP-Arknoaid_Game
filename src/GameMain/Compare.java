package GameMain;

/**
 * the class is to compare between 2 doubles.
 */
public class Compare {
    private static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * .
     * the method check if 2 doubles are equals
     *
     * @param a double number
     * @param b double number
     * @return true/false
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }
}
