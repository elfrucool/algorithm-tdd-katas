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
    protected Object parametersForShouldReturnNewListWithSortedNumbers() {
        return new Object[]{//
                $(null, emptyList()), //
                $(singletonList(1), singletonList(1)), //
                $(emptyList(), emptyList()), //
                $(singletonList(0), singletonList(0)), //
                $(asList(1, 0), asList(0, 1)), //
                $(asList(2, 1), asList(1, 2)), //
                $(asList(1, 2), asList(1, 2)), //
                $(asList(0, 1, 2, 3), asList(0, 1, 2, 3)), //
                $(asList(2, 1, 0), asList(0, 1, 2)), //
                $(asList(3, 2, 1, 0), asList(0, 1, 2, 3)), //
        };
    }

    @Test
    @Parameters
    public void shouldReturnNewListWithSortedNumbers(List<Integer> numbers, List<Object> expected) {
        assertThat(MergeSort.sort(numbers), is(expected));
    }
}
