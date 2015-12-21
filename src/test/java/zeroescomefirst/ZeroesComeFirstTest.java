package zeroescomefirst;

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
import static zeroescomefirst.ZeroesComeFirst.sort;

@RunWith(JUnitParamsRunner.class)
public class ZeroesComeFirstTest {
    @SuppressWarnings("unused")
    protected Object parametersForSortPutsZeroesFirst() {
        return new Object[]{//
                $(null, emptyList()), //
                $(singletonList(1), singletonList(1)), //
                $(singletonList(2), singletonList(2)), //
                $(emptyList(), emptyList()), //
                $(asList(0, 0), asList(0, 0)), //
                $(asList(0, 1), asList(0, 1)), //
                $(asList(1, 0), asList(0, 1)), //
                $(asList(2, 1, 0), asList(0, 2, 1)), //
                $(asList(2, 0, 1, 0, 0, 1, 2, 2, 3, 0), asList(0, 0, 0, 0, 2, 1, 1, 2, 2, 3)), //
        };
    }

    @Test
    @Parameters
    public void sortPutsZeroesFirst(List<Integer> numbers, List<Object> expected) {
        assertThat(sort(numbers), is(expected));
    }
}
