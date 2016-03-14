package coinchange;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinChangeTest {

    public static final int MAX_COIN_RANGE = 50;
    public static final int MAX_TOTAL = 250;

    @Test
    public void testCombinations() {
        assertEquals(1, CoinChange.combinations(1, new int[]{1})); // minimal
        assertEquals(0, CoinChange.combinations(1, new int[]{2})); // minimal not match, smaller
        assertEquals(0, CoinChange.combinations(3, new int[]{2})); // not match, remaining
        assertEquals(0, CoinChange.combinations(0, new int[]{1})); // not match, total is zero
        assertEquals(2, CoinChange.combinations(3, new int[]{1, 2})); // two coins combined work
        assertEquals(2, CoinChange.combinations(3, new int[]{2, 1})); // unsorted
        assertEquals(1, CoinChange.combinations(3, new int[]{1, 4})); // two coins only first work
        assertEquals(0, CoinChange.combinations(3, new int[]{2, 4})); // two coins no one work
        assertEquals(2, CoinChange.combinations(2, new int[]{1, 2})); // two coins, separate work w first x 1
        assertEquals(2, CoinChange.combinations(5, new int[]{1, 3})); // two coins, separate work w first x 2
        assertEquals(2, CoinChange.combinations(6, new int[]{2, 3})); // two coins, first is not 1
        assertEquals(3, CoinChange.combinations(3, new int[]{1, 2, 3})); // three coins
        assertEquals(4, CoinChange.combinations(4, new int[]{1, 2, 3})); // example 1
        assertEquals(5, CoinChange.combinations(10, new int[]{2, 3, 5, 6})); // example 2
    }

    @Test(timeout = 400L)
    public void testBigOne() {
        int[] allCoins = new int[MAX_COIN_RANGE];
        for (int i = 0; i < MAX_COIN_RANGE; i++)
            allCoins[i] = i + 1;
        CoinChange.combinations(MAX_TOTAL, allCoins);
    }

    @Test(timeout = 300L)
    public void testBigTwo() {
        int[] coins = {41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11};
        assertEquals(15685693751L, CoinChange.combinations(MAX_TOTAL, coins));
    }
}
