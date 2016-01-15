package anagram;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class Anagram {
    private String dictionary;

    public Set<String> find(String word) {
        if (word == null || word.trim().isEmpty() || dictionary == null)
            return emptySet();
        return dictionary.equals(word) ? singleton(word) : emptySet();
    }

    public void add(String word) {
        dictionary = word;
    }
}
