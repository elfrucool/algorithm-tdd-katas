package subarraysum;

import java.util.Scanner;

public abstract class SubArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCases = scanner.nextInt();

        while (numberOfCases-- > 0)
            executeTestCase(scanner);
    }

    protected static void executeTestCase(Scanner scanner) {
        long[] maxSumBoth = maxSumBoth(loadArray(scanner, scanner.nextInt()));
        System.out.printf("%d %d\n", maxSumBoth[0], maxSumBoth[1]);
    }

    private static int[] loadArray(Scanner scanner, int arraySize) {
        int[] numbers = new int[arraySize];

        for (int i = 0; i < arraySize; i++)
            numbers[i] = scanner.nextInt();

        return numbers;
    }

    public static long[] maxSumBoth(int[] numbers) {
        SubArraySum[] problems = {contiguous(), nonContiguous()};

        for (int n : numbers)
            for (SubArraySum p : problems)
                p.add(n);

        return new long[]{problems[0].max(), problems[1].max()};
    }

    public static SubArraySum contiguous() {
        return new Contiguous();
    }

    public static SubArraySum nonContiguous() {
        return new NonContiguous();
    }

    public abstract long max();

    public abstract void add(int n);

    private static class Contiguous extends SubArraySum {
        private long currentSum;
        private long maxSum = Long.MIN_VALUE;

        @Override
        public long max() {
            return maxSum < currentSum ? currentSum : maxSum;
        }

        @Override
        public void add(int n) {
            if (currentSum < 0 && n >= 0 || n < 0 && currentSum <= 0)
                currentSum = n;
            else
                currentSum += n;

            if (maxSum < currentSum)
                maxSum = currentSum;
        }
    }

    private static class NonContiguous extends SubArraySum {
        private long sum;
        private int numbersAdded;

        @Override
        public long max() {
            return sum;
        }

        @Override
        public void add(int n) {
            numbersAdded++;
            if (n >= 0 && sum >= 0) {
                sum += n;
            } else if (numbersAdded == 1 || sum < n)
                sum = n;
        }
    }
}
