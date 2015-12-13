package reverse;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReverseWordsTest {
    private static String reverseWords(String original) {
        if (original == null)
            return "";
        return reverseWordsNotNull(original.trim());
    }

    private static String reverseWordsNotNull(String s) {
        int indexOfSpace = s.indexOf(" ");
        if (indexOfSpace > -1) {
            return reverseWords(s.substring(indexOfSpace + 1)) + " " + s.substring(0, indexOfSpace);
        }
        return s;
    }

    @Test
    public void canReverseWords() {
        assertThat(reverseWords(null), is(""));
        assertThat(reverseWords(""), is(""));
        assertThat(reverseWords("x"), is("x"));
        assertThat(reverseWords("x "), is("x"));
        assertThat(reverseWords("x y"), is("y x"));
        assertThat(reverseWords("x y z"), is("z y x"));
        assertThat(reverseWords("hello world"), is("world hello"));
        assertThat(reverseWords("anita lava la tina"), is("tina la lava anita"));
    }
}
