import java.lang.annotation.*;

//Объявление кастомной аннотации DeprecatedEx

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DeprecatedEx {
    String message();
}

