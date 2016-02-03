package lis;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// longest increasing subsequence
// [2, 5, 3, 7, 101, 18] -> |[2, 3, 7, 101]| -> 4
public class LisTest {
    @Test
    public void testCalculate() {
        assertEquals(0, Lis.calculate(new int[0])); // empty collection
        assertEquals(1, Lis.calculate(new int[]{0})); // single element
        assertEquals(2, Lis.calculate(new int[]{0, 1})); // collection bigger than one
        assertEquals(1, Lis.calculate(new int[]{1, 0})); // sequence smaller than collection
        assertEquals(3, Lis.calculate(new int[]{0, 1, 2})); // more than 2 in sequence
        assertEquals(2, Lis.calculate(new int[]{0, 1, 1})); // break the sequence
        assertEquals(2, Lis.calculate(new int[]{0, 1, 0, 1})); // noise in sequence
        assertEquals(3, Lis.calculate(new int[]{5, 1, 6, 2, 3})); // sequence of smaller numbers is greater
        assertEquals(3, Lis.calculate(new int[]{1, -5, 2, -9, -4, -8, -7})); // negative numbers
        assertEquals(4, Lis.calculate(new int[]{2, 5, 3, 7, 101, 18})); // the original problem
    }
}
