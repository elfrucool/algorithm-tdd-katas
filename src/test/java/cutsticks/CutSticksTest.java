package cutsticks;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class CutSticksTest {
    @Test
    public void testVeryBigExample() {
        List<Integer> bigList = new ArrayList<>(1000);
        for (int i = 0; i < 500; i++) {
            bigList.add(i);
            bigList.add(i);
        }

        List<Integer> bigExpectedOutput = new ArrayList<>(500);
        for (int i = 998; i > 0; i -= 2)
            bigExpectedOutput.add(i);

        assertCutAll(bigList, bigExpectedOutput);
    }

    @Test
    public void thousandOnes() {
        List<Integer> ones = new ArrayList<>(1000);
        for (int i = 1000; i > 0; i--)
            ones.add(1);
        assertCutAll(ones, singletonList(1000));
    }

    @Test
    public void testCutAll() {
        assertCutAll(emptyList(), emptyList()); // empty case
        assertCutAll(singletonList(1), singletonList(1)); // single case w/value
        assertCutAll(singletonList(0), emptyList()); // single case w/zero
        assertCutAll(asList(1, 2), asList(2, 1)); // two cases w/value
        assertCutAll(asList(0, 2), singletonList(1)); // two cases w/value
        assertCutAll(asList(3, 0, 2, 2, 4), asList(4, 2, 1)); // more complex case just to verify
        assertCutAll(asList(5, 4, 4, 2, 2, 8), asList(6, 4, 2, 1)); // problem example 00
        assertCutAll(asList(1, 2, 3, 4, 3, 3, 2, 1), asList(8, 6, 4, 1)); // problem example 01
    }

    protected void assertCutAll(List<Integer> inputList, List<Integer> expectedCaptured) {
        List<Integer> captured = new ArrayList<>(expectedCaptured.size());
        CutSticks.cutAll(inputList, captured::add);
        assertEquals(expectedCaptured, captured);
    }

    @Test
    public void testCut() {
        assertCut(emptyList(), emptyList(), 0); // empty case
        assertCut(singletonList(1), emptyList(), 1); // single case w/value
        assertCut(singletonList(0), emptyList(), 0); // single case w/zero
        assertCut(asList(1, 2), singletonList(1), 2); // two cases w/value
        assertCut(asList(0, 2), emptyList(), 1); // two cases w/zero in one (just to verify)
        assertCut(asList(3, 0, 2, 2, 4), asList(1, 2), 4); // more complex case just to verify
    }

    protected void assertCut(List<Integer> inputList, List<Integer> expectedRemanentSticks, int expectedNumberOfSticksCut) {
        List<Integer> captured = new ArrayList<>(1);
        assertEquals(expectedRemanentSticks, CutSticks.cut(inputList, captured::add));
        if (expectedNumberOfSticksCut > 0)
            assertEquals(expectedNumberOfSticksCut, (int) captured.get(0));
    }
}
