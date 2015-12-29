package binarysearch

import spock.lang.Specification
import spock.lang.Unroll


class BinarySearchSpec extends Specification {
    private static final List<Integer> BIG_LIST = [-1, 1, 1, 2, 5, 7, 8, 8, 8, 9, 9, 10, 11, 12]

    @Unroll
    def "given #list, and #number, it returns #expectedIndex in less than #maxInvocations --> O(n*log(n))"() {
        given:
        def binarySearch = Spy(BinarySearch)

        when:
        def index = binarySearch.search(list, number)

        then:
        index == expectedIndex
        (0..maxInvocations) * binarySearch.search(_, _, _, *_)

        where:
        list               | number | expectedIndex | maxInvocations
        null               | 0      | -1            | 0
        [1]                | 1      | 0             | 1
        []                 | 0      | -1            | 0
        [0]                | 1      | -1            | 1
        [1, 2]             | 1      | 0             | 2
        [1]                | 0      | -1            | 1
        [1, 2, 3]          | 3      | 2             | 2
        [1, 2, 3, 4, 5, 6] | 3      | 2             | 3
        BIG_LIST           | 8      | 7             | 3
    }
}