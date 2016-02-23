package cutsticks;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CutSticks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        List<Integer> elements = new ArrayList<>(numberOfElements);
        while (numberOfElements-- > 0)
            elements.add(scanner.nextInt());

        cutAll(elements, System.out::println);
    }

    public static List<Integer> cut(List<Integer> inputList, Consumer<Integer> out) {
        List<Integer> withoutZeroes = new LinkedList<>(inputList);
        withoutZeroes.removeIf(element -> element == 0);

        if (withoutZeroes.isEmpty())
            return Collections.emptyList();

        out.accept(withoutZeroes.size());

        PriorityQueue<Integer> sortedSticks = new PriorityQueue<>(withoutZeroes);
        int first = sortedSticks.remove();
        if (sortedSticks.contains(first))
            sortedSticks.remove();

        return sortedSticks.stream().map(item -> item - first).collect(Collectors.toList());
    }

    public static void cutAll(List<Integer> inputList, Consumer<Integer> out) {
        if (!inputList.isEmpty())
            cutAll(cut(inputList, out), out);
    }
}
