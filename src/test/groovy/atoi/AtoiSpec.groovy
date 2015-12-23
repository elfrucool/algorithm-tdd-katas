package atoi

import spock.lang.Specification


class AtoiSpec extends Specification {
    def "Atoi.atoi converts strings to integers"() {
        expect:
        Atoi.atoi(s) == expected

        where:
        s       |    expected
        null    |    0
        "1"     |    1
        ""      |    0
        " "     |    0
        "2"     |    2
        " 1"    |    1
        "-1"    |   -1
        "12"    |   12
    }
}