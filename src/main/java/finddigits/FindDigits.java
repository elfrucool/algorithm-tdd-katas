package finddigits;

import java.util.Scanner;

public class FindDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCases = scanner.nextInt();
        while (numberOfCases-- > 0)
            System.out.println(find(scanner.next()));
    }

    public static int find(String numberAsString) {
        int number = Integer.parseInt(numberAsString);
        int length = numberAsString.length();
        int divisions = 0;

        for (int i = 0; i < length; i++) {
            int digit = numberAsString.charAt(i) - '0';
            if (digit != 0 && number % digit == 0)
                divisions++;
        }

        return divisions;
    }
}
