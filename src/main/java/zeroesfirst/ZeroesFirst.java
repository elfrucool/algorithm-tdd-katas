package zeroesfirst;

public class ZeroesFirst {
    public static void sort(int[] numbers) {
        int dest = numbers.length - 1;
        int src = dest;

        while (src >= 0) {
            if (numbers[src] == 0)
                src--;
            else
                numbers[dest--] = numbers[src--];
        }

        while (dest >= 0)
            numbers[dest--] = 0;
    }
}
