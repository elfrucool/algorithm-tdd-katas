package lis;

import java.util.Arrays;

public class Lis {
    public static int calculate(int[] numbers) {
        int length = numbers.length;

        if (length == 0)
            return 0;

        int[] lis = arrayOf(1, length);
        int[] top = new int[length];

        top[0] = numbers[0];
        int sequenceId = 1;

        for (int index = 0; index + 1 < length; index++) {
            int next = numbers[index + 1];
            int sequenceIndex = 0;

            sequenceIndex = getSequenceIndex(top, sequenceId, next, sequenceIndex);

            if (sequenceIndex < sequenceId) {
                top[sequenceIndex] = next;
                lis[sequenceIndex]++;
            } else if (sequenceId + 1 < length) {
                top[sequenceId++] = next;
            }
        }

        Arrays.sort(lis);

        return lis[length - 1];
    }

    protected static int getSequenceIndex(int[] top, int sequenceId, int next, int sequenceIndex) {
        for (; sequenceIndex < sequenceId; sequenceIndex++)
            if (top[sequenceIndex] < next)
                return sequenceIndex;

        return sequenceId;
    }

    private static int[] arrayOf(int initialValue, int length) {
        int[] ints = new int[length];
        Arrays.fill(ints, initialValue);
        return ints;
    }
}
