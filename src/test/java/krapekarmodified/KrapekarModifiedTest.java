package krapekarmodified;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KrapekarModifiedTest {
    public static final List<Integer> EXPECTED_FROM_BIG_RANGE = asList(
            1, 9, 45, 55, 99, 297, 703, 999, 2223, 2728, 4950, 5050, 7272,
            7777, 9999, 17344, 22222, 77778, 82656, 95121, 99999);

    public static final int MAX_NUMBER = 99_999;

    @Test(timeout = 200)
    public void getKrapekarModifiedNumbersInRange() {
        assertEquals(singletonList(1), KrapekarModified.getKrapekarModifiedNumbersInRange(1, 2)); // including 1
        assertEquals(emptyList(), KrapekarModified.getKrapekarModifiedNumbersInRange(2, 3)); // not including 1
        assertEquals(singletonList(9), KrapekarModified.getKrapekarModifiedNumbersInRange(9, 10)); // from has a kpn
        assertEquals(singletonList(9), KrapekarModified.getKrapekarModifiedNumbersInRange(8, 9)); // to has a kpn
        assertEquals(singletonList(9), KrapekarModified.getKrapekarModifiedNumbersInRange(8, 10)); // kpn inside range
        assertEquals(asList(1, 9, 45, 55, 99), KrapekarModified.getKrapekarModifiedNumbersInRange(1, 100)); // example
        assertEquals(EXPECTED_FROM_BIG_RANGE, KrapekarModified.getKrapekarModifiedNumbersInRange(1, MAX_NUMBER)); // big
    }

    @Test
    public void testIsKrapekarModified() {
        assertTrue(KrapekarModified.isKrapekarModified(1));
        assertFalse(KrapekarModified.isKrapekarModified(2));
        assertTrue(KrapekarModified.isKrapekarModified(9)); // from example
        assertFalse(KrapekarModified.isKrapekarModified(10)); // +1
        assertTrue(KrapekarModified.isKrapekarModified(45)); // from example
        assertFalse(KrapekarModified.isKrapekarModified(46)); // + 1
        assertTrue(KrapekarModified.isKrapekarModified(99)); // from example
        assertFalse(KrapekarModified.isKrapekarModified(100)); // + 1
        assertTrue(KrapekarModified.isKrapekarModified(99999)); // big Krapekar modified number
    }

    @Test
    public void testGetHigherDigits() {
        assertEquals(0, KrapekarModified.getHigherDigits(1L, 1));
        assertEquals(1, KrapekarModified.getHigherDigits(11L, 1));
        assertEquals(1, KrapekarModified.getHigherDigits(111L, 2));
        assertEquals(1, KrapekarModified.getHigherDigits(1111L, 3));
        assertEquals(1, KrapekarModified.getHigherDigits(11111L, 4));
        assertEquals(1, KrapekarModified.getHigherDigits(111111L, 5));
        assertEquals(99798, KrapekarModified.getHigherDigits(9979810201L, 5)); // big number
    }

    @Test
    public void testGetLowerDigits() {
        assertEquals(1, KrapekarModified.getLowerDigits(1L, 1));
        assertEquals(2, KrapekarModified.getLowerDigits(2L, 1));
        assertEquals(2, KrapekarModified.getLowerDigits(92L, 1));
        assertEquals(92, KrapekarModified.getLowerDigits(992L, 2));
        assertEquals(992, KrapekarModified.getLowerDigits(9992L, 3));
        assertEquals(9992, KrapekarModified.getLowerDigits(99992L, 4));
        assertEquals(99992, KrapekarModified.getLowerDigits(999992L, 5));
        assertEquals(10201, KrapekarModified.getLowerDigits(9979810201L, 5)); // big number
    }

    @Test
    public void testDigitsInNumber() {
        assertEquals(1, KrapekarModified.digitsInNumber(9));     // 1 digit
        assertEquals(3, KrapekarModified.digitsInNumber(999));   // 3 digits
        assertEquals(2, KrapekarModified.digitsInNumber(99));    // 2 digits
        assertEquals(5, KrapekarModified.digitsInNumber(99999)); // 5 digits
        assertEquals(4, KrapekarModified.digitsInNumber(9999));  // 4 digits
    }
}
