public class Time {
    private static final int SECONDS_PER_DAY = 24 * 60 * 60;

    // Attributes
    private int hour;
    private int minute;
    private int second;

    // No-argument constructor
    // Input: none
    // Output: 00:00:00
    // Purpose: default time
    // Approach: this(0, 0, 0)
    public Time() {
        this(0, 0, 0);
    }

    // Parameterized constructor
    // Input: hour, minute, second
    // Output: new time
    // Purpose: time with given values
    // Approach: out of range -> 0
    public Time(int hour, int minute, int second) {
        this.hour = (hour >= 0 && hour <= 23) ? hour : 0;
        this.minute = (minute >= 0 && minute <= 59) ? minute : 0;
        this.second = (second >= 0 && second <= 59) ? second : 0;
    }

    // Methods
    // Input: none
    // Output: prints HH:MM:SS
    // Purpose: show time
    // Approach: printf %02d
    public void display() {
        System.out.printf("%02d:%02d:%02d%n", hour, minute, second);
    }

    // Input: seconds (can be negative)
    // Output: none, time is updated
    // Purpose: shared code for add/subtract
    // Approach: total seconds + seconds, floorMod 86400, split to h/m/s
    private void shift(long seconds) {
        long total = hour * 3600L + minute * 60L + second + seconds;
        int wrapped = (int) Math.floorMod(total, (long) SECONDS_PER_DAY);
        hour = wrapped / 3600;
        minute = wrapped % 3600 / 60;
        second = wrapped % 60;
    }

    // Input: seconds
    // Output: none, time moves forward
    // Purpose: add seconds
    // Approach: shift(seconds)
    public void addSeconds(int seconds) {
        shift(seconds);
    }

    // Input: seconds
    // Output: none, time moves backward
    // Purpose: subtract seconds
    // Approach: shift(-seconds)
    public void subtractSeconds(int seconds) {
        shift(-(long) seconds);
    }
}
