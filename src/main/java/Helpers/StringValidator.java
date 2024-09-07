package Helpers;

public class StringValidator {
    public static boolean isValidString(String str) {
        return str != null && str.trim().length() >= 3 && str.trim().length() <= 200;
    }
}
