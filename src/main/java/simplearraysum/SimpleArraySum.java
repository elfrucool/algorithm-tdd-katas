package simplearraysum;

import java.util.Scanner;

public class SimpleArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // we don't need the first input element
        System.out.println(calculate(lineToNumbers(scanner.nextLine())));
    }

    public static long calculate(int[] numbers) {
        long sum = 0;
        for (int number : numbers)
            sum += number;
        return sum;
    }

    public static int[] lineToNumbers(String line) {
        if (line.length() == 0)
            return new int[0];

        String[] tokens = line.split(" ");
        int[] numbers = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++)
            numbers[i] = Integer.parseInt(tokens[i]);

        return numbers;
    }
}
