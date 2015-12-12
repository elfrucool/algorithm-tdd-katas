package zeroescomefirst;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ZeroesComeFirstTest {
    @Test
    public void sortWithNull() {
        assertThat(sort(null), is(new int[0]));
    }

    /**
     * Method used by JUnitParamsRunner to build the scenarios with their parameters.
     */
    @SuppressWarnings("unused")
    protected Object parametersForSortWithValues() {
        return new Object[]{//
                $(new int[0], new int[0]), //
                $(new int[]{0}, new int[]{0}), //
                $(new int[]{1, 0}, new int[]{0, 1}), //
                $(new int[]{0, 1}, new int[]{0, 1}), //
                $(new int[]{1, 1, 0}, new int[]{0, 1, 1}), //
                // keep order
                $(new int[]{2, 1, 0}, new int[]{0, 2, 1}), //
                $(new int[]{1, 2, 0}, new int[]{0, 1, 2}), //
                // suggested
                $(new int[]{3, 5, 0, 2, 1, 3, 0, 0}, new int[]{0, 0, 0, 3, 5, 2, 1, 3}), //
                // others
                $(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}), //
                $(new int[]{0, 0, 0, 1, 2, 4, 5, 7}, new int[]{0, 0, 0, 1, 2, 4, 5, 7}), //
                $(new int[]{1, 2, 4, 5, 7, 0, 0, 0}, new int[]{0, 0, 0, 1, 2, 4, 5, 7}), //
                $(new int[]{0, 1, 1, 1, 1, 1, 1, 0}, new int[]{0, 0, 1, 1, 1, 1, 1, 1}), //
        };
    }

    @Test
    @Parameters
    public void sortWithValues(int[] original, int[] expected) {
        int[] sorted = sort(original);
        assertThat(sorted, is(expected));
        assertThat(sorted, is(sameInstance(original)));
    }

    private int[] sort(int[] numbers) {
        if (numbers == null)
            return new int[0];
        int index = 0;
        while (numbers.length > index + 1) {
            int index1 = index;
            if (index1 >= 0 && nextIsZero(numbers, index1))
                swap(numbers, index1--);
            else
                index1++;
            index = index1;
        }
        return numbers;
    }

    private boolean nextIsZero(int[] numbers, int index) {
        return numbers[index + 1] == 0 && numbers[index] != 0;
    }

    private void swap(int[] numbers, int index) {
        int tmp = numbers[index];
        numbers[index] = numbers[index + 1];
        numbers[index + 1] = tmp;
    }
}
