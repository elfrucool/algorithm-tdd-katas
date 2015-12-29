package atoi

import spock.lang.Specification
import spock.lang.Unroll

import static atoi.Atoi.atoi


class AtoiSpec extends Specification {
    @Unroll
    def "converts a string into an integer"() {
        expect:
        atoi(s) == expected

        where:
        s         | expected
        null      | 0
        "1"       | 1
        "0"       | 0
        ""        | 0
        " "       | 0
        " 1"      | 1
        "-1"      | -1
        "23"      | 23
        "-129482" | -129482
    }
}