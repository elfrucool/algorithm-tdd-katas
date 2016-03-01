package manasaandstones;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Stones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        while (numberOfTestCases-- > 0)
            System.out.println(join(getLast(scanner.nextInt() - 1, scanner.nextInt(), scanner.nextInt())));
    }

    private static String join(Collection<Integer> numbers) {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public static Set<Integer> getLast(int n, int a, int b) {
        Set<Integer> lastStones = new TreeSet<>();

        for (int i = n; i >= 0; i--)
            lastStones.add(a * i + b * (n - i));

        return lastStones;
    }
}
