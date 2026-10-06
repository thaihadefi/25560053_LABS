public class Main {
    // Input: none
    // Output: times before/after change
    // Purpose: test Time
    // Approach: 23:59:50 + 20s, 00:00:10 - 20s, default time + 90061s, invalid input
    public static void main(String[] args) {
        Time t1 = new Time(23, 59, 50);
        System.out.print("t1 = ");
        t1.display();
        t1.addSeconds(20);
        System.out.print("t1 + 20s = ");
        t1.display();

        Time t2 = new Time(0, 0, 10);
        System.out.print("t2 = ");
        t2.display();
        t2.subtractSeconds(20);
        System.out.print("t2 - 20s = ");
        t2.display();

        Time t3 = new Time();
        System.out.print("t3 = ");
        t3.display();
        t3.addSeconds(90061);
        System.out.print("t3 + 90061s (1 day 1h 1m 1s) = ");
        t3.display();

        Time t4 = new Time(25, 70, -5);
        System.out.print("Time(25, 70, -5) = ");
        t4.display();
    }
}
