package reverse

import spock.lang.Specification


class ReverseWordsSpec extends Specification {
    def "ReverseWords.reverse() should reverse the order of aparition of words in a string"() {
        expect: "e.g. ReverseWords.reverse('hello world') should be 'world hello'"
        ReverseWords.reverse(s) == expected

        where:
        s                                       | expected
        null                                    | ""
        "x"                                     | "x"
        "y"                                     | "y"
        " "                                     | ""
        "x y"                                   | "y x"
        "x  y"                                  | "y x"
        "x y z"                                 | "z y x"
        "the crazy fox jumps over the lazy dog" | "dog lazy the over jumps fox crazy the"
    }
}