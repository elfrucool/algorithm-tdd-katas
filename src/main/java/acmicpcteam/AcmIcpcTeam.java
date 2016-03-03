package acmicpcteam;

import java.util.BitSet;
import java.util.List;

public class AcmIcpcTeam {
    public static Results calculate(List<BitObject> peopleWithKnowledge) {
        int teams = 0;
        int pivot = 0;
        int topics = 0;
        int numberOfPeople = peopleWithKnowledge.size();

        if (pivot + 1 < numberOfPeople) {
            int offset = 1;
            while (offset < numberOfPeople) {
                topics = topicsOfTeam(peopleWithKnowledge.get(pivot), peopleWithKnowledge.get(pivot + offset));
                offset++;
                teams++;
            }
        }

        return results(topics, teams);
    }

    public static int topicsOfTeam(BitObject person1, BitObject person2) {
        BitSet result = (BitSet) person1.getBitSet().clone();
        result.or(person2.getBitSet());
        return result.cardinality();
    }

    public static String bitsToString(BitObject bitObject) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitObject.getSize(); i++)
            s.append(bitObject.getBitSet().get(i) ? "1" : "0");
        return s.toString();
    }

    public static BitObject stringToBits(String bitsStr) {
        int size = bitsStr.length();
        BitSet bitSet = new BitSet(size);

        for (int index = 0; index < size; index++)
            bitSet.set(index, bitsStr.charAt(index) == '1');

        return bitObject(size, bitSet);
    }

    public static BitObject bitObject(int size, BitSet bitSet) {
        return new BitObject(size, bitSet);
    }

    public static Results results(int topics, int teams) {
        return new Results(topics, teams);
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

    public static class Results {
        private final int topics;
        private final int teams;

        @Override
        public String toString() {
            return "Results{" +
                    "topics=" + topics +
                    ", teams=" + teams +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Results results = (Results) o;

            return topics == results.topics && teams == results.teams;
        }

        @Override
        public int hashCode() {
            int result = topics;
            result = 31 * result + teams;
            return result;
        }

        public Results(int topics, int teams) {
            this.topics = topics;
            this.teams = teams;
        }
    }
}