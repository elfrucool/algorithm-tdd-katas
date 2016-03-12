package subarraysum;

import java.util.Scanner;

interface Problem {
    void input(int n, int index);

    long solution();
}

public class SubArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        while (numberOfTestCases-- > 0)
            dump(maxContiguousAndNonContiguous(loadData(scanner)));
    }

    private static void dump(long[] results) {
        System.out.printf("%d %d\n", results[0], results[1]);
    }

    private static int[] loadData(Scanner scanner) {
        int[] numbers = new int[scanner.nextInt()];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = scanner.nextInt();
        return numbers;
    }

    public static long[] maxContiguousAndNonContiguous(int[] numbers) {
        Problem contiguous = new Contiguous();
        Problem nonContiguous = new NonContiguous();

        for (int i = 0; i < numbers.length; i++) {
            contiguous.input(numbers[i], i);
            nonContiguous.input(numbers[i], i);
        }

        return new long[]{contiguous.solution(), nonContiguous.solution()};
    }

    private static class Contiguous implements Problem {
        private long currentMax;
        private long overallMax = Long.MIN_VALUE;

        @Override
        public void input(int n, int index) {
            if (currentMax >= 0 && (n > 0 || currentMax + n >= 0))
                currentMax = currentMax + n;
            else currentMax = n;

            if (overallMax < currentMax)
                overallMax = currentMax;
        }

        @Override
        public long solution() {
            return currentMax < overallMax ? overallMax : currentMax;
        }
    }

    private static class NonContiguous implements Problem {
        long max;

        @Override
        public void input(int n, int index) {
            if (n >= 0 && max >= 0)
                max += n;
            else if (index == 0 || max < n)
                max = n;
        }

        @Override
        public long solution() {
            return max;
        }
    }
}
