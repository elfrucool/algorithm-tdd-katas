package plusminus;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class PlusMinusTest {
    protected void assertPlusMinus(List<Integer> numbers, double[] expected) {
        assertArrayEquals(expected, PlusMinus.calculate(numbers.iterator()), 0.000001);
    }

    @Test
    public void testCalculate() {
        assertPlusMinus(Collections.emptyList(), new double[]{0.0, 0.0, 0.0}); // empty sequence
        assertPlusMinus(Collections.singletonList(1), new double[]{1.0, 0.0, 0.0}); // positive
        assertPlusMinus(Collections.singletonList(-1), new double[]{0.0, 1.0, 0.0}); // negative
        assertPlusMinus(Collections.singletonList(0), new double[]{0.0, 0.0, 1.0}); // zero
        assertPlusMinus(Arrays.asList(1, -1), new double[]{0.5, 0.5, 0.0}); // positive and negative
        assertPlusMinus(Arrays.asList(1, 0), new double[]{0.5, 0.0, 0.5}); // positive and zero
        assertPlusMinus(Arrays.asList(-4, 3, -9, 0, 4, 1), new double[]{0.5, 0.333333, 0.166667}); // original example
    }
}
