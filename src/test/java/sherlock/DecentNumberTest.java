package sherlock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecentNumberTest {
    @Test
    public void testGetMaxDecentNumber() {
        assertEquals("-1", DecentNumber.get(1));
        assertEquals("555", DecentNumber.get(3));
        assertEquals("-1", DecentNumber.get(4));
        assertEquals("33333", DecentNumber.get(5));
        assertEquals("555555", DecentNumber.get(6));
        assertEquals("3333333333", DecentNumber.get(10));
        assertEquals("55533333", DecentNumber.get(8)); // complete
        assertEquals("55555533333", DecentNumber.get(11));
        assertEquals("5553333333333", DecentNumber.get(13));
        assertEquals("5555553333333333", DecentNumber.get(16));
    }
}
