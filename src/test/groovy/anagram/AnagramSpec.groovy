package anagram

import spock.lang.Specification


class AnagramSpec extends Specification {
    def "long test for anagrams"() {
        given: "anagram"
        def anagram = new Anagram()

        and: "all words"
        fillWithEnglishWords(anagram)

        when: "looking for a word"
        def word = "stop"
        def found = anagram.find("stop")
        println "looking for $word ==> $found"

        then: "should return all words in the anagram"
        ["post", "stop", "spot", "tops"] as Set == found
    }

    private static void fillWithEnglishWords(Anagram anagram) {
        def dict = "/usr/share/dict/words"
        println "loading $dict ..."

        def items = 0

        new File("$dict").eachLine { line ->
            anagram.add(line.trim())
            items++
        }

        println "  ...finished, loaded $items words."
    }
}