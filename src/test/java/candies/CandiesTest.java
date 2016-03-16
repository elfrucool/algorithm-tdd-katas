package candies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CandiesTest {
    @Test
    public void testDistribute() {
        assertEquals(0L, Candies.distribute(new int[0])); // minimal
        assertEquals(1L, Candies.distribute(new int[]{1})); // one child
        assertEquals(3L, Candies.distribute(new int[]{1, 2})); // two: the second has greater rating
        assertEquals(2L, Candies.distribute(new int[]{1, 1})); // two: equal
        assertEquals(3L, Candies.distribute(new int[]{2, 1})); // two: the second has smaller rating
        assertEquals(6L, Candies.distribute(new int[]{1, 2, 3})); // three: ascending order
        assertEquals(6L, Candies.distribute(new int[]{3, 2, 1})); // three: descending order
        assertEquals(4L, Candies.distribute(new int[]{1, 2, 2})); // example
        assertEquals(7L, Candies.distribute(new int[]{3, 3, 2, 1})); // discussion example 1: 1 + 2 + 3 + 1 = 7
        assertEquals(7L, Candies.distribute(new int[]{1, 2, 3, 3})); // inverted discussion example 1: 1 + 2 + 3 + 1 = 7
        assertEquals(15L, Candies.distribute(new int[]{5, 4, 3, 2, 1})); // discussion example 2
        assertEquals((100000L * 100001L)/2, Candies.distribute(allRangeAscending()));
        assertEquals((100000L * 100001L)/2, Candies.distribute(allRangeDescending()));
    }

    private int[] allRangeAscending() {
        int[] children = new int[100000];
        for (int i = 0; i < children.length; i++)
            children[i] = i + 1;
        return children;
    }

    private int[] allRangeDescending() {
        int[] children = new int[100000];
        for (int i = children.length; i > 0; i--)
            children[i - 1] = i;
        return children;
    }
}
