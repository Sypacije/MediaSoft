import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class CarDealership {
    private ArrayList<Car> carsList;

    public CarDealership(ArrayList<Car> cars) {
        this.carsList = cars;
    }
    
    public void addCarToDealership(Car car) {
        boolean flag = false;
        if (!carsList.isEmpty()) {
            for (Car c : carsList) {
                if (c.equals(car)) {
                    System.out.println("\nАвтомобиль с таким VIN-номером уже присутсвует в диллерском центре");
                    flag = true;
                }
            }

            if (flag == false || carsList.isEmpty()) {
                carsList.add(car);
                System.out.println("\nАвтомобиль успешно добавлен в диллерский центр");
            }
        }
    }
    
    public void printDealershipCars() {
        System.out.println("\nТекущий список автомобилей в диллерском центре:\n");
        for (Car c : this.carsList) {
            System.out.println(c.toString());
        }
        System.out.println("\n");
    }

    public void printAllCarManufacturers() {
        Set<String> carManufacturersSet = new HashSet<>();
        for (Car c : this.carsList) {
            carManufacturersSet.add(c.getCarManufacturer());
        }

        System.out.println("\nТекущие производители:\n");

        for (String s : carManufacturersSet) {
            System.out.println(s);
        }

        System.out.println("\n");
    }

    public void getCarsByManufacturer() {
        printAllCarManufacturers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите производителя: ");
        String getManufacturer = scanner.nextLine();
        System.out.println("\n");
        List<Car> result = (this.carsList).stream()
            .filter(car -> car.getCarManufacturer().equals(getManufacturer))
            .toList();

        if (result.isEmpty()) {
            System.out.println("Автомобили такой марки отсутствует\n");
        } 
        
        else {
            result.forEach(System.out::println);
            System.out.println("\n");
        }

    }

    public void getAveragePriceByType() {
        System.out.println("\nТипы автомобилей:\n");

        for (Car.CarType t : Car.CarType.values()) {
            System.out.println(t);
        }

        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип автомобилей: ");
        String getType = scanner.nextLine().toUpperCase();
        System.out.println("\n");

        List<Car> result = (this.carsList).stream()
            .filter(car -> car.getCarType() == Car.CarType.valueOf(getType))
            .toList();
        
        Double averagePrice = result.stream()
            .mapToInt(Car::getCarPrice)
            .average()                     
            .orElse(0);
        
        System.out.println("Средняя цена на автомобили такого типа:\n" + averagePrice + "\n");
    }

    public void getCarsSortedByYear() {
        System.out.println("Автомобили, отсортированные по году выпуска:\n");
        this.carsList.stream()
        .sorted(Comparator.comparing(Car::getCarReleaseYear).reversed())
        .forEach(System.out::println);
        System.out.println("\n");
    }

    public void getStats() {
        System.out.println("Количество машин каждого типа:\n");
        Map<Car.CarType, Long> countByType = (this.carsList).stream()
            .collect(Collectors.groupingBy(Car::getCarType, Collectors.counting()));
        
        countByType.forEach((type, count) ->
            System.out.println(type + ": " + count));
        
        Car youngestCar = (this.carsList).stream()
            .sorted(Comparator.comparing(Car::getCarPrice).reversed())
            .findFirst()
            .orElse(null);
        
        Car oldestCar = (this.carsList).stream()
            .sorted(Comparator.comparing(Car::getCarPrice))
            .findFirst()
            .orElse(null);
        
        System.out.println("\n\nСамая старая машина в наличии:\n" + oldestCar.toString() + "\n\nСамая новая машина в наличии:\n" + youngestCar.toString());
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Меню управления автомобилями ===");
            System.out.println("1. Добавить автомобиль");
            System.out.println("2. Показать все автомобили");
            System.out.println("3. Показать все автомобили определенного производителя");
            System.out.println("4. Показать среднюю цену автомобилей определенного типа");
            System.out.println("5. Показать автомобили, отсортированные по дате выпуска");
            System.out.println("6. Показать статистику");
            System.out.println("7. Выход");
            System.out.print("Выберите опцию: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addCarToDealership(Car.readCarFromKeyboard());
                    break;
                case 2:
                    printDealershipCars();
                    break;
                case 3:
                    getCarsByManufacturer();
                    break;
                case 4:
                    getAveragePriceByType();
                    break;
                case 5:
                    getCarsSortedByYear();
                    break;
                case 6:
                    getStats();
                    break;
                case 7:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }
}

class main {
    public static void main(String[] args) {
        ArrayList<Car> testCars = new ArrayList<>(
            List.of(
                new Car("1HGCM82633A004352", "Accord", "Honda", 2015, 95000, 13500, Car.CarType.SEDAN),
                new Car("JHMFA16586S012345", "Civic", "Honda", 2018, 60000, 15500, Car.CarType.HATCHBACK),
                new Car("WDBRF40J33F398742", "C-Class", "Mercedes-Benz", 2014, 120000, 16500, Car.CarType.SEDAN),
                new Car("WAUZZZ8K9DA123456", "A4", "Audi", 2017, 78000, 19000, Car.CarType.SEDAN),
                new Car("3FAHP0HA6AR123789", "Fusion", "Ford", 2016, 110000, 9800, Car.CarType.SEDAN),
                new Car("1FTSW21R08E123654", "F-150", "Ford", 2019, 45000, 28500, Car.CarType.SUV),
                new Car("5YJ3E1EA7JF012367", "Model 3", "Tesla", 2020, 30000, 33000, Car.CarType.ELECTRIC),
                new Car("JN1BV7AP2FM123987", "Q50", "Infiniti", 2015, 89000, 14500, Car.CarType.SEDAN),
                new Car("JTDKN3DU0A0123456", "Prius", "Toyota", 2013, 150000, 8200, Car.CarType.HATCHBACK),
                new Car("SALVP2BG0FH123654", "Range Rover Evoque", "Land Rover", 2016, 85000, 22000, Car.CarType.CROSSOVER)
            )
        );

        CarDealership testCarDealership = new CarDealership(testCars);
        testCarDealership.menu();
    }

}
