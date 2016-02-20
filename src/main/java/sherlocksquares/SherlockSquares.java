package sherlocksquares;

import java.util.Scanner;

public class SherlockSquares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCases = scanner.nextInt();
        while (numberOfCases-- > 0)
            System.out.println(squares(scanner.nextInt(), scanner.nextInt()));
    }

    public static int squares(int min, int max) {
        int sqrtMin = (int) Math.sqrt(min);
        int sqrtMax = (int) Math.sqrt(max);

        int numOfSquares = 0;

        numOfSquares += sqrtMax - sqrtMin;

        if (sqrtMin * sqrtMin == min)
            numOfSquares++;

        return numOfSquares;
    }
}
