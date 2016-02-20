package servicelane;

import java.util.Arrays;
import java.util.Scanner;

public class ServiceLane {
    public static final int MOTO = 1;
    public static final int CAR = 2;
    public static final int TRUCK = 6;
    public static final int PASSTHROUGH_LIMIT = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int laneSize = scanner.nextInt();
        int numberOfTestCases = scanner.nextInt();
        int[] lane = new int[laneSize];

        for (int i = 0; i < laneSize; i++)
            lane[i] = scanner.nextInt();

        while (numberOfTestCases-- > 0)
            System.out.println(getBiggestVehicleThroughSegment(lane, scanner.nextInt(), scanner.nextInt()));
    }

    public static int getBiggestVehicleThroughSegment(int[] lane, int entry, int exit) {
        if (exit - entry > PASSTHROUGH_LIMIT)
            exit = entry + PASSTHROUGH_LIMIT;
        int[] serviceLane = Arrays.copyOf(lane, lane.length);
        Arrays.sort(serviceLane, entry, exit + 1);
        return serviceLane[entry];
    }
}
