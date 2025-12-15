

public abstract  class Room {
    private int roomNumber;
    private double pricePerNight;
    private boolean status;

    public Room(int roomNumber, double pricePerNight, boolean status) { // status = false for newly added room?
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public abstract boolean isAvailable();
    public abstract double calculatePrice();

    public int getRoomNumber() {
        return roomNumber;
    }
    public void changeStatus(Room r, boolean b) {
        r.setStatus(b);
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean getStatus() {
        return status;
    }

    public String AvailableOrBooked() {
        if (this.getStatus()) return "AVAILABLE";
        else return "Booked";
    }
    
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Room room = (Room) obj;
        return this.roomNumber == room.roomNumber;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room{");
        sb.append("roomNumber=").append(roomNumber);
        sb.append(", pricePerNight=").append(pricePerNight);
        if (getStatus()) 
            sb.append(", status=").append("Available");
        else 
            sb.append(", status=").append("Booked");
        sb.append('}');
        return sb.toString();
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

class SingleRoom extends Room {
    private final String bedType = "Twin";
    private final int maxCapacity = 1;
    private final double price = 2000;

    public SingleRoom(int roomNumber, double pricePerNight, boolean status) {

        super(roomNumber, pricePerNight, status);
    }

    public boolean isAvailable(){
        return getStatus();  
    }

    public double calculatePrice(){
       return price * getPricePerNight();
    }

    public String getBedType() {
        return bedType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleRoom{");
        sb.append("bedType=").append(bedType);
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", price=").append(price);
        sb.append('}');
        return super.toString() + sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        SingleRoom room = (SingleRoom) obj;
        return this.getRoomNumber() == room.getRoomNumber();
    }


}

class DoubleRoom extends Room {
    private final String bedType = "Queen";
    private final int maxCapacity = 2;
    private final double price = 4000;

    public DoubleRoom(int roomNumber, double pricePerNight, boolean status) {
        super(roomNumber, pricePerNight, status);
    }

    public boolean isAvailable(){
        return getStatus();  
    }

    public double calculatePrice(){
       return price * getPricePerNight();
    }

    public String getBedType() {
        return bedType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DoubleRoom{");
        sb.append("bedType=").append(bedType);
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", price=").append(price);
        sb.append('}');
        return super.toString() + sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        DoubleRoom room = (DoubleRoom) obj;
        return this.getRoomNumber() == room.getRoomNumber();
    }

}
class FamilyRoom extends Room {
    private final String bedType = "Bunk";
    private final int maxCapacity = 4;
    private final double price = 5500;

    public FamilyRoom(int roomNumber, double pricePerNight, boolean status) {
        super(roomNumber, pricePerNight, status);
    }

    public boolean isAvailable(){
        return getStatus();  
    }

    public double calculatePrice(){
       return price * getPricePerNight();
    }

    public String getBedType() {
        return bedType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FamilyRoom{");
        sb.append("bedType=").append(bedType);
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", price=").append(price);
        sb.append('}');
        return super.toString() + sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(this == null || this.getClass() != obj.getClass()) {
            return false;
        }
        FamilyRoom room = (FamilyRoom) obj;
        return this.getRoomNumber() == room.getRoomNumber();
    }
    
    
 

}