import java.util.Objects;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;
import java.util.stream.Collectors;

class Car implements Comparable<Car> {
    private String carVin;
    private String carModel;
    private String carManufacturer;
    private int carReleaseYear;
    private int carMileage;
    private int carPrice;

    public void setCarVin(String vin) {
        this.carVin = vin;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public void setCarReleaseYear(int carReleaseYear) {
        this.carReleaseYear = carReleaseYear;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarVin() {
        return carVin;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public int getCarReleaseYear() {
        return carReleaseYear;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public String getCarModel() {
        return carModel;
    }

    public Car(String vin, String model, String manufacturer, int releaseYear, int mileage, int price) {
        this.carVin = vin;
        this.carModel = model;
        this.carManufacturer = manufacturer;
        this.carReleaseYear = releaseYear;
        this.carMileage = mileage;
        this.carPrice = price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // сравнение по ссылке
        if (o == null || getClass() != o.getClass())
            return false;

        Car that = (Car) o;

        // сравнение по VIN
        return Objects.equals(this.carVin, that.carVin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carVin);
    }

    @Override
    public String toString() {
        return "VIN: " + carVin + ", Model: " + carModel + ", Manufacturer: " + carManufacturer + ", Release year: " + carReleaseYear + ", Mileage: " + carMileage + ", Price: " + carPrice;
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.carReleaseYear, this.carReleaseYear);
    }
}

public class task2_1 {

    public static void printCarsList(List<Car> list) {
        System.out.println("\n");
        for (Car c : list) {
            System.out.println(c.toString());
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        HashSet<Car> carsHashSet = new HashSet<>();
        ArrayList<Car> carsArrayList = new ArrayList<>();

        Car car1 = new Car("VIN001", "Camry", "Toyota", 2019, 45000, 21000);
        Car car2 = new Car("VIN002", "Accord", "Honda", 2018, 52000, 19500);
        Car car3 = new Car("VIN003", "Model 3", "Tesla", 2021, 12000, 35000);

        // Дубликаты по VIN
        Car car4 = new Car("VIN001", "Camry", "Toyota", 2019, 45000, 21000); // дубликат car1
        Car car5 = new Car("VIN003", "Model 3", "Tesla", 2021, 12000, 35000); // дубликат car3

        Car car6 = new Car("VIN004", "A4", "Audi", 2017, 80000, 17500);

        carsArrayList.add(car1);
        carsArrayList.add(car2);
        carsArrayList.add(car3);
        carsArrayList.add(car4);
        carsArrayList.add(car5);
        carsArrayList.add(car6);

        carsHashSet.add(car1);
        carsHashSet.add(car2);
        carsHashSet.add(car3);
        carsHashSet.add(car4);
        carsHashSet.add(car5);
        carsHashSet.add(car6);

        for (Car c : carsHashSet) {
            System.out.println(c.toString());
        }

        System.out.println("\n");
        System.out.println("До сортировки:\n");

        printCarsList(carsArrayList);

        Collections.sort(carsArrayList);

        System.out.println("После сортировки:");

        printCarsList(carsArrayList);

        //Задание 2.4 Stream API

        List<Car> carsList = new ArrayList<>();

        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car4);
        carsList.add(car5);
        carsList.add(car6);

        List<Car> lowMileageCars = carsList.stream()
        .filter(car -> car.getCarMileage() < 50000)
        .toList();

        List<Car> carsSortByPrice = carsList.stream()
        .sorted(Comparator.comparing(Car::getCarPrice).reversed())
        .toList();

        System.out.println("Три самые дорогие машины:\n");

        carsList.stream()
        .sorted(Comparator.comparing(Car::getCarPrice).reversed())
        .limit(3)
        .forEach(System.out::println);

        double averageMileage = carsList.stream()
        .mapToInt(Car::getCarMileage) 
        .average()                     
        .orElse(0);
        
        Map<String, List<Car>> carsByManufacturer = carsList.stream()
        .collect(Collectors.groupingBy(Car::getCarManufacturer));

        System.out.println("\nПробег меньше 50000:");
        printCarsList(lowMileageCars);

        System.out.println("Сортировка по цене:");
        printCarsList(carsSortByPrice);

        System.out.println("Средний пробег всех машин: " + averageMileage + " км\n");

        System.out.println("Автомобили по производителю:\n");
        
        for (Map.Entry<String, List<Car>> entry : carsByManufacturer.entrySet()) {
            String carsString = entry.getValue().stream()
                .map(Car::toString)                 
                .collect(Collectors.joining("\n"));
    
            System.out.println(entry.getKey() + ": " + carsString + "\n");
        }
    }
}
