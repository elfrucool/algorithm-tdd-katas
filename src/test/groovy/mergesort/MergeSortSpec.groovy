package mergesort

import spock.lang.Specification
import spock.lang.Unroll


class MergeSortSpec extends Specification {
    @Unroll
    def "MergeSort.sort(#numbers) returns a new list with its elements sorted #expected"() {
        expect: "e.g. MergeSort.sort([3, 2, 1]) returning [1, 2, 3]"
        MergeSort.sort(numbers) == expected

        where:
        numbers                       | expected
        null                          | []
        [1]                           | [1]
        [2]                           | [2]
        []                            | []
        [1, 0]                        | [0, 1]
        [2, 1]                        | [1, 2]
        [0, 1]                        | [0, 1]
        [1, 3, 2, 4]                  | [1, 2, 3, 4]
        [3, 2, 1]                     | [1, 2, 3]
        [4, 3, 2, 1]                  | [1, 2, 3, 4]
        [2, 5, 2, 6, 1, -2, 0, 44, 0] | [-2, 0, 0, 1, 2, 2, 5, 6, 44]
    }
}