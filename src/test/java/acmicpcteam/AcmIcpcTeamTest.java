package acmicpcteam;

import org.junit.Test;

import java.util.BitSet;

import static acmicpcteam.AcmIcpcTeam.*;
import static acmicpcteam.AcmIcpcTeam.BitObject.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AcmIcpcTeamTest {
    public static final String BIG_BITS = "111101111101111101111101111101111101111101111101111101100000000001110110";

    @Test
    public void testCalculate() {
        assertEquals(results(0, 1), calculate(asList(fromString("0"), fromString("0"))));
        assertEquals(results(1, 1), calculate(asList(fromString("1"), fromString("0"))));
        assertEquals(results(1, 2), calculate(asList(fromString("0"), fromString("0"), fromString("1"))));
        assertEquals(results(2, 1), calculate(asList(fromString("00"), fromString("10"), fromString("01"))));
        assertEquals(results(5, 2), calculate(asList(//
                fromString("10101"), //
                fromString("11100"), //
                fromString("11010"), //
                fromString("00101"))));
    }

    @Test
    public void testTopicsOfTeam() {
        assertEquals(0, topicsOfTeam(fromString(""), fromString("")));
        assertEquals(1, topicsOfTeam(fromString("1"), fromString("0")));
        assertEquals(1, topicsOfTeam(fromString("0"), fromString("1")));
        assertEquals(2, topicsOfTeam(fromString("10"), fromString("01")));
        assertEquals(1, topicsOfTeam(fromString("10"), fromString("10")));
        assertEquals(5, topicsOfTeam(fromString("11011"), fromString("10101")));
    }

    @Test
    public void testBitObjectToString() {
        assertEquals("", bitObject(0, BitSet.valueOf(new byte[0])).toString());
        assertEquals("1", bitObject(1, BitSet.valueOf(new byte[]{0b1})).toString());
        assertEquals("0", bitObject(1, BitSet.valueOf(new byte[]{0b0})).toString());
        assertEquals("01", bitObject(2, BitSet.valueOf(new byte[]{0b10})).toString());
        assertEquals("10", bitObject(2, BitSet.valueOf(new byte[]{0b01})).toString());
        assertEquals("11101", bitObject(5, BitSet.valueOf(new byte[]{0b10111})).toString());
        assertEquals("1110110", bitObject(7, BitSet.valueOf(new byte[]{0b0110111})).toString());
    }

    @Test
    public void testBitObjectFromString() {
        assertEquals("", fromString("").toString());
        assertEquals("1", fromString("1").toString());
        assertEquals("0", fromString("0").toString());
        assertEquals("11", fromString("11").toString());
        assertEquals("1110110", fromString("1110110").toString());
        assertEquals(BIG_BITS, fromString(BIG_BITS).toString());
    }
}
