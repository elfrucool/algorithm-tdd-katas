package acmicpcteam;

import org.junit.Test;

import java.util.BitSet;

import static acmicpcteam.AcmIcpcTeam.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AcmIcpcTeamTest {
    public static final String BIG_BITS = "111101111101111101111101111101111101111101111101111101100000000001110110";

    @Test
    public void testCalculate() {
        assertEquals(results(0, 1), calculate(asList(stringToBits("0"), stringToBits("0"))));
        assertEquals(results(1, 1), calculate(asList(stringToBits("1"), stringToBits("0"))));
        assertEquals(results(1, 2), calculate(asList(stringToBits("0"), stringToBits("0"), stringToBits("1"))));
    }

    @Test
    public void testTopicsOfTeam() {
        assertEquals(0, topicsOfTeam(stringToBits(""), stringToBits("")));
        assertEquals(1, topicsOfTeam(stringToBits("1"), stringToBits("0")));
        assertEquals(1, topicsOfTeam(stringToBits("0"), stringToBits("1")));
        assertEquals(2, topicsOfTeam(stringToBits("10"), stringToBits("01")));
        assertEquals(1, topicsOfTeam(stringToBits("10"), stringToBits("10")));
        assertEquals(5, topicsOfTeam(stringToBits("11011"), stringToBits("10101")));
    }

    @Test
    public void testBitsToString() {
        assertEquals("", bitsToString(bitObject(0, BitSet.valueOf(new byte[0]))));
        assertEquals("1", bitsToString(bitObject(1, BitSet.valueOf(new byte[]{0b1}))));
        assertEquals("0", bitsToString(bitObject(1, BitSet.valueOf(new byte[]{0b0}))));
        assertEquals("01", bitsToString(bitObject(2, BitSet.valueOf(new byte[]{0b10}))));
        assertEquals("10", bitsToString(bitObject(2, BitSet.valueOf(new byte[]{0b01}))));
        assertEquals("11101", bitsToString(bitObject(5, BitSet.valueOf(new byte[]{0b10111}))));
        assertEquals("1110110", bitsToString(bitObject(7, BitSet.valueOf(new byte[]{0b0110111}))));
    }

    @Test
    public void testStringToBits() {
        assertEquals("", bitsToString(AcmIcpcTeam.stringToBits("")));
        assertEquals("1", bitsToString(AcmIcpcTeam.stringToBits("1")));
        assertEquals("0", bitsToString(AcmIcpcTeam.stringToBits("0")));
        assertEquals("11", bitsToString(AcmIcpcTeam.stringToBits("11")));
        assertEquals("1110110", bitsToString(AcmIcpcTeam.stringToBits("1110110")));
        assertEquals(BIG_BITS, bitsToString(AcmIcpcTeam.stringToBits(BIG_BITS)));
    }
}
