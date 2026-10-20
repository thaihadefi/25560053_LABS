public class Person {
    public static final int MAX_AGE = 150;
    private String fullName;
    private int age;

    // Input: fullName, age
    // Output: new person
    // Approach: age via setter
    public Person(String fullName, int age) {
        this.fullName = fullName;
        setAge(age);
    }

    // Input: age
    // Output: none, age updated if valid
    // Approach: in [1, MAX_AGE] -> update, else print error
    public void setAge(int age) {
        if (age > 0 && age <= MAX_AGE) {
            this.age = age;
        } else {
            System.out.println("Age must be from 1 to " + MAX_AGE + "!");
        }
    }

    // Input: none
    // Output: text with name and age
    // Approach: join fields; subclasses add their own
    @Override
    public String toString() {
        return "Name: " + fullName + "\n" + "Age: " + age;
    }
}
