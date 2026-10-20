public abstract class Singer {
    private String fullName;
    private int yearsOfExperience;
    private int albumsSold;
    private int performances;

    // Input: fullName, yearsOfExperience, albumsSold, performances
    // Output: new singer
    // Approach: numbers via setters
    public Singer(String fullName, int yearsOfExperience, int albumsSold, int performances) {
        this.fullName = fullName;
        setYearsOfExperience(yearsOfExperience);
        setAlbumsSold(albumsSold);
        setPerformances(performances);
    }

    // Input: none
    // Output: full name
    // Approach: return fullName
    public String getFullName() {
        return fullName;
    }

    // Input: none
    // Output: years of experience
    // Approach: return yearsOfExperience
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    // Input: yearsOfExperience
    // Output: none, years updated if valid
    // Approach: >= 0 -> update, else print error
    public void setYearsOfExperience(int yearsOfExperience) {
        if (yearsOfExperience >= 0) {
            this.yearsOfExperience = yearsOfExperience;
        } else {
            System.out.println("Years of experience must not be negative!");
        }
    }

    // Input: none
    // Output: albums sold
    // Approach: return albumsSold
    public int getAlbumsSold() {
        return albumsSold;
    }

    // Input: albumsSold
    // Output: none, albums updated if valid
    // Approach: >= 0 -> update, else print error
    public void setAlbumsSold(int albumsSold) {
        if (albumsSold >= 0) {
            this.albumsSold = albumsSold;
        } else {
            System.out.println("Albums sold must not be negative!");
        }
    }

    // Input: none
    // Output: number of performances
    // Approach: return performances
    public int getPerformances() {
        return performances;
    }

    // Input: performances
    // Output: none, performances updated if valid
    // Approach: >= 0 -> update, else print error
    public void setPerformances(int performances) {
        if (performances >= 0) {
            this.performances = performances;
        } else {
            System.out.println("Performances must not be negative!");
        }
    }

    // Input: none
    // Output: salary in VND
    // Approach: implemented by subclasses
    public abstract long calculateSalary();

    // Input: none
    // Output: text with common info
    // Approach: join fields; subclasses add their own and the salary
    @Override
    public String toString() {
        return "Name: " + fullName + "\n"
                + "Years of experience: " + yearsOfExperience + "\n"
                + "Albums sold: " + albumsSold + "\n"
                + "Performances: " + performances;
    }
}
