package staircase;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StairCase implements Iterable<String> {
    private final int _levels;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StairCase stairCase = new StairCase(scanner.nextInt());
        for (String row : stairCase)
            System.out.println(row);
    }

    public StairCase(int levels) {
        _levels = levels;
    }

    private String makeRow(int pending) {
        String row = "";
        int currentLevel = _levels - pending;

        while (pending-- > 0)
            row += " ";
        while (currentLevel-- > 0)
            row += "#";

        return row;
    }

    @Override
    public Iterator<String> iterator() {
        return new StairCaseIterator();
    }

    private class StairCaseIterator implements Iterator<String> {
        private int _pending = _levels;

        @Override
        public boolean hasNext() {
            return _pending > 0;
        }

        @Override
        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return makeRow(--_pending);
        }
    }
}
