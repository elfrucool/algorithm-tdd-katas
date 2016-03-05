package taumandbirthday;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DikshaGiftsTest {
    @Test
    public void testCalculate() {
        assertEquals(0, DikshaGifts.calculate(0, 0, 0, 0, 0));  // empty case
        assertEquals(1, DikshaGifts.calculate(1, 0, 1, 0, 10)); // single b item at 1
        assertEquals(2, DikshaGifts.calculate(1, 0, 2, 0, 10)); // single b item at 2
        assertEquals(2, DikshaGifts.calculate(2, 0, 1, 0, 10)); // two b items at 1
        assertEquals(1, DikshaGifts.calculate(0, 1, 0, 1, 10)); // single w item at 1
        assertEquals(2, DikshaGifts.calculate(0, 1, 0, 2, 10)); // single w item at 2
        assertEquals(0, DikshaGifts.calculate(0, 1, 0, 1, 0));  // single w item interchanged by x = z = 0
        assertEquals(0, DikshaGifts.calculate(1, 0, 1, 0, 0));  // single b item interchanged by y = z = 0
        assertEquals(2, DikshaGifts.calculate(0, 1, 1, 3, 1));  // single w item interchanged by x = z = 1
        assertEquals(2, DikshaGifts.calculate(1, 0, 3, 1, 1));  // single b item interchanged by y = z = 1
        assertEquals(3, DikshaGifts.calculate(0, 1, 2, 3, 2));  // z < y but y < x + z  (just to verify)
        assertEquals(20, DikshaGifts.calculate(10, 10, 1, 1, 1)); // example 1
        assertEquals(37, DikshaGifts.calculate(5, 9, 2, 3, 4)); // example 2
        assertEquals(12, DikshaGifts.calculate(3, 6, 9, 1, 1)); // example 3
        assertEquals(35, DikshaGifts.calculate(7, 7, 4, 2, 1)); // example 4
        assertEquals(12, DikshaGifts.calculate(3, 3, 1, 9, 2)); // example 5
        assertEquals(1000000000L * 1000000000L * 2L,
                DikshaGifts.calculate(1000000000, 1000000000, 1000000000, 1000000000, 1000000000)); // biggest case
    }
}
