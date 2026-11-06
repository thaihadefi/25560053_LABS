public class LabCourse extends CourseRegistration {
    private static final double FEE_PER_CREDIT = 600000;
    private double equipmentFee;

    // Input: courseCode, courseName, credits, registeredStudents, equipmentFee
    // Output: new lab course
    // Approach: super(...), equipment fee via setter
    public LabCourse(String courseCode, String courseName, int credits, int registeredStudents,
            double equipmentFee) {
        super("Laboratory", courseCode, courseName, credits, registeredStudents);
        setEquipmentFee(equipmentFee);
    }

    // Input: equipmentFee
    // Output: none, equipment fee updated
    // Approach: < 0 -> exception, else update
    public void setEquipmentFee(double equipmentFee) {
        if (equipmentFee < 0) {
            throw new IllegalArgumentException("Equipment fee must not be negative");
        }
        this.equipmentFee = equipmentFee;
    }

    // Input: none
    // Output: tuition per student in VND
    // Approach: credits x 600,000 + equipment fee
    @Override
    public double tuitionPerStudent() {
        return getCredits() * FEE_PER_CREDIT + equipmentFee;
    }

    // Input: none
    // Output: prints common info and equipment fee
    // Approach: super.displayInfo(), then equipment fee
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Equipment fee: %,.2f VND%n", equipmentFee);
    }
}
