package coinchange;

import java.util.*;

import static java.util.Arrays.copyOfRange;

public class CoinChange {
    private static Map<String, Long> cached = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = scanner.nextInt();

        int[] coins = new int[scanner.nextInt()];
        for (int i = 0; i < coins.length; i++)
            coins[i] = scanner.nextInt();

        System.out.println(combinations(total, coins));
    }

    public static long combinations(int total, int[] coins) {
        Arrays.sort(coins);
        return cachedThenUnCached(total, coins);
    }

    private static long cachedThenUnCached(int total, int[] coins) {
        int current = coins[0];

        if (coins.length == 1)
            return total == 0 || total % current != 0 ? 0L : 1L;

        return cached.containsKey(key(coins, total)) ? cached.get(key(coins, total)) : unCached(total, coins, current);
    }

    private static long unCached(int total, int[] coins, int current) {
        long solutions = 0L;

        for (int factor = 0; factor * current + coins[1] <= total; factor++)
            solutions += cachedThenUnCached(total - factor * current, copyOfRange(coins, 1, coins.length));

        solutions += (total % current == 0 ? 1L : 0L);
        cached.put(key(coins, total), solutions);
        return solutions;
    }

    private static String key(int[] coins, int total) {
        return total + "|" + Arrays.toString(coins);
    }
}
