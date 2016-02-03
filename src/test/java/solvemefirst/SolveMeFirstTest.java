package solvemefirst;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolveMeFirstTest {
    @Test
    public void testSolveMeFirst() {
        assertEquals(5, SolveMeFirst.solveMeFirst(2, 3));
        assertEquals(9, SolveMeFirst.solveMeFirst(4, 5));
    }
}
