package caesarcipher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarTest {
    @Test
    public void testEncrypt() {
        assertEquals("a", Caesar.encrypt("a", 0)); // minimal
        assertEquals("b", Caesar.encrypt("b", 0)); // with other letter
        assertEquals("b", Caesar.encrypt("a", 1)); // single transformation
        assertEquals("-", Caesar.encrypt("-", 1)); // not a letter
        assertEquals("B", Caesar.encrypt("A", 1)); // upper case
        assertEquals("a", Caesar.encrypt("z", 1)); // rotation
        assertEquals("A", Caesar.encrypt("Z", 1)); // rotation upper case
        assertEquals("bc", Caesar.encrypt("ab", 1)); // more than one character
        assertEquals("okffng-Qwvb", Caesar.encrypt("middle-Outz", 2)); // example
    }
}
