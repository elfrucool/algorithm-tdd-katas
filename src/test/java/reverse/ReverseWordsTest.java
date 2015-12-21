package reverse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static reverse.ReverseWords.reverse;

@RunWith(JUnitParamsRunner.class)
public class ReverseWordsTest {
    @SuppressWarnings("unused")
    protected Object parametersForTestReverseWordsInString() {
        return new Object[]{//
                $(null, ""), //
                $("x", "x"), //
                $("y", "y"), //
                $(" ", ""), //
                $("y ", "y"), //
                $("y x", "x y"), //
                $("y  x", "x y"), //
                $("z y x", "x y z"), //
                $("z y x", "x y z"), //
                $("ab  cd ef  gh  ij", "ij gh ef cd ab"), //
        };
    }

    @Test
    @Parameters
    public void testReverseWordsInString(String s, String expected) {
        assertThat(reverse(s), is(expected));
    }
}
