package chocolate;

import java.util.Scanner;

public class ChocolateFeast {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCases = scanner.nextInt();
        while (numberOfCases-- > 0)
            System.out.println(buy(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
    }

    public static int buy(int moneyInPocket, int chocolatePrice, int wrappersForNewChocolate) {
        int dueToPayment = moneyInPocket / chocolatePrice;
        int dueToWrappers = dueToPayment / wrappersForNewChocolate;
        int remanentWrappers = dueToPayment % wrappersForNewChocolate;
        int dueToRemanentWrappers = getWrappersFromWrappers(wrappersForNewChocolate, dueToWrappers, remanentWrappers);
        return dueToPayment + dueToWrappers + dueToRemanentWrappers;
    }

    protected static int getWrappersFromWrappers(
            int wrappersToChocolateRate,
            int wrappersFromDivision,
            int wrappersFromRemanent)
    {
        int totalWrappersToExchange = wrappersFromDivision + wrappersFromRemanent;

        if (totalWrappersToExchange < wrappersToChocolateRate)
            return 0;

        int wrappersFromWrappers = totalWrappersToExchange / wrappersToChocolateRate;
        int remanentWrappers = totalWrappersToExchange % wrappersToChocolateRate;

        return wrappersFromWrappers +
                getWrappersFromWrappers(wrappersToChocolateRate, wrappersFromWrappers, remanentWrappers);
    }
}
