public class Room {
    private String roomNumber;
    private int capacity;
    private String building;

    public Room(String roomNumber, int capacity, String building) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getBuilding() {
        return building;
    }

    @Override
    public String toString() {
        return "Số Phòng: " + roomNumber + ", Sức Chứa: " + capacity + ", Tòa Nhà: " + building;
    }
}

