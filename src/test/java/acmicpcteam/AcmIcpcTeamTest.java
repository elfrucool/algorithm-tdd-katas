package acmicpcteam;

import org.junit.Test;

import java.util.BitSet;

import static acmicpcteam.AcmIcpcTeam.bitObject;
import static org.junit.Assert.assertEquals;

public class AcmIcpcTeamTest {
    public static final String BIG_BITS = "11101111101111101111101111101111101111101111101111101111101100000000001110110";

    @Test
    public void testBitsToString() {
        assertEquals("", AcmIcpcTeam.bitsToString(bitObject(0, BitSet.valueOf(new byte[0]))));
        assertEquals("1", AcmIcpcTeam.bitsToString(bitObject(1, BitSet.valueOf(new byte[]{0b1}))));
        assertEquals("0", AcmIcpcTeam.bitsToString(bitObject(1, BitSet.valueOf(new byte[]{0b0}))));
        assertEquals("01", AcmIcpcTeam.bitsToString(bitObject(2, BitSet.valueOf(new byte[]{0b10}))));
        assertEquals("10", AcmIcpcTeam.bitsToString(bitObject(2, BitSet.valueOf(new byte[]{0b01}))));
        assertEquals("11101", AcmIcpcTeam.bitsToString(bitObject(5, BitSet.valueOf(new byte[]{0b10111}))));
        assertEquals("1110110", AcmIcpcTeam.bitsToString(bitObject(7, BitSet.valueOf(new byte[]{0b0110111}))));
    }

    @Test
    public void testStringToBits() {
        assertEquals("", AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits("")));
        assertEquals("1", AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits("1")));
        assertEquals("0", AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits("0")));
        assertEquals("11", AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits("11")));
        assertEquals("1110110", AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits("1110110")));
        assertEquals(BIG_BITS, AcmIcpcTeam.bitsToString(AcmIcpcTeam.stringToBits(BIG_BITS)));
    }
}
