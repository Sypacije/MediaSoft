import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;

class Car {
    private int carReleaseYear[] = new int[50];
    private List<String> carModels;

    Random random = new Random();

    public void setCarReleaseYear() {
        for (int i = 0; i < 50; i++) {
            int value = random.nextInt(2025 - 2000 + 1) + 2000;
            carReleaseYear[i] = value;
        }
    }

    public void setCarModels() {
        carModels = List.<String>of("Tesla Model S", "Tesla Model 3", "Toyota Camry", "BMW X5", 
        "Tesla Model X", "Honda Civic", "Audi A6", "Tesla Model Y", "Mercedes-Benz C200", "Ford Focus", 
        "Nissan Altima", "Hyundai Elantra", "Kia Sportage", "Lexus RX350", "Mazda CX-5", "Volkswagen Passat", 
        "Subaru Forester", "Toyota Corolla", "BMW 320i", "Tesla Model 3", "Audi Q7", "Mercedes-Benz GLE", 
        "Honda Accord", "Ford Explorer", "Nissan Qashqai", "Hyundai Tucson", "Kia Rio", "Lexus ES250", "Mazda 6", 
        "Volkswagen Tiguan", "Subaru Outback", "Toyota RAV4", "BMW X3", "Audi A4", "Mercedes-Benz E220", 
        "Tesla Model S", "Honda CR-V", "Ford Mustang", "Nissan Murano", "Hyundai Sonata", "Kia Sorento", 
        "Lexus GX460", "Mazda 3", "Volkswagen Golf", "Subaru XV", "Toyota Highlander", "Tesla Model Y", 
        "BMW X7", "Mazda CX-9", "Audi Q5");
    }

    public void processingCarModels() {
        List<String> carModelsNoDuplicates = new ArrayList<>(new HashSet<>(carModels));
        carModelsNoDuplicates.sort(Comparator.reverseOrder());
        for (String s : carModelsNoDuplicates) {
            System.out.println(s);
        }
        System.out.println("\n");
        Set<String> carModelsNoDuplicatesSet = new HashSet<>(carModelsNoDuplicates);
        Set<String> result = new HashSet<>();

        for (String s : carModelsNoDuplicatesSet) {
            if (s.contains("Tesla")) {
                result.add(s.replace("Tesla", "ELECTRO_CAR"));
            }
            else {
                result.add(s);
            }
        }

        for (String s : result) {
            System.out.println(s);
        }
    }

    public void getCarReleaseYearAfter2015() {
        System.out.println("Годы выпуска авто после 2015 года: ");
        for (int y : carReleaseYear) {
            if (y > 2015) {
                System.out.println(y);
            }
        }
    }

    private int getCarAge(int carYear) {
        return (2025 - carYear);
    }

    public void getAverageCarAge() {
        int yearsSummary = 0;
        for (int y : carReleaseYear) {
            yearsSummary += getCarAge(y);
        }
        System.out.println("\nСредний возраст автомобилей: ");
        System.out.println(yearsSummary / 50 + "\n");
    }
    
}

public class task2 {
    public static void main(String[] args) {
        Car testCar = new Car();
        testCar.setCarReleaseYear();
        testCar.getCarReleaseYearAfter2015();
        testCar.getAverageCarAge();
        testCar.setCarModels();
        testCar.processingCarModels();
    }
}


