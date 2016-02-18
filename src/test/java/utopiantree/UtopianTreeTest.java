package utopiantree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtopianTreeTest {
    @Test
    public void testGrow() {
        assertEquals(1, UtopianTree.grow(0));
        assertEquals(2, UtopianTree.grow(1)); // 1 x 2 = 2
        assertEquals(3, UtopianTree.grow(2)); // (1 x 2) + 1 = 2 + 1 = 3
        assertEquals(6, UtopianTree.grow(3)); // ((1 x 2) + 1) * 2 = (2 + 1) * 2 = 3 * 2 = 6
        assertEquals(7, UtopianTree.grow(4)); // in the problem
    }
}
