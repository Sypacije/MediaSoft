import java.util.function.Predicate;

public class PredicatesUtil {
    Predicate<String> isNotNull = string -> string != null;
    Predicate<String> isNotEmpty = string -> !string.isEmpty();
    Predicate<String> isNotNullAndNotEmpty = isNotNull.and(isNotEmpty);

    Predicate<String> startsFromJorN = string -> (string.toUpperCase()).startsWith("J") || (string.toUpperCase()).startsWith("N");
    Predicate<String> endsWithA = string -> (string.toUpperCase()).endsWith("A");
    Predicate<String> startsFromJorNAndEndsWithA = startsFromJorN.and(endsWithA);
}
