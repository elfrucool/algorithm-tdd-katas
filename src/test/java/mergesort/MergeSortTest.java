package mergesort;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MergeSortTest {
    private static List<Integer> mergeSort(List<Integer> original) {
        if (original == null || original.isEmpty())
            return Collections.emptyList();

        if (original.size() == 1)
            return Collections.singletonList(original.get(0));

        int center = original.size() / 2;
        return merge(mergeSort(original.subList(0, center)), mergeSort(original.subList(center, original.size())));
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> sorted = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) > right.get(rightIndex))
                sorted.add(right.get(rightIndex++));
            else
                sorted.add(left.get(leftIndex++));
        }

        if (leftIndex < left.size())
            sorted.addAll(left.subList(leftIndex, left.size()));
        if (rightIndex < right.size())
            sorted.addAll(right.subList(rightIndex, right.size()));

        return sorted;
    }

    @SuppressWarnings("unused")
    protected Object parametersForMergeSortSortsNumbers() {
        return new Object[]{//
                $(null, Collections.EMPTY_LIST), //
                $(Collections.emptyList(), Collections.emptyList()), //
                $(Collections.singletonList(1), Collections.singletonList(1)), //
                $(Arrays.asList(2, 1), Arrays.asList(1, 2)), //
                $(Arrays.asList(1, 2), Arrays.asList(1, 2)), //
                $(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)), //
                $(Arrays.asList(3, 1, 2), Arrays.asList(1, 2, 3)), //
                $(Arrays.asList(1, 3, 2), Arrays.asList(1, 2, 3)), //
                $(Arrays.asList(3, 4, 1, 2), Arrays.asList(1, 2, 3, 4)), //
                $(Arrays.asList(4, 3, 1, 2), Arrays.asList(1, 2, 3, 4)), //
        };
    }

    @Test
    @Parameters
    public void mergeSortSortsNumbers(List<Integer> original, List<Object> expected) {
        assertThat(mergeSort(original), is(expected));
    }
}
