
import java.util.*;

public class Hotel {
    private Manager manager;
    private Receptionist receptionist;
    private ArrayList<Guest> guests;
    
    Scanner sc = new Scanner(System.in);

    public Hotel(Manager manager, Receptionist receptionist) {
        this.manager = manager;
        this.receptionist = receptionist;
        this.guests = new ArrayList<>();
    }

    public void displayMainMenu() {
        System.out.println("====== Hotel Management System ======");
        System.out.println("1. Login as Manager");
        System.out.println("2. Login as Receptionist");
        System.out.println("3. Exit");
        System.out.print("Enter your choice : ");
    }

    public void displayReceptionistMenu() {
        System.out.println("====== Receptionist Module ======");
        System.out.println("1. Check-in a new guest");
        System.out.println("2. Check-out guest");
        System.out.println("3. Search booking");
        System.out.println("4. Search Guest");
        System.out.println("5. Modify a Booking");
        System.out.println("6. Display Available rooms");
        System.out.println("7. Display all Bookings");
        System.out.println("8. Log out");
        System.out.print("Enter your choice : ");
    }

    public void displayManagerMenu() {
        System.out.println("====== Manager Module ======");
        System.out.println("1. Add new room");
        System.out.println("2. Remove room");
        System.out.println("3. Search Room");
        System.out.println("4. Display all rooms");
        System.out.println("5. Add Staff Member");
        System.out.println("6. Remove Staff Member");
        System.out.println("7. Search Staff Member");
        System.out.println("8. Log out");
        System.out.print("Enter your choice : ");

    }

    public boolean managerLogin() {
        int tries = 3;
        System.out.println("Enter Your Credentials:\nManager ID: ");
        if (sc.nextLine().equals(manager.getManagerID())) {
            System.out.println("Enter The PassCode: ");
            while (tries > 0) {
                if (sc.nextLine().equals(manager.getPassCode())) {
                    System.out.println("Manager [" + manager.getName() + "] ID: " + manager.getManagerID() + " Successfully Logged In!" );
                    return true;
                } else {
                    System.out.println("Wrong PassCode! [" + (--tries) + " Attempts Left]");
                }
            }
            return false;
        } else {
            System.out.println("Invalid ID!");
            return false;
        }
    

    }

    public boolean receptionistLogin() {
        int tries = 3;
        System.out.println("Enter Your Credentials:\nReceptionist ID: ");
        if (sc.nextLine().equals(receptionist.getReceptID())) {
            System.out.println("Enter The PassCode: ");
            while (tries > 0) {
                if (sc.nextLine().equals(receptionist.getPassCode())) {
                    System.out.println("Receptionist [" + receptionist.getName() + "] ID: " + receptionist.getReceptID() + " Successfully Logged In!" );
                    return true;
                } else {
                    System.out.println("Wrong PassCode! [" + (--tries) + " Attempts Left]");
                }
            }
            System.out.println("Unable to Log in! Returning To Main Portal.....");
            return false;
        } else {
            System.out.println("Invalid ID! Unable to Log in! Returning To Main Portal.....");
            return false;
        }

    }
    public boolean isAvailable(ArrayList<Room> r) {
        for (Room room : r) {
            if (room.getStatus())  {
                return true;
            }
        }
        System.out.println("No Rooms Currently Available!");
        return false;
    }

    public void checkInGuest() {
        // ArrayList<Room> rooms = manager.getRooms();
        //checking if all rooms are booked
        if (!manager.displayRoomAvailability()) 
            return;
        //prompting guest data
        System.out.println("Enter Guest Details : ");
        System.out.print("Enter name : ");
        String name = sc.nextLine();
        System.out.println("Enter contact Number : ");
        int contact = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter total family Members : ");
        int familyMember = sc.nextInt();
        sc.nextLine();

        Guest guest = new Guest(name, contact, familyMember);
        guests.add(guest);
        // will display all rooms along with their data for only available rooms
        manager.displayRoomAvailability();
        // retreiving room info
        System.out.print("Select Room No: ");
        int no = sc.nextInt();
        Room r = manager.selectRoom(no);
      
        // calling the receptionist function
        receptionist.checkIn(r, guest);

    }

    public void checkOutGuest() {
        for (Guest g : guests) {
            System.out.println(g);
        }
        boolean found = false;
        Guest guest = null;
        System.out.println("Enter the Guest ID to check out: ");
        String id = sc.nextLine();
        for (Guest g : guests) {
            if (g.getGuestID().equals(id)) {
                guests.remove(g);
                guest = g;
                found = true;
                break;
            } 
        }
        if (!found) {
            System.out.println("Guest Not Found!");
            return;
        }
        receptionist.checkOut(guest);

    }

    public void searchBooking() {
        receptionist.displayBookingIDs();
        System.out.println("Enter Booking ID: ");
        int id = sc.nextInt();
        receptionist.displayBookingHistory(id);
        
    }

    public void searchGuest() {
        displayGuestIDs();
        System.out.println("Enter Guest ID: ");
        String id = sc.nextLine();
        receptionist.displayGuestHistory(id);

    }

    public void displayGuestIDs() {
        System.out.println("Guest IDs [ ");
        for (Guest guest : guests) {
            System.out.print((guest.getGuestID() + ", "));
        }
        System.out.println(" ]");
    }

