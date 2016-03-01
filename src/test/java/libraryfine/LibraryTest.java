package libraryfine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    @Test
    public void nothing() {
        assertEquals(0, Library.fine(6,6,2015,   6,6,2015)); // equal the date
        assertEquals(15 * 3, Library.fine(9,6,2015,   6,6,2015)); // after: same month, different day
        assertEquals(0, Library.fine(9,6,2015,   6,7,2015)); // before: same year, smaller month, bigger day
        assertEquals(500 * 3, Library.fine(6,9,2015,   6,6,2015)); // after: same year, different month
        assertEquals(0, Library.fine(9,6,2015,   6,3,2016)); // before: smaller year, bigger month, bigger day
        assertEquals(10000, Library.fine(6,6,2016,   6,6,2015)); // after: different year
    }
}
