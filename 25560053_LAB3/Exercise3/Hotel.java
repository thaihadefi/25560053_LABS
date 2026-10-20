import java.util.ArrayList;

public class Hotel {
    private ArrayList<Guest> guests;
    private ArrayList<Staff> staffList;

    // Input: none
    // Output: hotel with no guests and no staff
    // Approach: two empty ArrayLists
    public Hotel() {
        this.guests = new ArrayList<>();
        this.staffList = new ArrayList<>();
    }

    // Input: cccd
    // Output: true if a guest with this CCCD is staying
    // Approach: loop, compare getCccd()
    public boolean hasGuest(String cccd) {
        for (Guest g : guests) {
            if (g.getCccd().equals(cccd)) {
                return true;
            }
        }
        return false;
    }

    // Input: guest
    // Output: true if added, false if the CCCD already exists
    // Approach: reject duplicate CCCD, else guests.add(guest)
    public boolean addGuest(Guest guest) {
        if (hasGuest(guest.getCccd())) {
            return false;
        }
        guests.add(guest);
        return true;
    }

    // Input: cccd
    // Output: true if the guest was removed
    // Approach: loop by index, remove the guest with the same CCCD
    public boolean removeGuest(String cccd) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getCccd().equals(cccd)) {
                guests.remove(i);
                return true;
            }
        }
        return false;
    }

    // Input: employeeId
    // Output: true if a staff member with this ID exists
    // Approach: loop, compare getEmployeeId()
    public boolean hasStaff(String employeeId) {
        for (Staff s : staffList) {
            if (s.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    // Input: staff
    // Output: true if added, false if the employee ID already exists
    // Approach: reject duplicate employee ID, else staffList.add(staff)
    public boolean addStaff(Staff staff) {
        if (hasStaff(staff.getEmployeeId())) {
            return false;
        }
        staffList.add(staff);
        return true;
    }

    // Input: none
    // Output: total rental cost of all guests
    // Approach: loop, add getRentalCost()
    public long totalRevenue() {
        long total = 0;
        for (Guest g : guests) {
            total += g.getRentalCost();
        }
        return total;
    }

    // Input: none
    // Output: text with all staff, guests and revenue
    // Approach: join each person's overridden toString(), then revenue
    @Override
    public String toString() {
        String result = "Staff:\n";
        for (Person p : staffList) {
            result += p + "\n\n";
        }
        result += "Guests:\n";
        for (Person p : guests) {
            result += p + "\n\n";
        }
        result += "Total revenue: " + totalRevenue() + " VND";
        return result;
    }
}
