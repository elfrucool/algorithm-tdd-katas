package binarysearch

import spock.lang.Specification
import spock.lang.Unroll


class BinarySearchSpec extends Specification {
    private static final List<Integer> BIG_LIST = [-1, 0, 1, 2, 2, 3, 4, 5, 5, 6, 7, 9, 10, 11, 11, 12, 14, 15]

    @Unroll
    def "binarySearch.search(#list,#number) should be #expectedIndex"() {
        given: "Binary Search object"
        def binarySearch = Spy(BinarySearch)

        when: "search() method is invoked over a sorted list of numbers and with a given number"
        def index = binarySearch.search(list, number)

        then: "index of the number is returned or -1 if the number does not belong to the list"
        index == expectedIndex
        (0..maxInvocations) * binarySearch.search(_, _, _, *_)

        where:
        list               | number | expectedIndex | maxInvocations
        null               | 0      | -1            | 0
        [0]                | 0      | 0             | 1
        []                 | 0      | -1            | 0
        [0]                | 1      | -1            | 1
        [0, 1]             | 1      | 1             | 1
        [0, 1]             | 0      | 0             | 2
        [1]                | 0      | -1            | 1
        [1, 2, 3]          | 3      | 2             | 2
        [1, 2, 3, 4, 5, 6] | 3      | 2             | 3
        BIG_LIST           | 11     | 13            | 3
    }

}