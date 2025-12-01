import java.lang.annotation.*;

//Объявление кастомной аннотации JsonField

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}