package Hit;

/**
 * the class represent counter.
 */
public class Counter {

    private int count;

    /**
     * Constructor.
     *
     * @param count the number
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * default Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number that we want to add to current count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number that we want to subtract to current count.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return the current value
     */
    public int getValue() {
        return this.count;
    }
}
