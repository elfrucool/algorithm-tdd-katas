package diagonal;

import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        System.out.println(calculate(capture(new Scanner(System.in))));
    }

    public static int calculate(int[][] matrix) {
        if (matrix.length < 2)
            return 0;
        int[] diagonals = calculateDiagonals(matrix);
        return Math.abs(diagonals[0] - diagonals[1]);
    }

    public static int[] calculateDiagonals(int[][] matrix) {
        if (matrix.length == 0)
            return new int[]{0, 0};

        int totalLeft = 0;
        int totalRight = 0;

        for (int i = 0; i < matrix.length; i++) {
            totalLeft += matrix[i][i];
            totalRight += matrix[i][matrix.length - 1 - i];
        }

        return new int[]{totalLeft, totalRight};
    }

    public static int[][] capture(Scanner scanner) {
        int size = scanner.nextInt();

        if (size == 0)
            return new int[0][0];

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = scanner.nextInt();

        return matrix;
    }
}
