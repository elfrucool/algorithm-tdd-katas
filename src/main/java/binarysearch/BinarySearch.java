package binarysearch;

import java.util.List;

public class BinarySearch {
    public int search(List<Integer> list, int number) {
        if (list == null || list.isEmpty())
            return -1;
        return search(list, number, 0, list.size() / 2, list.size());
    }

    protected int search(List<Integer> list, int number, int left, int center, int right) {
        if (number == list.get(center))
            return center;
        if (left < center && number < list.get(center))
            return search(list, number, left, (left + center) / 2, center);
        else if (center + 1 < right && list.get(center) < number)
            return search(list, number, center, (center + right) / 2, right);
        return -1;
    }
}
