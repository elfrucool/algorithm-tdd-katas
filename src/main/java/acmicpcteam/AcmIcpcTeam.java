package acmicpcteam;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class AcmIcpcTeam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Results results = calculate(loadPeopleWithKnowledge(scanner, scanner.nextInt()));
        System.out.printf("%d\n%d", results.topics, results.teams);
    }

    protected static List<BitObject> loadPeopleWithKnowledge(Scanner scanner, int numberOfPeople) {
        scanner.nextInt();

        List<BitObject> peopleWithKnowledge = new ArrayList<>(numberOfPeople);
        while (numberOfPeople-- > 0)
            peopleWithKnowledge.add(BitObject.fromString(scanner.next()));

        return peopleWithKnowledge;
    }

    public static Results calculate(List<BitObject> peopleWithKnowledge) {
        int numberOfPeople = peopleWithKnowledge.size();
        Results results = results(0, 0);

        for (int pivot = 0; pivot + 1 < numberOfPeople; pivot++)
            for (int offset = 1; pivot + offset < numberOfPeople; offset++)
                update(results, topicsOfTeam(peopleWithKnowledge.get(pivot), peopleWithKnowledge.get(pivot + offset)));

        return results;
    }

    public static int topicsOfTeam(BitObject person1, BitObject person2) {
        BitSet result = (BitSet) person1.getBitSet().clone();
        result.or(person2.getBitSet());
        return result.cardinality();
    }

    public static void update(Results results, int nowTopics) {
        if (nowTopics > results.topics)
            growTopics(results, nowTopics);
        else if (nowTopics == results.topics)
            growTeams(results);
    }

    public static void growTopics(Results results, int nowTopics) {
        results.topics = nowTopics;
        results.teams = 1;
    }

    protected static void growTeams(Results results) {
        results.teams++;
    }

    public static Results results(int topics, int teams) {
        return new Results(topics, teams);
    }

    public static class BitObject {
        private final int size;
        private final BitSet bitSet;

        public BitObject(int size, BitSet bitSet) {
            this.size = size;
            this.bitSet = bitSet;
        }

        public static BitObject bitObject(int size, BitSet bitSet) {
            return new BitObject(size, bitSet);
        }

        public static BitObject fromString(String bitsStr) {
            int size = bitsStr.length();
            BitSet bitSet = new BitSet(size);

            for (int index = 0; index < size; index++)
                bitSet.set(index, bitsStr.charAt(index) == '1');

            return bitObject(size, bitSet);
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < size; i++)
                s.append(bitSet.get(i) ? "1" : "0");
            return s.toString();
        }

        public BitSet getBitSet() {
            return bitSet;
        }
    }

    public static class Results {
        private int topics;
        private int teams;

        public Results(int topics, int teams) {
            this.topics = topics;
            this.teams = teams;
        }

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
    }
}