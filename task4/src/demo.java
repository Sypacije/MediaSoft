@DeprecatedEx(message = "Используйте NewService вместо этого класса")
class OldService {

    @DeprecatedEx(message = "Используйте newMethod()")
    public void oldMethod() {}

    public void newMethod() {}
}

public class demo {

    static Printable printable = () -> System.out.println("Hello World!");

    public static void main(String[] args) {
        printable.print();

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

        DeprecatedExHandler.checkDeprecatedEx(OldService.class);
    }
}
