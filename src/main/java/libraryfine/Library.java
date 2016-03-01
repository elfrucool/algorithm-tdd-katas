package libraryfine;

import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(fine( //
                scanner.nextInt(), //
                scanner.nextInt(), //
                scanner.nextInt(), //
                scanner.nextInt(), //
                scanner.nextInt(), //
                scanner.nextInt()));
    }

    public static int fine(
            int actualDay,
            int actualMonth,
            int actualYear,
            int expectedDay,
            int expectedMonth,
            int expectedYear) //
    {
        if (isBeforeOrSame(actualDay, actualMonth, actualYear, expectedDay, expectedMonth, expectedYear))
            return 0;
        if (actualYear > expectedYear)
            return 10_000;
        if (actualMonth > expectedMonth)
            return (actualMonth - expectedMonth) * 500;
        return (actualDay - expectedDay) * 15;
    }

    protected static boolean isBeforeOrSame(
            int actualDay, int actualMonth, int actualYear, int expectedDay, int expectedMonth, int expectedYear)//
    {
        return actualYear < expectedYear ||
                actualYear == expectedYear &&
                        (actualMonth < expectedMonth ||
                                actualMonth == expectedMonth && actualDay <= expectedDay);
    }
}
