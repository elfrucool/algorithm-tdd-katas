package zeroescomefirst

import spock.lang.Specification
import spock.lang.Unroll

import static zeroescomefirst.ZeroesComeFirst.sort


class ZeroesComeFirstSpec extends Specification {
    private static final ArrayList<Integer> BIG_LIST = //
            [-1, 0, 0, 1, 2, 0, 0, 0, 3, 4, 0, 2, 0, 1, 0, 0, 0, 1, 0, 2, 0, 3, 0, -3, 0, 0, 0]
    private static final ArrayList<Integer> BIG_SORTED_LIST = //
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 2, 3, 4, 2, 1, 1, 2, 3, -3]

    @Unroll
    def "given a list of integers, returns a new list with all zeroes at the beginning (#list -> #expected)"() {
        expect:
        sort(list) == expected

        where:
        list      | expected
        null      | []
        [3]       | [3]
        [0]       | [0]
        []        | []
        [0, 0]    | [0, 0]
        [0, 1]    | [0, 1]
        [0, 2]    | [0, 2]
        [1, 1]    | [1, 1]
        [2, 1, 0] | [0, 2, 1]
        BIG_LIST  | BIG_SORTED_LIST
    }
}