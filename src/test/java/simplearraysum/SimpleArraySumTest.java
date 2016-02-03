package simplearraysum;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SimpleArraySumTest {
    @Test
    public void testSum() {
        assertEquals(0, SimpleArraySum.calculate(new int[0])); // default to constant
        assertEquals(3, SimpleArraySum.calculate(new int[]{3})); // constant to scalar
        assertEquals(5, SimpleArraySum.calculate(new int[]{2, 3})); // scalar to iteration
        assertEquals(10, SimpleArraySum.calculate(new int[]{1, 2, 3, 4})); // just to show it is complete
    }

    @Test
    public void testLineToNumbers() {
        assertArrayEquals(new int[0], SimpleArraySum.lineToNumbers("")); // null to constant
        assertArrayEquals(new int[]{2}, SimpleArraySum.lineToNumbers("2")); // constant to if/constant
        assertArrayEquals(new int[]{3}, SimpleArraySum.lineToNumbers("3")); // constant to scalar
        assertArrayEquals(new int[]{2, 3}, SimpleArraySum.lineToNumbers("2 3")); // scalar to array
    }
}
