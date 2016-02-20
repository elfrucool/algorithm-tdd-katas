package finddigits;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindDigitsTest {
    @Test
    public void testFind() {
        assertEquals(0, FindDigits.find("0"));
        assertEquals(1, FindDigits.find("1"));
        assertEquals(2, FindDigits.find("11"));
        assertEquals(1, FindDigits.find("21"));
        assertEquals(3, FindDigits.find("1012")); // sample in problem
        assertEquals(9, FindDigits.find("999999999")); // limit: big number
    }
}
