package caesarcipher;

import java.util.Scanner;

public class Caesar {
    public static final int ALPHABET_LENGTH = 'z' - 'a';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // ignore this
        System.out.println(encrypt(scanner.nextLine(), scanner.nextInt()));
    }

    public static String encrypt(String s, int k) {
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length; index++) if (isConvertible(chars[index]))
                chars[index] = encryptChar(chars[index], k);
        return new String(chars);
    }

    protected static boolean isConvertible(char c) {
        return isLower(c) || isUpper(c);
    }

    private static boolean isUpper(char c) {
        return 'A' <= c && c <= 'Z';
    }

    private static boolean isLower(char c) {
        return 'a' <= c && c <= 'z';
    }

    protected static char encryptChar(char c, int k) {
        char base = isLower(c) ? 'a' : 'A';
        return (char) ((c - base + k) % (ALPHABET_LENGTH + 1) + base);
    }
}
