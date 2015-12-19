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

@RunWith(JUnitParamsRunner.class)
public class ZeroesComeFirstTest {
    @SuppressWarnings("unused")
    protected Object parametersForTestZeroesComeFirst() {
        return new Object[]{//
                $(null, emptyList()), //
                $(singletonList(0), singletonList(0)), //
                $(singletonList(1), singletonList(1)), //
                $(emptyList(), emptyList()), //
                $(asList(0, 0), asList(0, 0)), //
                $(asList(0, 1), asList(0, 1)), //
                $(asList(1, 0), asList(0, 1)), //
                $(asList(1, -3), asList(1, -3)), //
                $(asList(0, 3, 0, 0, 2, 0, 0, 1, 0), asList(0, 0, 0 ,0, 0, 0, 3, 2, 1)), //
        };
    }

    @Test
    @Parameters
    public void testZeroesComeFirst(List<Integer> numbers, List<Object> expected) {
        assertThat(ZeroesComeFirst.sort(numbers), is(expected));
    }
}
