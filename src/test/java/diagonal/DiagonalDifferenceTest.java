package diagonal;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;
import java.util.Scanner;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DiagonalDifferenceTest {
    @SuppressWarnings("unused") // used by JUnitParams runner
    public Object parametersForTestCalculate() {
        return new Object[]{ //
                $(0, new int[0][0]), // empty matrix
                $(2, new int[][]{{1, 4}, {2, 3}}), // 2x2 matrix -> |(1+3)-(4+2)| = |4 - 6| = |-2| = 2
                $(0, new int[][]{{6}}), // 1x1 matrices always return 0 since |x - x| = 0
                $(0, new int[][]{{1, 2}, {3, 4}}), // 2x2 matrix -> |(1+4)-(2+3)| = |5 - 5| = 0
        };
    }

    @Test
    @Parameters
    public void testCalculate(int expected, int[][] matrix) {
        assertEquals(expected, DiagonalDifference.calculate(matrix));
    }

    @SuppressWarnings("unused") // used by JUnitParams runner
    public Object parametersForTestCalculateDiagonals() {
        return new Object[]{ //
                $(new int[]{0, 0}, new int[0][0]), // empty matrix
                $(new int[]{1, 1}, new int[][]{{1}}), // 1x1 matrix of 1
                $(new int[]{0, 0}, new int[][]{{0}}), // 1x1 matrix of 0
                $(new int[]{5, 5}, new int[][]{{1, 3}, {2, 4}}), // 2x2 matrix
        };
    }

    @Test
    @Parameters
    public void testCalculateDiagonals(int[] expected, int[][] matrix) {
        assertArrayEquals(expected, DiagonalDifference.calculateDiagonals(matrix));
    }

    @SuppressWarnings("unused") // used by JUnitParams runner
    public Object parametersForTestCapture() {
        return new Object[]{ //
                $(new int[0][0], "0"), // empty matrix
                $(new int[][]{{1}}, "1\n1"), // 1x1 matrix of 1
                $(new int[][]{{0}}, "1\n0"), // 1x1 matrix of 0
                $(new int[][]{{1, 2}, {3, 4}}, "2\n1 2\n3 4"), // 2x2 matrix
        };
    }

    @Test
    @Parameters
    public void testCapture(int[][] expectedMatrix, String input) {
        assertArrayEquals(expectedMatrix, DiagonalDifference.capture(new Scanner(new StringReader(input))));
    }
}
