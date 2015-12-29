package mergesort;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class MergeSort {
    public static List<Integer> sort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty())
            return emptyList();

        if (numbers.size() == 1)
            return singletonList(numbers.get(0));

        int center = numbers.size() / 2;
        return merge(sort(numbers.subList(0, center)), sort(numbers.subList(center, numbers.size())));
    }

    protected static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> sorted = new ArrayList<>(left.size() + right.size());

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size())
            if (left.get(leftIndex) < right.get(rightIndex))
                sorted.add(left.get(leftIndex++));
            else
                sorted.add(right.get(rightIndex++));

        sorted.addAll(right.subList(rightIndex, right.size()));
        sorted.addAll(left.subList(leftIndex, left.size()));

        return sorted;
    }
}
