package zeroescomefirst;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class ZeroesComeFirst {
    public static List<Integer> sort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty())
            return emptyList();

        if (numbers.size() == 1)
            return singletonList(numbers.get(0));

        ArrayList<Integer> sorted = new ArrayList<>(numbers.size());
        int index = numbers.size();

        while (--index >= 0)
            if (numbers.get(index) != 0)
                sorted.add(0, numbers.get(index));

        sorted.addAll(0, nCopies(numbers.size() - sorted.size(), 0));
        return sorted;
    }
}
