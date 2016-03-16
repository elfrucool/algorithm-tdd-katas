package candies;

import java.util.Scanner;

public class Candies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfChildren = scanner.nextInt();
        int[] children = new int[numberOfChildren];

        for (int i = 0; i < numberOfChildren; i++)
            children[i] = scanner.nextInt();

        System.out.println(distribute(children));
    }

    public static long distribute(int[] children) {
        int maxIndex = children.length - 1;

        long candies = children.length;

        long ascendingIncrement = 0L;
        long descendingIncrement = 0L;

        for (int index = 0; index + 1 < children.length; index++) {
            if (children[index] < children[index + 1])
                ascendingIncrement++;
            else
                ascendingIncrement = 0L;

            if (children[maxIndex - index] < children[maxIndex - (index + 1)])
                descendingIncrement++;
            else
                descendingIncrement = 0L;

            candies += ascendingIncrement + descendingIncrement;
        }

        return candies;
    }
}
