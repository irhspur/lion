package utility;

/**
 * Created by irhspur on 8/16/14.
 */
public class UtilityString {

    public static boolean caseIgnoredContains(String original, String input){
        return original.toLowerCase().contains(input.toLowerCase());
    }

    public static boolean isNUll(String input){
        return (input.equalsIgnoreCase("") || input.isEmpty() || input.equalsIgnoreCase(null));
    }

    public static boolean isNotNull(String input){
        return !isNUll(input);
    }
}
