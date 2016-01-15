package anagram;

import java.util.*;

import static java.util.Collections.emptyList;

public class Anagram {
    private Map<String, Set<String>> dictionary = new HashMap<>();

    public void add(String word) {
        String key = canonicalize(word);

        if (!dictionary.containsKey(key))
            dictionary.put(key, new HashSet<>());

        dictionary.get(key).add(word);
    }

    public List<String> find(String word) {
        String key = canonicalize(word);

        if (!dictionary.containsKey(key))
            return emptyList();

        List<String> wordList = new ArrayList<>();

        wordList.addAll(dictionary.get(key));

        if (!wordList.contains(word))
            wordList.add(word);

        return wordList;
    }

    private String canonicalize(String word) {
        char[] chars = new char[word.length()];
        word.getChars(0, word.length(), chars, 0);
        Arrays.sort(chars);
        return new String(chars);
    }
}
