package mergesort;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MergeSortTest {
    @SuppressWarnings("unused")
    protected Object parametersForTestSort() {
        return new Object[]{//
                $(null, emptyList()), //
                $(singletonList(1), singletonList(1)), //
                $(emptyList(), emptyList()), //
                $(asList(2, 1), asList(1, 2)), //
                $(asList(1, 2), asList(1, 2)), //
                $(asList(2, 4, 1, 3), asList(1, 2, 3, 4)), //
                $(asList(3, 2, 1), asList(1, 2, 3)), //
                $(asList(4, 3, 1, 2), asList(1, 2, 3, 4)), //

                $(asList(1, 2, 3), asList(1, 2, 3)), //
                $(asList(3, 4, 1, 2), asList(1, 2, 3, 4)), //
                $(asList(3, 1, 2), asList(1, 2, 3)), //
                $(asList(4, 3, 2, 1, 9, 6, 7, 8, 8), asList(1, 2, 3, 4, 6, 7, 8, 8, 9)), //
                $(asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)), //
                $(asList(3, 2, 1, 1, 2, 3, 2, 3, 1), asList(1, 1, 1, 2, 2, 2, 3, 3, 3)), //
        };
    }

    @Test
    @Parameters
    public void testSort(List<Integer> numbers, List<Object> expected) {
        assertThat(MergeSort.sort(numbers), is(expected));
    }
}
