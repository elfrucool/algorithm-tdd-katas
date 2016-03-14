package lis;

// longest increasing subsequence
// [2, 5, 3, 7, 101, 18] -> |[2, 3, 7, 101]| -> 4

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class LisTest {
    @SuppressWarnings("unused") // used by JUnitParams runner
    public Object parametersForTestLis() {
        return new Object[]{ //
                $(0, new int[0]), // empty array
                $(1, new int[]{1}), // single element array
                $(1, new int[]{1, 0}), // sequence of 1
                $(2, new int[]{0, 1}), // sequence of 2
                $(3, new int[]{0, 1, 2}), // sequence of 3
                $(2, new int[]{0, 1, 0, 1}), // breaking sequence
                $(3, new int[]{5, 1, 2, 3}), // two sequences
                $(3, new int[]{5, 1, 6, 2, 7}), // interleaving sequences
                $(4, new int[]{2, 5, 3, 7, 101, 18}), // original scenario
        };
    }

    @Test
    @Parameters
    public void testLis(int expected, int[] numbers) {
        assertEquals(expected, Lis.calculate(numbers));
    }
}
