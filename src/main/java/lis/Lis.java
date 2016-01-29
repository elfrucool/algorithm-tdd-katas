package lis;

public class Lis {
    public static int calculate(int[] numbers) {
        if (numbers.length == 0)
            return 0;
        if (numbers.length == 1)
            return 1;

        int index = 0;
        int lis = 1;

        while (index + 1 < numbers.length)
            if (numbers[index] < numbers[index++ + 1])
                lis++;

        return lis;
    }
}