    public void modifyBooking() {
        System.out.println("Modifying Booking...");
        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        
        while (true) {
            if (!manager.roomExists(roomNo)) {
                System.out.println("Error Finding Room. Room may not be booked or Room may not exist!\nTry a different room No.");
                roomNo = sc.nextInt();
                continue;
            }
            break;

        }
        System.out.println("Enter Guest Details : ");
        System.out.print("Enter name : ");
        String name = sc.nextLine();
        System.out.println("Enter contact Number : ");
        int contact = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter total family Members : ");
        int familyMember = sc.nextInt();
        sc.nextLine();

        Guest guest = new Guest(name, contact, familyMember);

        receptionist.modifyGuestInBooking(roomNo, guest); //calling the receptionist method



    }

    public void displayAvailableRooms() {
        manager.displayRoomAvailability();

    }

    public void displayAllBookings() {
        receptionist.displayBookingHistory();

    }


    // Manager methods
    public void addRoom() {
        Room room;
        RoomType rt = null;
        while (rt == null) {
            System.out.println("1.SINGLEROOM..2.DOUBLEROOM..3.FAMILYROOM\nEnter Room Type: ");
            int n = sc.nextInt();
            switch (n) {
                case 1: rt = RoomType.SINGLEROOM; break;
                case 2: rt = RoomType.DOUBLEROOM; break;
                case 3: rt = RoomType.FAMILYROOM; break;
                default: System.out.println("Invalid Input. TryAgain!"); 
            }

        }
        int roomNo;
        while (true) {
            System.out.println("Enter Room Data [RoomNo..PricePerNight]");
            roomNo = sc.nextInt();
            if (manager.roomExists(roomNo)) {
                System.out.println("Room " + roomNo + "Already Exists!\nTry Entring a new Room No;");
                continue;
            }
            break;
        }  
        double ppn = sc.nextDouble();
        manager.addRoom(roomNo, ppn, rt);
        

    }

    public void removeRoom() {
        System.out.println("Enter roomNo: ");
        int n = sc.nextInt();
        manager.removeRoom(n); 

    }

    public void searchRoom() {
        System.out.println("Enter Room Number : ");
        int roomNumber = sc.nextInt();

        manager.searchRoom(roomNumber);
        

    }

    public void displayRooms() {
        System.out.println("Rooms And Their Status!");
        manager.displayAllRooms();

    }

    public void addStaff() {
        Staff staff;
        String id;
        System.out.println("Enter Staff Details: [StaffID..Name..Contact Number..Salary..StaffDescription..ShiftType");
        while (true) { 
            System.out.print("ID: ");
            id = sc.nextLine();
            if (manager.staffExists(id)) {
                System.out.print("Staff with the given ID already exists.\nTry Again with a new ID: ");
                continue;
            }
            break;
            
        }
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Contact: ");
        int ct = sc.nextInt();
        System.out.print("Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine(); //consumes the newline character from input buffer
        System.out.println("Description: ");
        String desc = sc.nextLine();
        System.out.println("Shift [DAY / NIGHT / ALLDAY]");
        String sh;
        Staff.Shift shift = null;
        while (shift == null) { 
            try {
                sh = sc.nextLine().toUpperCase();
                shift = Staff.Shift.valueOf(sh); //enum used!!!!! .valueOf(sh) converts the string inside into enum and if it does not matches throws illegalArgument exception;
                break;
            } catch (IllegalArgumentException ie) {
                System.out.println("Shift can only be [DAY / NIGHT / ALLDAY].");
            }
        }
        staff = new Staff(name, ct, sal, id, desc, shift);
        manager.addStaff(staff);
    }

    public void removeStaff() {
        System.out.println("Removing Staff...");
        System.out.print("Enter Staff ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        manager.removeStaff(name, id);
        
    }

    public void searchStaff() {
        System.out.println("Searching Staff...");
        System.out.print("Enter Staff ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        manager.searchStaff(name, id);
    }




    public void run() {
        boolean exit = false;

        while(!exit) {
            displayMainMenu();
            int choice = Input.sc.nextInt();

            switch (choice) {
                case 1:
                    // Manager Module
                    boolean logout = false;
                    System.out.println("Sucessfully Logged in as Manger!\n");
                    while(!logout) {
                        displayManagerMenu();
                        int mChoice = Input.sc.nextInt();

                        switch (mChoice) {
                            case 1: addRoom(); break;
                            case 2:removeRoom();break;
                            case 3:searchRoom();break;
                            case 4:displayRooms();break;
                            case 5:addStaff();break;
                            case 6:removeStaff();break;
                            case 7:searchStaff();break;
                            case 8:logout = true;break;
                            default:
                                System.out.println("Invalid choice. Try Again...");
                                break;
                        }
                    } 
                    
                    break;
                
                // Receptionist module
                case 2:
                    System.out.println("Login Succesfull as Receptionist");
                    logout = false;

                    while (!logout) {
                        displayReceptionistMenu();
                        int rChoice = Input.sc.nextInt();
                        switch (rChoice) {
                            case 1:checkInGuest();break;
                            case 2:checkOutGuest();break;
                            case 3:searchBooking();break;
                            case 4:searchGuest();break;
                            case 5:modifyBooking();break;
                            case 6:displayAvailableRooms();break;
                            case 7:displayAllBookings();break;
                            case 8:logout = true;break;
                            default:
                                System.out.println("Invalid choice. Enter Again...");
                                break;
                        }
                    }
                default:
                    break;
            }
        }
    }
    public enum RoomType { //this concept is used to make room creation of any specific type in a way that deals less with strings upper case lowercase and unwanted prompts.
        SINGLEROOM,
        DOUBLEROOM,
        FAMILYROOM
    
    }
    
 
}


