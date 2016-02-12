package timeconversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeConversionTest {
    @Test
    public void testConvert() {
        assertEquals("10:00:00", TimeConversion.convert("10:00:00AM"));
        assertEquals("09:45:23", TimeConversion.convert("09:45:23AM"));
        assertEquals("19:45:23", TimeConversion.convert("07:45:23PM"));
        assertEquals("19:45:23", TimeConversion.convert("07:45:23PM"));
        assertEquals("12:45:23", TimeConversion.convert("12:45:23PM"));
        assertEquals("00:45:23", TimeConversion.convert("12:45:23AM"));
    }
}
