package binarysearch

import spock.lang.Specification
import spock.lang.Unroll


class BinarySearchSpec extends Specification {
    @Unroll
    def "search(#number, #list) should return #expectedIndex in #expectedCalls call(s) or less"() {
        given:
        def binarySearch = Spy(BinarySearch)

        when:
        def index = binarySearch.search(number, list)

        then:
        index == expectedIndex
        (_..expectedCalls) * binarySearch.search(*_)

        where:
        number | list               | expectedIndex | expectedCalls
        0      | null               | -1            | 1
        0      | [0]                | 0             | 2
        0      | []                 | -1            | 1
        0      | [1]                | -1            | 2
        1      | [0]                | -1            | 2
        1      | [0, 1]             | 1             | 2
        0      | [0, 1]             | 0             | 3
        2      | [0, 1, 2]          | 2             | 3
        2      | [0, 1, 2, 3, 4, 5] | 2             | 4
        5      | [0, 1, 2, 3, 4, 5] | 5             | 4
    }
}