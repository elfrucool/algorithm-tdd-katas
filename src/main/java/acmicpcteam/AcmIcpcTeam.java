package acmicpcteam;

import java.util.BitSet;

public class AcmIcpcTeam {

    public static String bitsToString(BitObject bitObject) {
        String s = "";
        for (int i = 0; i < bitObject.getSize(); i++)
            s += bitObject.getBitSet().get(i) ? "1" : "0";
        return s;
    }

    public static BitObject stringToBits(String bitsStr) {
        int size = bitsStr.length();
        BitSet bitSet = new BitSet(size);

        for(int index = 0; index < size; index++)
          bitSet.set(index, bitsStr.charAt(index) == '1');

        return bitObject(size, bitSet);
    }

    public static BitObject bitObject(int size, BitSet bitSet) {
        return new BitObject(size, bitSet);
    }

    public static class BitObject {
        private final int size;
        private final BitSet bitSet;

        public int getSize() {
            return size;
        }

        public BitSet getBitSet() {
            return bitSet;
        }

        public BitObject(int size, BitSet bitSet) {
            this.size = size;
            this.bitSet = bitSet;
        }
    }
}