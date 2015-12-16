package mergesort;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MergeSortTest {
    /**
     * Method used by JUnitParamsRunner to build the scenarios with their parameters.
     */
    @SuppressWarnings("unused")
    protected Object parametersForTestMergeSort() {
        return new Object[]{//
                $(new int[0], new int[0]), //

                $(new int[]{1, 0}, new int[]{0, 1}), //

                $(new int[]{1, 2, 0}, new int[]{0, 1, 2}), //
                $(new int[]{2, 0, 1}, new int[]{0, 1, 2}), //

                $(new int[]{3, 2, 1, 0}, new int[]{0, 1, 2, 3}), //
                $(new int[]{4, 3, 2, 1, 0}, new int[]{0, 1, 2, 3, 4}) //
        };
    }

    @Test
    @Parameters
    public void testMergeSort(int[] numbers, int[] expected) {
        MergeSort.mergeSort(numbers);
        assertThat(numbers, is(expected));
    }

    @SuppressWarnings("unused")
    protected Object parametersForTestMerge() {
        return new Object[]{//
                $(new int[0], new int[0]), //

                $(new int[]{0}, new int[]{0}), //

                $(new int[]{0, 1}, new int[]{0, 1}), //
                $(new int[]{1, 0}, new int[]{0, 1}), //

                $(new int[]{0, 1, 2}, new int[]{0, 1, 2}), //
                $(new int[]{1, 0, 2}, new int[]{0, 1, 2}), //
                $(new int[]{2, 0, 1}, new int[]{0, 1, 2}), //

                $(new int[]{1, 4, 2, 3}, new int[]{1, 2, 3, 4}), //
                $(new int[]{1, 3, 2, 4}, new int[]{1, 2, 3, 4}), //
                $(new int[]{3, 4, 1, 2}, new int[]{1, 2, 3, 4}), //

                $(new int[]{1, 5, 2, 3, 4}, new int[]{1, 2, 3, 4, 5}), //
        };
    }

    @Test
    @Parameters
    public void testMerge(int[] numbers, int[] expected) {
        MergeSort.merge(numbers, new int[numbers.length], 0, numbers.length / 2, numbers.length);
        assertThat(numbers, is(expected));
    }

}
