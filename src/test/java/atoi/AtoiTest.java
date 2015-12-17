package atoi;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AtoiTest {
    private static int atoi(String s) {
        if (s == null || s.trim().isEmpty())
            return 0;

        return atoiNonEmpty(s.trim());
    }

    private static int atoiNonEmpty(String numberAsString) {
        int signFactor = 1;
        int startIndex = 0;

        if (numberAsString.charAt(0) == '-') {
            signFactor = -1;
            startIndex++;
        }

        return makeNumber(numberAsString, startIndex) * signFactor;
    }

    private static int makeNumber(String numberAsString, int startIndex) {
        int number = 0;
        int index = startIndex;

        for (; index < numberAsString.length(); index++) {
            number = number * 10 + numberAsString.charAt(index) - '0';
        }

        return number;
    }

    /**
     * Method used by JUnitParamsRunner to build the scenarios with their parameters.
     */
    @SuppressWarnings("unused")
    protected Object parametersForTestAtoi() {
        return new Object[]{//
                $(null, 0), //
                $("", 0), //
                $(" ", 0), //
                $("0", 0), //
                $("1", 1), //
                $(" 1 ", 1), //
                $("-1", -1), //
                $("12", 12), //
                $(" -1239284", -1239284), //
        };
    }

    @Test
    @Parameters
    public void testAtoi(String s, int expected) {
        assertThat(atoi(s), is(expected));
    }

}
