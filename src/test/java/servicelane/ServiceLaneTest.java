package servicelane;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static servicelane.ServiceLane.*;

public class ServiceLaneTest {

    @Test
    public void testGetBiggestVehicleThroughSegment() {
        assertServiceLane(new int[]{MOTO, CAR}, 0, 1, MOTO); // minimal
        assertServiceLane(new int[]{CAR, CAR}, 0, 1, CAR); // another car with
        assertServiceLane(new int[]{CAR, MOTO}, 0, 1, MOTO); // mixed widths
        assertServiceLane(new int[]{CAR, TRUCK, MOTO}, 0, 1, CAR); // ending before length
        assertServiceLane(new int[]{MOTO, TRUCK, MOTO, CAR, CAR, TRUCK}, 2, 5, MOTO); // starting after zero
        assertServiceLane(new int[]{MOTO, CAR, CAR, TRUCK}, 1, 3, CAR); // starting after zero
        assertServiceLane(new int[]{1, 2, 2, 2, 1}, 2, 4, MOTO); // example
        assertServiceLane(makeBigLane(), 10, 5000, CAR); // adjusting to N
    }

    private int[] makeBigLane() {
        int[] lane = new int[10000];
        Arrays.fill(lane, 0, 1000, TRUCK);
        Arrays.fill(lane, 1000, 1011, CAR);
        Arrays.fill(lane, 1011, 10000, MOTO);
        return lane;
    }

    protected void assertServiceLane(int[] lane, int entry, int exit, int expected) {
        assertEquals(expected, ServiceLane.getBiggestVehicleThroughSegment(lane, entry, exit));
    }
}
