package zeroescomefirst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static java.util.Collections.singletonList;

public class ZeroesComeFirst {
    public static List<Integer> sort(List<Integer> list) {
        if (list == null || list.isEmpty())
            return emptyList();

        if (list.size() == 1)
            return singletonList(list.get(0));

        List<Integer> sorted = new ArrayList<>();

        int index = list.size();
        while (index-- > 0)
            if (list.get(index) != 0)
                sorted.add(0, list.get(index));

        sorted.addAll(0, nCopies(list.size() - sorted.size(), 0));
        return sorted;
    }
}
