package anagram;

import java.util.*;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class Anagram {
    private Map<String, Set<String>> dictionary = new HashMap<>();

    public Set<String> find(String word) {
        if (word == null || word.trim().isEmpty() || dictionary == null)
            return emptySet();

        String key = canonicalize(word);

        if (!dictionary.containsKey(key))
            return emptySet();

        return makeFoundWordsSet(key, word);
    }

    protected Set<String> makeFoundWordsSet(String key, String word) {
        Set<String> foundWords = new HashSet<>();

        foundWords.addAll(dictionary.get(key));
        if (!foundWords.contains(word))
            foundWords.add(word);

        return foundWords;
    }

    public void add(String word) {
        String key = canonicalize(word);
        if (!dictionary.containsKey(key))
            dictionary.put(key, new HashSet<>());
        dictionary.get(key).add(word);
    }

    private String canonicalize(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
