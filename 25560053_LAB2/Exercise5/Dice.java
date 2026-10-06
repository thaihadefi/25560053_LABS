import java.util.Random;

public class Dice {
    private static final Random RANDOM = new Random();

    // Attributes
    private int value;

    // No-argument constructor
    // Input: none
    // Output: die with value 1
    // Purpose: new die
    // Approach: value = 1
    public Dice() {
        value = 1;
    }

    // Methods
    // Input: none
    // Output: new value 1-6
    // Purpose: roll the die
    // Approach: nextInt(6) + 1, save to value
    public int roll() {
        value = RANDOM.nextInt(6) + 1;
        return value;
    }
}
