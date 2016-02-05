package plusminus;

import java.util.Iterator;
import java.util.Scanner;

public class PlusMinus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt(); // ignore result
        double[] results = calculate(new ScannerIntegerIterator(scanner));
        System.out.printf("%f\n%f\n%f", results[0], results[1], results[2]);
    }

    public static double[] calculate(Iterator<Integer> numbersIterator) {
        if (!numbersIterator.hasNext())
            return new double[]{0.0, 0.0, 0.0};

        int size = 0;

        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;

        for (; numbersIterator.hasNext(); size++) {
            int signum = Integer.signum(numbersIterator.next());

            positiveCount += signum > 0 ? 1 : 0;
            negativeCount += signum < 0 ? 1 : 0;
            zeroCount += signum == 0 ? 1 : 0;
        }

        double positiveRatio = ((double) positiveCount) / size;
        double negativeRatio = ((double) negativeCount) / size;
        double zeroRatio = ((double) zeroCount) / size;

        return new double[]{positiveRatio, negativeRatio, zeroRatio};
    }

    private static class ScannerIntegerIterator implements Iterator<Integer> {
        private final Scanner scanner;

        public ScannerIntegerIterator(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public boolean hasNext() {
            return scanner.hasNext();
        }

        @Override
        public Integer next() {
            return scanner.nextInt();
        }
    }
}
