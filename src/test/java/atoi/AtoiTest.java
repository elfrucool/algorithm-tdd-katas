package atoi;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AtoiTest {
    private static int addDigit(int number, char c) {
        return 10 * number + (c - '0');
    }

    private static int initialIndexValue(int sign) {
        return sign > 0 ? 0 : 1;
    }

    private static int getSign(String s) {
        return s.charAt(0) == '-' ? -1 : 1;
    }

    @Test
    public void atoiTest() {
        assertThat(atoi(null), is(0));
        assertThat(atoi(""), is(0));
        assertThat(atoi("0"), is(0));
        assertThat(atoi("1"), is(1));
        assertThat(atoi("15"), is(15));
        assertThat(atoi("-1"), is(-1));
    }

    private int atoi(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int sign = getSign(s);
        return getNumber(s, sign) * sign;
    }

    private int getNumber(String s, int sign) {
        int number = 0;
        for (int index = initialIndexValue(sign); index < s.length(); index++) {
            char c = s.charAt(index);
            number = addDigit(number, c);
        }
        return number;
    }
}
