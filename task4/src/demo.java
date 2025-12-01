import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Random;

@DeprecatedEx(message = "Используйте NewService вместо этого класса")
class OldService {

    @DeprecatedEx(message = "Используйте newMethod()")
    public void oldMethod() {}

    public void newMethod() {}
}

public class demo {

    //Лямбда выражение для интерфейса Printable
    static Printable printable = () -> System.out.println("Hello World!");

    public static void main(String[] args) throws IllegalAccessException {
        //Тест для Printable
        printable.print();
        System.out.println("\n");

        //Тест для Predicates
        PredicatesUtil predicates = new PredicatesUtil();
        System.out.println(predicates.isNotNull.test("Test"));
        System.out.println(predicates.isNotNull.test(null));
        System.out.println(predicates.isNotEmpty.test("Test"));
        System.out.println(predicates.isNotEmpty.test(""));
        System.out.println(predicates.isNotNullAndNotEmpty.test(""));
        System.out.println(predicates.isNotNullAndNotEmpty.test(null));
        System.out.println(predicates.isNotNullAndNotEmpty.test("Test"));
        System.out.println(predicates.startsFromJorNAndEndsWithA.test("joanna"));
        System.out.println(predicates.startsFromJorNAndEndsWithA.test("Test"));
        System.out.println(predicates.startsFromJorNAndEndsWithA.test("joann"));
        System.out.println("\n");

        //Тест для кастомной аннотации @DeprecatedEx
        DeprecatedExHandler.checkDeprecatedEx(OldService.class);
        System.out.println("\n");

        //Лямбда выражение для HeavyBox
        HeavyBox box = new HeavyBox(50);

        Consumer<HeavyBox> sendBox = b -> System.out.println("Отправляем ящик с весом " + b.getWeight());
        Consumer<HeavyBox> shippedBox = b -> System.out.println("Отгрузили ящик с весом " + b.getWeight());
        Consumer<HeavyBox> processBox = sendBox.andThen(shippedBox);

        processBox.accept(box);
        System.out.println("\n");

        //Лямбда для Function
        Function<Integer, String> checkNumber = n -> {
            if (n > 0) return "Положительное число";
            else if (n < 0) return "Отрицательное число";
            else return "Ноль";
        };

        System.out.println(checkNumber.apply(10));   
        System.out.println(checkNumber.apply(-5));  
        System.out.println(checkNumber.apply(0));
        System.out.println("\n");

        //Лямбда для Supplier

        Random random = new Random();

        Supplier<Integer> randNumber = () -> random.nextInt(11);

        System.out.println(randNumber.get());
        System.out.println(randNumber.get());
        System.out.println(randNumber.get());
        System.out.println("\n");
        
        //Тест для кастомной аннотации JsonField

        Cat cat = new Cat("Salem", 4, "Polly");
        String json = JsonSerializer.serialize(cat);
        System.out.println(json);
    }
}
