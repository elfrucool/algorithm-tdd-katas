package anagram;

import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;

public class AnagramTest {
    private Anagram anagram;

    @Before
    public void setUp() throws Exception {
        anagram = new Anagram();
    }

    @Test
    public void findNullReturnsEmptySet() {
        assertEquals(emptySet(), anagram.find(null));
    }

    @Test
    public void findSingleWordThatWasSetReturnsSetWithThatWord() {
        anagram.add("a");
        assertEquals(singleton("a"), anagram.find("a"));
    }

    @Test
    public void findBlankStringReturnsEmptySet() {
        assertEquals(emptySet(), anagram.find(" \n \r \t  "));
    }

    @Test
    public void findEmptyAnagramReturnsEmptySet() {
        assertEquals(emptySet(), anagram.find("anything"));
    }

    @Test
    public void findWordWithDifferentLettersThanCapturedReturnsEmptyCollection() {
        anagram.add("a");
        assertEquals(emptySet(), anagram.find("b"));
    }
}
