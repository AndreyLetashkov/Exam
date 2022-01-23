package utils;

public class StringUtils {
    public static String getNumber(String text) {
        return text.replaceAll("[^0-9]","");
    }
}