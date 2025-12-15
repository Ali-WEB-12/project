

public class Booking {

    private Room room;
    private Guest guests;
    private int bookingID;
    private static int idCounts = 1;
    // protected static ArrayList<Integer> bookingData = new ArrayList<>();
    
    public Booking() {
    }
    
    public Booking(Room room, Guest guests) {
        this.bookingID = idCounts++;
        this.room = room;
        this.guests = guests;
        // bookingData.add(this.bookingID++);
        
    }
    
    public int getBookingID() {
        return bookingID;
    }
    
    // public static ArrayList<Integer> getBookingData() {
    //     return Booking.bookingData;
    // }
    
    public Room getRoom() {
        return room;
    }
    public void changeRoomStatus(Room r, boolean  b) {
        room.changeStatus(r, b);
    }

    public Guest getGuests() {
        return guests;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setGuests(Guest guests) {
        this.guests = guests;
    } 
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BOOKING ID " + this.bookingID + "\n");
        sb.append("Room Data:\n");
        sb.append(room.toString());
        sb.append("\nGuest Data:\n");
        sb.append(guests.toString());
        sb.append("/n===========");
        return sb.toString();

    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    
}
