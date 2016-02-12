package staircase;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StairCaseTest {
    public static final String[] EXPECTED_ROWS = new String[]{
            "     #",
            "    ##",
            "   ###",
            "  ####",
            " #####",
            "######",
    };

    @Test
    public void zeroLevelsMeansFinishedIterator() {
        assertFalse(new StairCase(0).iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingNextOnZeroLevelStaircaseFails() {
        new StairCase(0).iterator().next();
    }

    @Test
    public void singleLevel() {
        Iterator<String> iterator = new StairCase(1).iterator();
        assertEquals("#", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void twoLevels() {
        Iterator<String> iterator = new StairCase(2).iterator();
        assertEquals(" #", iterator.next());
        assertEquals("##", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void manyLevels() {
        StairCase stairCase = new StairCase(6);
        int idx = 0;
        for (String row : stairCase)
            assertEquals(EXPECTED_ROWS[idx++], row);
    }
}
