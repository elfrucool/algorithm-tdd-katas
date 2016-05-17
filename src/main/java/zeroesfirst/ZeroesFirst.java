package zeroesfirst;

import java.util.Arrays;

public class ZeroesFirst {
    static void zeroesFirst(int[] numbers) {
        int dest = numbers.length - 1;
        int src = dest;

        while (src >= 0) {
            if (numbers[src] == 0)
                src--;
            else
                numbers[dest--] = numbers[src--];
        }

        if (dest != src)
            Arrays.fill(numbers, 0, dest + 1, 0);
    }
}
