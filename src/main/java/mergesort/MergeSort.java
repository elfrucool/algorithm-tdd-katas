package mergesort;

public class MergeSort {
    public static void mergeSort(int[] numbers) {
        mergeSort(numbers, new int[numbers.length], 0, numbers.length);
    }

    private static void mergeSort(int[] numbers, int[] tmp, int left, int right) {
        int size = right - left;
        int center = size / 2 + left;

        if (size > 1) {
            mergeSort(numbers, tmp, left, center);
            mergeSort(numbers, tmp, center, right);
            merge(numbers, tmp, left, center, right);
        }
    }

    public static void merge(int[] numbers, int[] tmp, int left, int center, int right) {
        int leftCursor = left;
        int centerCursor = center;
        int tmpCursor = 0;

        while (leftCursor < center && centerCursor < right) {
            if (numbers[leftCursor] < numbers[centerCursor])
                tmp[tmpCursor++] = numbers[leftCursor++];
            else
                tmp[tmpCursor++] = numbers[centerCursor++];
        }

        if (leftCursor == center)
            System.arraycopy(numbers, centerCursor, tmp, tmpCursor, right - centerCursor);

        if (centerCursor == right)
            System.arraycopy(numbers, leftCursor, tmp, tmpCursor, center - leftCursor);

        System.arraycopy(tmp, 0, numbers, left, right - left);
    }
}
