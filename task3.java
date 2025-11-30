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
        this.isBooked = false;
    }

    public Room(int rNum, int maxPQ, int oneNP, boolean isB) {
        this.roomNumber = rNum;
        this.maxPeopleQuantity = maxPQ;
        this.oneNightPrice = oneNP;
        this.isBooked = isB;
    }
}

enum Prices {
    ECONOMY(1500, 2500),
    STANDARD(3000, 5000),
    LUX(6000, 9000),
    ULTRA_LUX(10000, 20000);
    
    private final int minPrice;
    private final int maxPrice;
    
    Prices(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    
    public int getRandomPrice() {
        Random random = new Random();
        return random.nextInt(maxPrice - minPrice + 1) + minPrice;
    }
    
    public int getMinPrice() {
        return minPrice;
    }
    
    public int getMaxPrice() {
        return maxPrice;
    }
}

class EconomyRoom extends Room { 
    public EconomyRoom() {
        super();
        this.setOneNightPrice(Prices.ECONOMY.getRandomPrice());
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
        this.setOneNightPrice(Prices.STANDARD.getRandomPrice());
    }
}

class LuxRoom extends ProRoom { 
    public LuxRoom() {
        super();
        this.setOneNightPrice(Prices.LUX.getRandomPrice());
    }
}

class UltraLuxRoom extends LuxRoom { 
    public UltraLuxRoom() {
        super();
        this.setOneNightPrice(Prices.ULTRA_LUX.getRandomPrice());
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

interface LuxRoomService<T extends LuxRoom> extends RoomService<T> {
    void foodDelivery(T room);
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

class LuxHotelRoomService <T extends LuxRoom> extends HotelRoomService<T> implements LuxRoomService<T> {
    
    @Override
    public void foodDelivery(T room) {
        System.out.println("Заказ на доставку еды в комнату " + room.getRoomNumber());
        
    }
}

public class task3 {
    public static void main(String[] args) {
        EconomyRoom ecRoom = new EconomyRoom();
        HotelRoomService<EconomyRoom> serviceForEconomyRoom = new HotelRoomService<>();
        serviceForEconomyRoom.reserve(ecRoom);

        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom();
        LuxHotelRoomService<UltraLuxRoom> serviceForUltraLuxRoom = new LuxHotelRoomService<>();
        serviceForUltraLuxRoom.reserve(ultraLuxRoom);
        serviceForUltraLuxRoom.free(ultraLuxRoom);
        serviceForUltraLuxRoom.clean(ultraLuxRoom);
        serviceForUltraLuxRoom.foodDelivery(ultraLuxRoom);

        LuxRoom luxRoom = new LuxRoom();
        LuxHotelRoomService<LuxRoom> serviceForLuxRoom = new LuxHotelRoomService<>();
        serviceForLuxRoom.reserve(luxRoom);
        serviceForLuxRoom.free(luxRoom);
        serviceForLuxRoom.clean(luxRoom);
        serviceForLuxRoom.foodDelivery(luxRoom);

        // StandardRoom stdRoom = new StandardRoom();
        // LuxHotelRoomService<StandardRoom> serviceForStandardRoom = new LuxHotelRoomService<>();
        // serviceForStandardRoom.reserve(stdRoom);
    }
}
