package anagram

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class AnagramSpec extends Specification {
    def "can instantiate"() {
        expect:
        new Anagram() != null
    }

    def "can add a word"() {
        when:
        def anagram = new Anagram()
        anagram.add("word")

        then:
        notThrown(Exception)
    }

    def "can look for non registered word"() {
        given:
        def anagram = new Anagram()

        expect:
        anagram.find("foo") == []
    }

    def "given a word it should find it"() {
        given:
        def anagram = new Anagram()

        when:
        anagram.add("hello")

        then:
        anagram.find("hello") == ["hello"]
    }

    def "given a word and looking for another that does not match it should return empty list"() {
        given:
        def anagram = new Anagram()

        when:
        anagram.add("hello")

        then:
        anagram.find("foo") == []
    }

    def "given a word and another word with same letters it should return both"() {
        given:
        def anagram = new Anagram()

        when:
        anagram.add("on")

        then:
        anagram.find("no").containsAll(["on", "no"])
    }

    def "given two words in same anagram and a third one in same anagram it should return the three words"() {
        given:
        def anagram = new Anagram()

        when:
        anagram.add("bac")
        anagram.add("cba")

        then:
        anagram.find("acb").containsAll(["bac", "cba", "acb"])
    }

    def "given two belonging to different anagrams, and a word belong to the first, then returns those two words"() {
        given:
        def anagram = new Anagram()

        when:
        anagram.add("no")
        anagram.add("yes")

        then:
        anagram.find("on").containsAll(["no", "on"])
    }

    def "big example"() {
        when: "all words in /usr/share/dict/words loaded in anagram object"
        def anagram = new Anagram()
        loadWords(anagram)

        then: "display all words in anagram"
        ["red", "erd"].containsAll(anagram.find("red"))
        ["stop", "post", "spot", "tops"].containsAll(anagram.find("stop"))
        ["blue", "lube"].containsAll(anagram.find("blue"))
        ["for", "orf", "fro"].containsAll(anagram.find("for"))
        ["are", "era", "ear", "aer", "rea"].containsAll(anagram.find("are"))
    }

    private void loadWords(anagram) {
        def wordsPath = "/usr/share/dict/words"
        println "reading everything in $wordsPath ..."

        def total = 0
        new File(wordsPath).eachLine { line ->
            def word = line.trim()
            if (!word.isEmpty()) {
                anagram.add(word)
                total++
            }
        }

        println "loaded $total words."
    }
}