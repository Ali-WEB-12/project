import java.util.*;
class Receptionist extends Employee{
    private String receptID;
    private final String passCode;
    ArrayList<Booking> bookings;

    public Receptionist(String name, int contactNumber, double Salary, String receptID, String passCode) {
        super(name, contactNumber, Salary);
        this.receptID = receptID;
        this.passCode = passCode;
        this.bookings = new ArrayList<>();
    }
    

    public void checkIn(Room r, Guest g) {
        Booking book = new Booking(r, g);
        book.changeRoomStatus(book.getRoom(), false);
        // book.getRoom().setStatus(false); we can also do this to change room status. Konsa sahi hay?
        bookings.add(book);
    }
        

    public void checkOut(Guest g){
        for (Booking booking : bookings) {
            if (booking.getGuests().equals(g)) { //i.e their ids are same
                booking.changeRoomStatus(booking.getRoom(), true);
                bookings.remove(booking);
                System.out.println("Guest Has been Removed successfully!");
            }
        }
    }
    
    
    public void modifyGuestInBooking(int roomNumber, Guest newGuest) {
    for (Booking b : bookings) {
        if ((b.getRoom()).getRoomNumber() == roomNumber) {
            b.setGuests(newGuest); 
            System.out.println("Guest in room " + roomNumber + " updated successfully.");
            return;
        }
    }
        System.out.println("Booking for room " + roomNumber + " not found.");
    }

   
    public void displayGuestHistory(String guestID) {
        for (Booking book : bookings) {
            if (book.getGuests().getGuestID().equals(guestID)) {
                System.out.println(book.getGuests());
            }
        }
    }

    
    public void displayBookingIDs() {
        System.out.println("Booking IDs: [");
        for (Booking book : bookings) {
            System.out.println(book.getBookingID() + " , ");
        }
        System.out.println("]");
        
    }
    public void displayBookingHistory() {
        System.out.println("Booking History: ");
        for (Booking book : bookings) {
            System.out.println(book);
        }
    }
    //overloaded method example (for just a single booking)
    public void displayBookingHistory(int bookingID) {
        for (Booking book : bookings) {
            if (book.getBookingID() == bookingID) {
                System.out.println(book);
            }
        }
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Receptionist)) {
            return false;
        }
        Receptionist recp = (Receptionist) obj;
        return this.receptID != null && receptID.equals(recp.receptID);
   
    }

    public String getReceptID() {
        return receptID;
    }

    public String getPassCode() {
        return passCode;
    }
}