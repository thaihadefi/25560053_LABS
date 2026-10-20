public class FamousSinger extends Singer {
    private int gameShows;

    // Input: fullName, yearsOfExperience, albumsSold, performances, gameShows
    // Output: new famous singer
    // Approach: super(...), gameShows via setter
    public FamousSinger(String fullName, int yearsOfExperience, int albumsSold, int performances, int gameShows) {
        super(fullName, yearsOfExperience, albumsSold, performances);
        setGameShows(gameShows);
    }

    // Input: gameShows
    // Output: none, game shows updated if valid
    // Approach: >= 0 -> update, else print error
    public void setGameShows(int gameShows) {
        if (gameShows >= 0) {
            this.gameShows = gameShows;
        } else {
            System.out.println("Game shows must not be negative!");
        }
    }

    // Input: none
    // Output: salary in VND
    // Approach: 5,000,000 + 500,000 x years + 1,200 x albums + 500,000 x performances + 500,000 x game shows
    @Override
    public long calculateSalary() {
        return 5000000 + 500000L * getYearsOfExperience() + 1200L * getAlbumsSold()
                + 500000L * getPerformances() + 500000L * gameShows;
    }

    // Input: none
    // Output: text with singer info, game shows and salary
    // Approach: super.toString(), then game shows and salary
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Game shows: " + gameShows + "\n"
                + "Salary: " + calculateSalary() + " VND";
    }
}
