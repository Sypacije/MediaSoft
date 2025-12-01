import java.lang.reflect.Method;

public class DeprecatedExHandler {
    public static void checkDeprecatedEx(Class<?> clazz) {
        // Проверка класса
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = clazz.getAnnotation(DeprecatedEx.class);
            System.out.println("! класс '" + clazz.getSimpleName() + "' устарел – альтернатива: '" + annotation.message() + "'");
        }

        // Проверка методов
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                System.out.println("! метод '" + method.getName() + "' устарел – альтернатива: '" + annotation.message() + "'");
            }
        }
    }
}
