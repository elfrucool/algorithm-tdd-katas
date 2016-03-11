package subarraysum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SubArraySumTest {
    @Test
    public void testMax() {
        assertArrayEquals(new long[]{0L, 0L}, SubArraySum.maxContiguousAndNonContiguous(new int[0]));
        assertArrayEquals(new long[]{1L, 1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{1}));
        assertArrayEquals(new long[]{3L, 3L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{1, 2}));
        assertArrayEquals(new long[]{2L, 3L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{1, -1, 2}));
        assertArrayEquals(new long[]{2L, 3L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{1, -2, 2}));
        assertArrayEquals(new long[]{1L, 1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{1, -1}));
        assertArrayEquals(new long[]{-1L, -1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{-1}));
        assertArrayEquals(new long[]{-1L, -1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{-2, -1}));
        assertArrayEquals(new long[]{-1L, -1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{-2, -1, -3}));
        assertArrayEquals(new long[]{0L, 0L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{-1, 0}));
        assertArrayEquals(new long[]{1L, 1L}, SubArraySum.maxContiguousAndNonContiguous(new int[]{-1, 1}));
    }
}
