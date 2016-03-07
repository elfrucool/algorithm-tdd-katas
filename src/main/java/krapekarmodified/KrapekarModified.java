package krapekarmodified;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KrapekarModified {
    private static final long[] DIGITS_MARKER = {10, 100, 1000, 10000, 100000};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = getKrapekarModifiedNumbersInRange(scanner.nextInt(), scanner.nextInt());
        if (numbers.isEmpty())
            System.out.println("INVALID RANGE");
        else
            System.out.println(String.join(" ", numbers.stream().map(Object::toString).collect(Collectors.toList())));
    }

    public static List<Integer> getKrapekarModifiedNumbersInRange(int from, int to) {
        List<Integer> krapekarModifiedNumbers = new ArrayList<>();

        for (int n = from; n <= to; n++)
            if (isKrapekarModified(n))
                krapekarModifiedNumbers.add(n);

        return krapekarModifiedNumbers;
    }

    public static boolean isKrapekarModified(int number) {
        int digits = digitsInNumber(number);
        long square = number * (long) number;
        return number == getHigherDigits(square, digits) + getLowerDigits(square, digits);
    }

    public static int digitsInNumber(int number) {
        return number > 99
                ? number > 9999
                ? 5
                : number > 999 ? 4 : 3
                : number > 9 ? 2 : 1;
    }

    public static int getLowerDigits(long number, int digits) {
        return (int) (number % DIGITS_MARKER[digits - 1]);
    }

    public static int getHigherDigits(long number, int digits) {
        return (int) (number / DIGITS_MARKER[digits - 1]);
    }
}
