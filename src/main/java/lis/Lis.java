package lis;

import java.util.Arrays;

public class Lis {
    public static int calculate(int[] numbers) {
        if (numbers.length < 1)
            return 0;

        int[] lis = new int[numbers.length];
        int[] top = new int[numbers.length];

        top[0] = numbers[0];
        int seqCount = 1;

        for (int index = 0; index + 1 < numbers.length; index++) {
            int seqIndex = 0;
            int next = numbers[index + 1];

            while (seqIndex < seqCount && top[seqIndex] >= next)
                seqIndex++;

            top[seqIndex] = next;

            if (seqIndex == seqCount)
                seqCount++;
            else
                lis[seqIndex]++;
        }

        Arrays.sort(lis);

        return lis[numbers.length - 1] + 1;
    }
}
