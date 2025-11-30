import java.util.Random;

abstract class Room {
    private int roomNumber;
    private int maxPeopleQuantity;
    private int oneNightPrice;
    private boolean isBooked;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxPeopleQuantity() {
        return maxPeopleQuantity;
    }

    public void setMaxPeopleQuantity(int maxPeopleQuantity) {
        this.maxPeopleQuantity = maxPeopleQuantity;
    }

    public int getOneNightPrice() {
        return oneNightPrice;
    }

    public void setOneNightPrice(int oneNightPrice) {
        this.oneNightPrice = oneNightPrice;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Room() {
        Random random = new Random();
        this.roomNumber = random.nextInt(99) + 1;
        this.maxPeopleQuantity = random.nextInt(3) + 1;
        this.oneNightPrice = random.nextInt(10000 - 1000 + 1) + 1000;
        this.isBooked = false;
    }

    public Room(int rNum, int maxPQ, int oneNP, boolean isB) {
        this.roomNumber = rNum;
        this.maxPeopleQuantity = maxPQ;
        this.oneNightPrice = oneNP;
        this.isBooked = isB;
    }
}

class EconomyRoom extends Room { 
    public EconomyRoom() {
        super();
    }
}

abstract class ProRoom extends Room { 
    public ProRoom() {
        super();
    }
}

class StandardRoom extends ProRoom { 
    public StandardRoom() {
        super();
    }
}

class LuxRoom extends ProRoom { 
    public LuxRoom() {
        super();
    }
}

class UltraLuxRoom extends LuxRoom { 
    public UltraLuxRoom() {
        super();
    }
}

class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
    
    public RoomAlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}

interface RoomService<T extends Room> {
    void clean(T room);
    void reserve(T room);
    void free(T room);
}

class HotelRoomService <T extends Room> implements RoomService<T> {
    @Override
    public void clean(T room) {
        System.out.println("Комната " + room.getRoomNumber() + " очищена");
    }
    
    @Override
    public void reserve(T room) {
        if (!room.isIsBooked()) {
            room.setIsBooked(true);
            System.out.println("Комната " + room.getRoomNumber() + " зарезервирована");
        } else {
            throw new RoomAlreadyBookedException(
                "Комната " + room.getRoomNumber() + " уже забронирована!"
            );
        }
    }
    
    @Override
    public void free(T room) {
        if (room.isIsBooked()) {
            room.setIsBooked(false);
            System.out.println("Комната " + room.getRoomNumber() + " освобождена");
        } else {
            System.out.println("Комната " + room.getRoomNumber() + " уже свободна");
        }
    }
}

public class task3 {
    public static void main(String[] args) {
        EconomyRoom ecRoom = new EconomyRoom();
        HotelRoomService<EconomyRoom> serviceForEconomyRoom = new HotelRoomService<>();
        serviceForEconomyRoom.reserve(ecRoom);
        serviceForEconomyRoom.reserve(ecRoom);

        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom();
        HotelRoomService<UltraLuxRoom> serviceForUltraLuxRoom = new HotelRoomService<>();
        serviceForUltraLuxRoom.reserve(ultraLuxRoom);
        serviceForUltraLuxRoom.free(ultraLuxRoom);
        serviceForUltraLuxRoom.clean(ultraLuxRoom);
    }
}
