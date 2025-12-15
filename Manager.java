import java.util.*;

public class Manager extends Employee{
    private String managerID;
    private final String passCode;
    private ArrayList<Room> rooms;
    private ArrayList<Staff> staff;

    public Manager(String name, int contactNumber, double Salary, String managerID, String passCode) {
        super(name, contactNumber, Salary);
        this.managerID = managerID;
        this.passCode = passCode;
    }

    public Room selectRoom(int roomNo) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo) 
                return room;
        } 
        System.out.println("Room Not Found! Guest Cannot be added!");
        return null;
        
        
    }

    public void addRoom(int roomNo, double pricePerNight, Hotel.RoomType rt) {
        Room room;
        boolean status = true; // on creation every room is by default available
        switch (rt) {
            case SINGLEROOM:
                room = new SingleRoom(roomNo, pricePerNight, status); break;
            case DOUBLEROOM:
                room = new DoubleRoom(roomNo, pricePerNight, status); break;
            case FAMILYROOM:
                room = new FamilyRoom(roomNo, pricePerNight, status); break;
            default:
                throw new IllegalArgumentException("RoomType Invalid!");
        }
        rooms.add(room);
        System.out.println("ROOM\n" + room + "\nSUCCESSFULLY ADDED!");
        
    }

    public void removeRoom(int roomNumber) {
        // checks if the room is booked. A booked room can not be removed in our system!
        for (Room room : rooms) { 
            if (room.getRoomNumber() == roomNumber && !room.getStatus()) {
                System.out.println("Room cannot be removed since it is Booked yet!");
                return;
            }
        }
        // below lambda expression performs room removing steps in just one line
        boolean removed = rooms.removeIf((r) -> 
                r.getRoomNumber() == roomNumber); 
        if (removed) System.out.println("Room removed Successfully");
        else System.out.println("Error Removing Room (Matbe Room Not Found)");
        
    }

    public void searchRoom(int roomNumber){
        if (!roomExists(roomNumber)) {
            System.out.println("Error Finding Room. Room may not Exist!");
            return;
        }
        
        for (Room room : rooms) {
            if(room.getRoomNumber() == roomNumber){
                System.out.println("Room with Room Number " + roomNumber + " is found");
                System.out.println(room);
            }
        }
        
    }
    public void displayAllRooms() {
        for (Room room : rooms) {
            System.out.println(room.getRoomNumber() + " " + room.AvailableOrBooked());
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addStaff(Staff s){
        staff.add(s);
    }

    public boolean staffExists(String staffID) {
        for (Staff s : staff) {
            if (s.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    public void removeStaff(String name, String staffID) {
        boolean found = false;

        for (Staff s : staff) {
            boolean nameMatch = s.getName().equalsIgnoreCase(name);
            boolean idMatch = s.getStaffID().equalsIgnoreCase(staffID);

            if (nameMatch && idMatch) {
                staff.remove(s);
                System.out.println(" Staff Member removed successfully: " + name + " (" + staffID + ")");
                found = true;
                break;
            }
            else if (nameMatch) {
                System.out.println("Name matches but ID does NOT match!");
                found = true;
            }
            else if (idMatch) {
                System.out.println(" ID matches but Name does NOT match!");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Staff Member found with name '" + name + "' or ID '" + staffID + "'");
        }
    }

    public void searchStaff(String name, String staffID) {
        boolean found = false;

        for (Staff s : staff) {
            boolean nameMatch = s.getName().equalsIgnoreCase(name);
            boolean idMatch = s.getStaffID().equalsIgnoreCase(staffID);

            if (nameMatch && idMatch) {
                System.out.println("Staff Member FOUND: Name and ID both match.");
                found = true;
                System.out.println("Staff Member details : ");
                System.out.println(s);
                break;
            }
            else if (nameMatch) {
                System.out.println("Name matches but ID does NOT match!");
                found = true;
                System.out.println("Staff Member details : ");
                System.out.println(s);
            }
            else if (idMatch) {
                System.out.println("ID matches but Name does NOT match!");
                found = true;
                System.out.println("Staff Member details : ");
                System.out.println(s);
            }
        }

        if (!found) {
            System.out.println("Staff NOT found!");
        }

    }
    public boolean displayRoomAvailability() {
        boolean isAvailable = false;
        System.out.print("Currently Available Rooms: ");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
                isAvailable = true;
    
            }
        }
        return isAvailable;
        
    }
    //checks if room already exist while creation
    public boolean roomExists(int roomNo) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Manager) || obj == null) {
            return false;
        }
        Manager m = (Manager) obj;
        return managerID.equals(m.managerID);
        
    }

    public String getManagerID() {
        return managerID;
    }

    public String getPassCode() {
        return passCode;
    }
    
 
}

