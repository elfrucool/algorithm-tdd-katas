package chocolate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// #C = BC + WC + WWC
// BC = $/$C
// WC[0] = BC/W
// WC[1] = (WC[0] + BC % W) / W
// WC[n] = (WC[n - 1] + WC[N - 2] % W)/W
// ================================================================================
// $    $C  W   |   BC  WC  WWC |   #C
// ================================================================================
// 10   2   5   |   5   1   0   |   6
// 12   4   4   |   3   0   0   |   3
//  6   2   2   |   3   1   1   |   5
public class ChocolateFeastTest {
    @Test
    public void testBuy() {
        assertEquals(1, ChocolateFeast.buy(2, 2, 2)); // minimal case
        assertEquals(2, ChocolateFeast.buy(4, 2, 4)); // only by price
        assertEquals(3, ChocolateFeast.buy(2, 1, 2)); // by price + wrapper but without remanent
        assertEquals(5, ChocolateFeast.buy(6, 2, 2)); // by price + wrapper + remanent: third example
        assertEquals(15, ChocolateFeast.buy(16, 2, 2)); // 8 + 4 + 2 + 1 = 15 // wrappers from wrappers
        assertEquals(6, ChocolateFeast.buy(10, 2, 5)); // first example
        assertEquals(3, ChocolateFeast.buy(12, 4, 4)); // second example
        assertEquals(899, ChocolateFeast.buy(43203, 60, 5)); // failing test in case 5 ?
    }
}
