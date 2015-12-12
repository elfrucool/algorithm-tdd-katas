package zeroscomefirst;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ZerosComeFirstTest {

    private static int[] sort(int[] numbers) {
        if (numbers == null)
            return new int[0];
        int index = 0;
        while (index + 1 < numbers.length) {
            if (shouldMove(numbers, index)) {
                swapWithNext(numbers, index--);
            } else {
                index++;
            }
        }
        return numbers;
    }

    private static boolean shouldMove(int[] numbers, int index) {
        return index >= 0 && numbers[index] != 0 && numbers[index + 1] == 0;
    }

    // ugly if
    // swap can be extracted

    private static void swapWithNext(int[] numbers, int index) {
        int tmp = numbers[index];
        numbers[index] = numbers[index + 1];
        numbers[index + 1] = tmp;
    }

    @Test
    public void testSortNull() {
        assertThat(sort(null), is(new int[0]));
    }

    /**
     * Method used by JUnitParamsRunner to build the scenarios with their parameters.
     */
    @SuppressWarnings("unused")
    protected Object parametersForTestSort() {
        return new Object[]{//
                $(new int[0], new int[0]), //
                $(new int[]{0}, new int[]{0}), //
                $(new int[]{1}, new int[]{1}), //
                $(new int[]{0, 0}, new int[]{0, 0}), //
                $(new int[]{0, 1}, new int[]{0, 1}), //
                $(new int[]{1, 0}, new int[]{0, 1}), //
                $(new int[]{1, 1}, new int[]{1, 1}), //
                $(new int[]{1, 0, 1}, new int[]{0, 1, 1}), //
                $(new int[]{1, 1, 0}, new int[]{0, 1, 1}), //
                $(new int[]{2, 1, 0}, new int[]{0, 2, 1}), //
                $(new int[]{1, 2, 0}, new int[]{0, 1, 2}), //
                $(new int[]{3, 5, 0, 2, 1, 3, 0, 0}, new int[]{0, 0, 0, 3, 5, 2, 1, 3}), //
                $(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), //
                $(new int[]{0, 0, 0, 1, 2, 4, 5, 7}, new int[]{0, 0, 0, 1, 2, 4, 5, 7}), //
                $(new int[]{1, 2, 4, 5, 7, 0, 0, 0}, new int[]{0, 0, 0, 1, 2, 4, 5, 7}), //
                $(new int[]{0, 1, 1, 1, 1, 1, 1, 0}, new int[]{0, 0, 1, 1, 1, 1, 1, 1}), //
        };
    }

    @Test
    @Parameters
    public void testSort(int[] numbers, int[] expected) {
        int[] sorted = sort(numbers);
        assertThat(sorted, is(expected));
        assertThat(sorted, is(sameInstance(numbers)));
    }
}
