package subarraysum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SubarraySumTest {
    private void assertMax(SubArraySum subArraySum, long expected, int[] numbers) {
        for (int n : numbers)
            subArraySum.add(n);

        assertEquals(expected, subArraySum.max());
    }

    protected void assertMaxContiguous(long expected, int[] numbers) {
        assertMax(SubArraySum.contiguous(), expected, numbers);
    }

    protected void assertMaxNonContiguous(long expected, int[] numbers) {
        assertMax(SubArraySum.nonContiguous(), expected, numbers);
    }

    @Test
    public void testMaxContiguous() {
        assertMaxContiguous(0L, new int[0]);
        assertMaxContiguous(1L, new int[]{1});
        assertMaxContiguous(2L, new int[]{1,1});
        assertMaxContiguous(1L, new int[]{1, -1});
        assertMaxContiguous(1L, new int[]{1, -1, 1});
        assertMaxContiguous(4L, new int[]{2, -3, 4});
        assertMaxContiguous(-1L, new int[]{-1});
        assertMaxContiguous(-1L, new int[]{-4, -2, -1, -3});
        assertMaxContiguous(0L, new int[]{0, -1});
        assertMaxContiguous(0L, new int[]{-1, 0});
        assertMaxContiguous(9L, new int[]{2, 1, 0, -3, 4, 5, -1}); // full case I hope
    }

    @Test
    public void testMaxNonContiguous() {
        assertMaxNonContiguous(0L, new int[0]);
        assertMaxNonContiguous(1L, new int[]{1});
        assertMaxNonContiguous(2L, new int[]{1,1});
        assertMaxNonContiguous(2L, new int[]{1,-2, 1});
        assertMaxNonContiguous(-1L, new int[]{-1});
        assertMaxNonContiguous(0L, new int[]{-1, 0});
        assertMaxNonContiguous(10L, new int[]{-6, -5, -4, -3, -2, -1, 0, 1, 2, 3, -4, -10, 4}); // full case  I hope
    }

    @Test
    public void testBothTogether() {
        assertArrayEquals(new long[] {0L, 0L}, SubArraySum.maxSumBoth(new int[0]));
        assertArrayEquals(new long[] {10L, 10L}, SubArraySum.maxSumBoth(new int[]{1, 2, 3, 4}));
        assertArrayEquals(new long[] {10L, 11L}, SubArraySum.maxSumBoth(new int[]{2, -1, 2, 3, 4, -5}));
    }
}
