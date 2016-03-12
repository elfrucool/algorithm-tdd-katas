package subarraysum;

import java.util.Scanner;

interface Problem {
    void add(int n);
    long max();
}


class Contiguous implements Problem {
    private long currentMax;
    private long overallMax = Long.MIN_VALUE;

    @Override
    public void add(int n) {
        if (currentMax > 0 && n + currentMax >= 0)
            currentMax = currentMax + n;
        else
            currentMax = n;

        if (overallMax < currentMax)
            overallMax = currentMax;
    }

    @Override
    public long max() {
        return overallMax < currentMax ? currentMax : overallMax;
    }
}

class NonContiguous implements Problem {
    private long max;
    private int additions;

    @Override
    public void add(int n) {
        if (n > 0)
            max = max + n;
        else if (additions == 0 || max < n)
            max = n;

        additions++;
    }

    @Override
    public long max() {
        return max;
    }
}

public class SumArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0)
            dump(solve(load(scanner)));
    }

    private static void dump(Solution solution) {
        System.out.printf("%d %d\n", solution.contiguous, solution.nonContiguous);
    }

    private static int[] load(Scanner scanner) {
        int[] numbers = new int[scanner.nextInt()];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = scanner.nextInt();
        return numbers;
    }

    public static Solution solve(int[] numbers) {
        Problem contiguous = new Contiguous();
        Problem nonContiguous = new NonContiguous();

        for (int n : numbers) {
            contiguous.add(n);
            nonContiguous.add(n);
        }

        return new Solution(contiguous.max(), nonContiguous.max());
    }

    public static class Solution {
        public long contiguous;
        public long nonContiguous;

        public Solution(long contiguous, long nonContiguous) {
            this.contiguous = contiguous;
            this.nonContiguous = nonContiguous;
        }
    }
}
