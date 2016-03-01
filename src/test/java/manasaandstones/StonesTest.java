package manasaandstones;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;

public class StonesTest {
    @Test
    public void testGetLast() {
        assertEquals(singleton(0), Stones.getLast(1, 1, 1)); // single stone, single value
        assertEquals(singleton(1), Stones.getLast(2, 1, 1)); // two stone, single value
        assertEquals(singleton(2), Stones.getLast(2, 2, 2)); // two stone, single value (2)
        assertEquals(singleton(2), Stones.getLast(3, 1, 1)); // three stones, single value
        assertEquals(asSet(1, 2), Stones.getLast(2, 1, 2)); // two stones two values
        assertEquals(asSet(2, 3, 4), Stones.getLast(3, 1, 2)); // three stones two values
        assertEquals(asSet(3, 4, 5, 6), Stones.getLast(4, 1, 2)); // four stones, two values
        assertEquals(asSet(3, 4, 5, 6), Stones.getLast(4, 2, 1)); // four stones, two values, first is bigger
    }

    private Set<Integer> asSet(Integer... numbers) {
        return new TreeSet<>(asList(numbers));
    }
}

//    N     =     3
//    n     =     N
//     0
//    n     =     n   - 1
//     i           i-1
//    a     =     1
//    b     =     2
// last     =     a*n + b*(N-n)
//
//        n    a*n   b*(N-n)    last
//        ===========================
//        3   1*3=3  2*(3-3)=0  3+0=3
//        2   1*2=2  2*(3-2)=2  2+2=4
//        1   1*1=1  2*(3-1)=4  1+4=5
//        0   1*0=0  2*(3-0)=6  0+6=6
//
//
//                             result=3,4,5,6
