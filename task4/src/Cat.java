//Тестовый класс для проверки работы кастомной аннотации JsonField

public class Cat {
    @JsonField(name = "cat_name")
    private String name;

    @JsonField(name = "cat_age")
    private int age;

    private String owner; 
    public Cat(String name, int age, String owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }
}

