package sherlocksquares;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SherlockSquaresTest {
    @Test
    public void testSquares() {
        assertEquals(1, SherlockSquares.squares(1, 1)); // minimal case
        assertEquals(0, SherlockSquares.squares(2, 2)); // not matching
        assertEquals(1, SherlockSquares.squares(2, 4)); // matching by right
        assertEquals(1, SherlockSquares.squares(2, 5)); // matching by right
        assertEquals(2, SherlockSquares.squares(3, 9)); // example matching
        assertEquals(0, SherlockSquares.squares(17, 24)); // example not matching
        assertEquals(31622, SherlockSquares.squares(1, 999999999)); // big number
    }
}
