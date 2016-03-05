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

    public static int calculate(int b, int w, int x, int y, int z) {
        return  b * (x <= y + z ? x : y + z ) +
                w * (y <= x + z ? y : x + z);
    }
}