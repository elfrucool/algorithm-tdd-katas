package taumandbirthday;

import java.util.Scanner;

public class DikshaGifts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();
        while (numberOfTestCases-- > 0)
            System.out.println(calculate( //
                    scanner.nextInt(), scanner.nextInt(), //
                    scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
    }

    public static long calculate(long b, long w, long x, long y, long z) {
        return  b * (x <= y + z ? x : y + z ) +
                w * (y <= x + z ? y : x + z);
    }
}