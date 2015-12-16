package mergesort;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MergeSortTest {

    @SuppressWarnings("unused")
    protected List<Object> parametersForTestMerge() {
        Object[] scenarios = {//
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
        return Arrays.asList(scenarios);
    }

    @Test
    @Parameters
    public void testMerge(int[] numbers, int[] expected) {
        merge(numbers, new int[numbers.length], 0, numbers.length / 2, numbers.length - 1);
        assertThat(numbers, is(expected));
    }

    private void merge(int[] numbers, int[] tmp, int left, int center, int right) {
        int leftCursor = left;
        int centerCursor = center;
        int tmpCursor = 0;

        while (leftCursor < center && centerCursor <= right) {
            if (numbers[leftCursor] < numbers[centerCursor])
                tmp[tmpCursor++] = numbers[leftCursor++];
            else
                tmp[tmpCursor++] = numbers[centerCursor++];
        }

        if (leftCursor == center)
            System.arraycopy(numbers, centerCursor, tmp, tmpCursor, right - centerCursor + 1);

        if (centerCursor > right)
            System.arraycopy(numbers, leftCursor, tmp, tmpCursor, center - leftCursor);

        System.arraycopy(tmp, 0, numbers, 0, right + 1 - left);
    }
}
