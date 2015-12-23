package mergesort

import spock.lang.Specification


class MergeSortSpec extends Specification {
    def "MergeSort.sort(List<Integer>) returns a new List<Integer> with elements sorted"() {
        expect: "e.g. MergeSort.sort([3, 2, 1]) returning [1, 2, 3]"
        MergeSort.sort(numbers) == expected

        where:
        numbers      | expected
        null         | []
        [1]          | [1]
        [2]          | [2]
        []           | []
        [1, 0]       | [0, 1]
        [2, 1]       | [1, 2]
        [0, 1]       | [0, 1]
        [1, 3, 2, 4] | [1, 2, 3, 4]
        [3, 2, 1]    | [1, 2, 3]
        [4, 3, 2, 1] | [1, 2, 3, 4]
    }
}