package binarysearch

import spock.lang.Specification
import spock.lang.Unroll


class BinarySearchSpec extends Specification {
    public static final BIG_LIST = [-1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10]

    @Unroll
    def "binary search takes a sorted list of numbers (#list) and a number (#number) and returns the index of the given index"() {
        given: "a BinarySearch object"
        def binarySearch = Spy(BinarySearch)

        when: "search() method is invoked over a sorted list of numbers looking for a specific number"
        int index = binarySearch.search(list, number)

        then: "returns the index of the selected number or -1 if not found"
        index == expectedIndex
        (0..maxInvocations) * binarySearch.search(_, _, _, *_)

        where:
        list                  | number | expectedIndex | maxInvocations
        null                  | 0      | -1            | 0
        [1]                   | 1      | 0             | 1
        []                    | 1      | -1            | 0
        [0]                   | 1      | -1            | 1
        [0, 1]                | 1      | 1             | 1
        [0, 1]                | 0      | 0             | 2
        [1, 2, 3]             | 3      | 2             | 2
        [1, 2, 3]             | 0      | -1            | 2
        [1, 2, 3, 4, 5, 6]    | 3      | 2             | 3
        [1, 2, 3, 4, 5, 6, 7] | 5      | 4             | 3
        BIG_LIST              | 7      | 11            | 3
    }
}