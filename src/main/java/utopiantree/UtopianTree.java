package utopiantree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtopianTree {
    public static final int INITIAL_SIZE = 1;
    public static final int SUMMER_INCREMENT = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        List<Integer> results = new ArrayList<>(numberOfTestCases);

        while (numberOfTestCases-- > 0)
            results.add(grow(scanner.nextInt()));

        results.forEach(System.out::println);
    }

    public static int grow(int cycles) {
        int height = INITIAL_SIZE;

        for (int currentCycle = 0; currentCycle < cycles; currentCycle++)
            height += (currentCycle % 2 == 0) ? height : SUMMER_INCREMENT;

        return height;
    }
}
