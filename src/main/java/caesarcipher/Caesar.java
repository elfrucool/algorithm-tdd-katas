package caesarcipher;

import java.util.Scanner;

public class Caesar {
    public static final int ALPHABET_LENGTH = 'z' - 'a';
    public static final int CASE_INCREMENT = 'a' - 'A';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // ignore this
        System.out.println(encrypt(scanner.nextLine(), scanner.nextInt()));
    }

    public static String encrypt(String s, int k) {
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length; index++)
            chars[index] = toUpper(encryptIfConvertible(toLower(chars[index]), k), chars[index]);
        return new String(chars);
    }

    protected static char encryptIfConvertible(char c, int k) {
        return isLower(c) ? encryptChar(c, k) : c;
    }

    protected static char encryptChar(char c, int k) {
        return (char) ((c - 'a' + k) % (ALPHABET_LENGTH + 1) + 'a');
    }

    protected static char toUpper(char c, char original) {
        return isUpper(original) ? (char) (c - CASE_INCREMENT) : c;
    }

    protected static char toLower(char c) {
        return isUpper(c) ? (char) (c + CASE_INCREMENT) : c;
    }

    private static boolean isUpper(char c) {
        return 'A' <= c && c <= 'Z';
    }

    private static boolean isLower(char c) {
        return 'a' <= c && c <= 'z';
    }
}
