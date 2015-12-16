package zeroescomefirst;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ZeroesComeFirstTest {
    private static int[] sort(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return new int[0];

        int[] sorted = new int[numbers.length];

        int sortedIndex = numbers.length;
        int numbersIndex = numbers.length;

        while (numbersIndex-- > 0)
            if (numbers[numbersIndex] != 0)
                sorted[--sortedIndex] = numbers[numbersIndex];

        return sorted;
    }

    @Test
    public void whenNullReturnsEmptyArray() {
        assertThat(sort(null), is(new int[0]));
    }

    @SuppressWarnings("unused")
    protected Object parametersForWhenNotNullReturnsSortedArray() {
        return new Object[]{//
                $(new int[0], new int[0]), //
                $(new int[]{0}, new int[]{0}), //
                $(new int[]{1}, new int[]{1}), //
                $(new int[]{1, 0}, new int[]{0, 1}), //
                $(new int[]{0, 1}, new int[]{0, 1}), //
                $(new int[]{2, 0, 1}, new int[]{0, 2, 1}), //
                $(new int[]{3, 5, 0, 2, 1, 3, 0, 0}, new int[]{0, 0, 0, 3, 5, 2, 1, 3}), //
        };
    }

    @Test
    @Parameters
    public void whenNotNullReturnsSortedArray(int[] numbers, int[] expected) {
        assertThat(sort(numbers), is(expected));
    }
}
