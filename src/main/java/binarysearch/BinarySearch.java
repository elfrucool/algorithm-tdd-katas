package binarysearch;

import java.util.List;

public class BinarySearch {
    public int search(int number, List<Integer> list) {
        if (list == null || list.isEmpty())
            return -1;

        return search(number, list, 0, list.size() >> 1, list.size());
    }

    protected int search(int number, List<Integer> list, int left, int center, int right) {
        if (list.get(center) == number)
            return center;

        if (center <= left)
            return -1;

        if (number < list.get(center))
            return search(number, list, left, (left + center) >> 1, center);

        return search(number, list, center, (center + right) >> 1, right);
    }
}
