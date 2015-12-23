package zeroescomefirst

import spock.lang.Specification


class ZeroesComeFirstSpec extends Specification {
    def "ZeroesComeFirst.sort(List<Integer>) returns a new list with all zeros at the first place"() {
        expect: "ZeroesComeFirst.sort([1, 0, -1, 0, 2, 0, 3]) return: [0, 0, 0, 1, -1, 2, 3]"
        ZeroesComeFirst.sort(numbers) == expected

        where:
        numbers          | expected
        null             | []
        [1]              | [1]
        []               | []
        [2]              | [2]
        [0, 0]           | [0, 0]
        [0, 1]           | [0, 1]
        [0, 2]           | [0, 2]
        [1, 0]           | [0, 1]
        [1, 0, -1, 2, 0] | [0, 0, 1, -1, 2]
    }
}