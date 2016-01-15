package anagram;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class AnagramTest {
    @SafeVarargs
    protected static <T> Set<T> asSet(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    private Anagram anagram;

    @Before
    public void setUp() throws Exception {
        anagram = new Anagram();
    }

    public class GivenEmptyArguments {
        @Before
        public void setUp() {
            anagram.add("foo");
            anagram.add("bar");
        }

        @Test
        public void findNullReturnsEmptySet() {
            assertEquals(emptySet(), anagram.find(null));
        }

        @Test
        public void findBlankStringReturnsEmptySet() {
            assertEquals(emptySet(), anagram.find(" \n \r \t  "));
        }

    }

    public class GivenEmptyAnagram {
        @Test
        public void findAnythingReturnsEmptySet() {
            assertEquals(emptySet(), anagram.find("anything"));
        }
    }

    public class GivenAnagramWithSingleLetter {
        @Before
        public void setUp() {
            anagram.add("a");
        }

        @Test
        public void findExistingLetterReturnsSetWithThatLetter() {
            assertEquals(singleton("a"), anagram.find("a"));
        }

        @Test
        public void findDifferentLetterReturnsEmptySet() {
            assertEquals(emptySet(), anagram.find("b"));
        }
    }

    public class GivenAnagramWithWordsLongerThanOneLetter {
        @Before
        public void setUp() {
            anagram.add("abc");
        }

        @Test
        public void findAnotherWordWithSameLettersReturnsSetWithExistingAndSearchingWords() {
            assertEquals(asSet("abc", "bca"), anagram.find("bca"));
        }

        public class GivenSeveralWordsWithSameLetters {
            @Before
            public void setUp() {
                anagram.add("bca");
                anagram.add("cab");
            }

            @Test
            public void findAnotherWordWithSameLettersReturnsSetWithAllExistingAndSearchingWords() {
                assertEquals(asSet("abc", "bca", "cab", "cba"), anagram.find("cba"));
            }
        }

        public class GivenWordsWithDifferentLetters {
            @Before
            public void setUp() {
                anagram.add("no");
            }

            @Test
            public void findWordLooksFoTheCorrespondingAnagram() {
                assertEquals(asSet("no", "on"), anagram.find("on"));
                assertEquals(asSet("abc", "cba"), anagram.find("cba"));
            }
        }
    }
}
