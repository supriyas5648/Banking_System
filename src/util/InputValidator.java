package util;

public class InputValidator {

    private InputValidator() {
    }

    public static boolean isValidName(String name) {

        return (name != null && name.trim() != "");
    }

    public static boolean isValidAmount(double amount) {

        return amount > 0;
    }

    public static boolean isValidAccountNumber(long accountNo) {

        return accountNo > 0;
    }

    public static boolean isValidUsername( String username) {

        return (username != null && username.length() >= 4);
    }

    public static boolean isValidPassword(String password) {
        return (password != null && password.length() >= 4);
    }
}