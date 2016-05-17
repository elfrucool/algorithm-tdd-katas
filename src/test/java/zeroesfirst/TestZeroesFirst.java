package zeroesfirst;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static zeroesfirst.ZeroesFirst.zeroesFirst;

public class TestZeroesFirst {
    @Test
    public void testZeroesFirst() {
        assertZeroesFirst(new int[0], new int[0]);
        assertZeroesFirst(new int[]{1, 0}, new int[]{0, 1});
        assertZeroesFirst(new int[]{2, 0}, new int[]{0, 2});
        assertZeroesFirst(new int[]{1, 2}, new int[]{1, 2});
        assertZeroesFirst(new int[]{1, 0, 0}, new int[]{0, 0, 1});
        assertZeroesFirst(new int[]{1, 2, 0, 0}, new int[]{0, 0, 1, 2});
    }

    private void assertZeroesFirst(int[] numbers, int[] expected) {
        zeroesFirst(numbers);
        String message = String.format("%s should be %s", Arrays.toString(numbers), Arrays.toString(expected));
        assertThat(message, numbers, is(expected));
    }
}
