import java.lang.reflect.Field;

//реализатор класса-сериализатора JsonSerializer

public class JsonSerializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();
        boolean firstField = true;

        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                String jsonName = annotation.name();
                Object value = field.get(obj);

                if (!firstField) {
                    jsonBuilder.append(", ");
                }
                jsonBuilder.append("\"").append(jsonName).append("\": ");

                if (value instanceof String) {
                    jsonBuilder.append("\"").append(value).append("\"");
                } else {
                    jsonBuilder.append(value);
                }

                firstField = false;
            }
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
