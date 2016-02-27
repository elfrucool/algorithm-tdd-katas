package cavitymap;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {
    @Test
    public void testFindUsingStringArray() {
        assertArrayEquals($$("1112", "1X12", "18X2", "1234"), Solution.find($$("1112", "1912", "1892", "1234"))); // example
    }

    @Test
    public void testFindUsingBidirectionalCharArray() {
        assertArrayEquals($("1"), Solution.find($("1"))); // minimal
        assertArrayEquals($("2"), Solution.find($("2"))); // with other value
        assertArrayEquals($("111", "1X1", "111"), Solution.find($("111", "121", "111"))); // minimal cavity
        assertArrayEquals($("121", "121", "111"), Solution.find($("121", "121", "111"))); // upper is same size
        assertArrayEquals($("111", "121", "121"), Solution.find($("111", "121", "121"))); // lower is same size
        assertArrayEquals($("111", "221", "111"), Solution.find($("111", "221", "111"))); // left is same size
        assertArrayEquals($("111", "122", "111"), Solution.find($("111", "122", "111"))); // right is same size
        assertArrayEquals($("1111", "11X1", "1111", "1111"), Solution.find($("1111", "1121", "1111", "1111"))); // cavity in another column
        assertArrayEquals($("1111", "1111", "1X11", "1111"), Solution.find($("1111", "1111", "1211", "1111"))); // cavity in another row
        assertArrayEquals($("1111", "1X11", "11X1", "1111"), Solution.find($("1111", "1211", "1121", "1111"))); // two cavities to verify
        assertArrayEquals($("1112", "1X12", "18X2", "1234"), Solution.find($("1112", "1912", "1892", "1234"))); // example
    }

    private static char[][] $(String... strings) {
        return Solution.toCharArrays(strings);
    }

    private static String[] $$(String... strings) {
        return strings;
    }

    @Test
    public void testToCharArrays() {
        assertArrayEquals( //
                new char[][]{{'1', '2', '3'}, {'4', '5', '6'}}, //
                Solution.toCharArrays($$("123", "456")));
    }

    @Test
    public void testToStringArray() {
        assertArrayEquals($$("123", "456"), Solution.toStringArray($("123", "456")));
    }
}
