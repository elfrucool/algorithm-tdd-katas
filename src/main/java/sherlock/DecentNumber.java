package sherlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DecentNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalOfCases = scanner.nextInt();
        List<String> numbers = new ArrayList<>(totalOfCases);

        while (totalOfCases-- > 0)
            numbers.add(get(scanner.nextInt()));

        for (String decentNumber : numbers)
            System.out.println(decentNumber);
    }

    public static String get(int n) {
        return get(n, 0);
    }

    protected static String get(int numberOfFives, int numberOfThrees) {
        if (numberOfFives + numberOfThrees < 3 || numberOfFives < 0)
            return "-1";
        if (numberOfFives % 3 == 0 && numberOfThrees % 5 == 0)
            return repeatString('5', numberOfFives) + repeatString('3', numberOfThrees);
        return get(numberOfFives - 5, numberOfThrees + 5);
    }

    private static String repeatString(char c, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, c);
        return new String(chars);
    }
}
