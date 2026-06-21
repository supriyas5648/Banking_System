package util;

public class AccountNumberGenerator {

    private AccountNumberGenerator() {
    }

    public static long generate() {

        return System.currentTimeMillis();
    }
}