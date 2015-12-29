package reversewords

import spock.lang.Specification
import spock.lang.Unroll

import static reversewords.ReverseWords.reverse


class ReverseWordsSpec extends Specification {
    @Unroll
    def "it should reverse the order of apparition of words in a string: #s -> '#expected'"() {
        expect:
        reverse(s) == expected

        where:
        s                                           | expected
        null                                        | ""
        "a"                                         | "a"
        "b"                                         | "b"
        " a"                                        | "a"
        "a b"                                       | "b a"
        "a b c"                                     | "c b a"
        "the  crazy fox   jumps over  the lazy dog" | "dog lazy the over jumps fox crazy the"
    }
}