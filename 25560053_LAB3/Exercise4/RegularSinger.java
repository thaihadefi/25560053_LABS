public class RegularSinger extends Singer {
    // Input: fullName, yearsOfExperience, albumsSold, performances
    // Output: new regular singer
    // Approach: super(...)
    public RegularSinger(String fullName, int yearsOfExperience, int albumsSold, int performances) {
        super(fullName, yearsOfExperience, albumsSold, performances);
    }

    // Input: none
    // Output: salary in VND
    // Approach: 3,000,000 + 500,000 x years + 1,000 x albums + 200,000 x performances
    @Override
    public long calculateSalary() {
        return 3000000 + 500000L * getYearsOfExperience() + 1000L * getAlbumsSold()
                + 200000L * getPerformances();
    }

    // Input: none
    // Output: text with singer info and salary
    // Approach: super.toString(), then salary
    @Override
    public String toString() {
        return super.toString() + "\n" + "Salary: " + calculateSalary() + " VND";
    }
}
