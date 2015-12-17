package reverse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ReverseWordsTest {
    private static String reverseWords(String original) {
        if (original == null)
            return "";

        String s = original.trim();
        int spaceIndex = s.indexOf(" ");

        if (spaceIndex > -1) {
            return reverseWords(s.substring(spaceIndex + 1)) + " " + s.substring(0, spaceIndex);
        }

        return s;
    }

    @SuppressWarnings("unused")
    protected Object parametersForReverseWordsInvertWordsOrderInString() {
        return new Object[]{//
                $(null, ""), //
                $("", ""), //
                $(" ", ""), //
                $("x", "x"), //
                $("x y", "y x"), //
                $("x  y", "y x"), //
                $("x y z", "z y x"), //
                $("x  y z", "z y x"), //
                $("x y  z", "z y x"), //
                $(" ab  cd  ef  gh ", "gh ef cd ab"), //
        };
    }

    @Test
    @Parameters
    public void reverseWordsInvertWordsOrderInString(String original, String expected) {
        assertThat(reverseWords(original), is(expected));
    }
}
