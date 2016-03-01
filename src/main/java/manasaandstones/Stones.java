package manasaandstones;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Collections.singleton;

public class Stones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        while (numberOfTestCases-- > 0)
            System.out.println(join(getLast(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())));
    }

    private static String join(Collection<Integer> numbers) {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public static Set<Integer> getLast(int n, int a, int b) {
        Set<Integer> lastStones = new TreeSet<>();

        if (n == 1)
            return singleton(0);

        int zeroBasedN = n - 1;
        for (int i = zeroBasedN; i >= 0; i--)
            lastStones.add(a * i + b * (zeroBasedN - i));

        return lastStones;
    }
}

