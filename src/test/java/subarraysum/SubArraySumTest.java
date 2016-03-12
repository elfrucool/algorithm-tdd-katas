package subarraysum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubArraySumTest {
    protected void assertSolution(int[] numbers, long expectedContiguous, long expectedNonContiguous) {
        SumArraySum.Solution solution = SumArraySum.solve(numbers);
        assertEquals("bad contiguous", expectedContiguous, solution.contiguous);
        assertEquals("bad non contiguous", expectedNonContiguous, solution.nonContiguous);
    }

    @Test
    public void testSolve() {
        assertSolution(new int[0], 0L, 0L);
        assertSolution(new int[]{1}, 1L, 1L);
        assertSolution(new int[]{0}, 0L, 0L);
        assertSolution(new int[]{1, 2}, 3L, 3L);
        assertSolution(new int[]{1, -1, 2}, 2L, 3L);
        assertSolution(new int[]{1, -2, 2}, 2L, 3L);
        assertSolution(new int[]{2, -3, 1}, 2L, 3L);
        assertSolution(new int[]{-1}, -1L, -1L);
        assertSolution(new int[]{-2, -1}, -1L, -1L);
        assertSolution(new int[]{-1, 1}, 1L, 1L);
    }
}
