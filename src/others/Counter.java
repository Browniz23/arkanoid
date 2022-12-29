//ID: 316482355

package others;

/**
 * others.Counter - class for counting things.
 */
public class Counter {

    // the count.
    private int count;

    /**
     * Count constructor. create a others.Counter. count starts from an integer 'c' that method gets.
     * @param c - number to start count from.
     */
    public Counter(int c) {
        this.count = c;
    }

    /**
     * method increase the count in given number.
     * @param number - integer to increase count in.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * method subtract number from current count.
     * @param number - integer to decrease count in.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * method returns current count.
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}