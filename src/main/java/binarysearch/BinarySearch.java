package binarysearch;

import java.util.List;

public class BinarySearch {
    public int search(List<Integer> list, int number) {
        if (list == null || list.isEmpty())
            return -1;
        return search(list, number, 0, list.size() / 2, list.size());
    }

    protected int search(List<Integer> list, int number, int left, int center, int right) {
        int testNumber = list.get(center);
        if (testNumber == number)
            return center;
        if (left < center && number < testNumber)
            return search(list, number, left, (left + center) / 2, center);
        else if (center + 1 < right && testNumber < number)
            return search(list, number, center, (center + right) / 2, right);
        return -1;
    }
}
