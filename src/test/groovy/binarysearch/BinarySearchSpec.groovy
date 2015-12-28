package binarysearch

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class BinarySearchSpec extends Specification {
    private final static List<Integer> BIG_LIST = [-2, -1, -1, 0, 0, 1, 2, 3, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 9, 10]

    def "searchs over sorted list of integers (#list) for a number (#number) and returns its index (#expectedIndex) or -1"() {
        given:
        def binarySearch = Spy(BinarySearch)

        when:
        def index = binarySearch.search(list, number)

        then:
        index == expectedIndex
        (0..maxInvocations) * binarySearch.search(_, _, _, *_)

        where:
        list                     | number | expectedIndex | maxInvocations
        null                     | 0      | -1            | 0
        [1]                      | 1      | 0             | 1
        []                       | 0      | -1            | 0
        [1]                      | 2      | -1            | 1
        [1, 2]                   | 2      | 1             | 1
        [1, 2]                   | 1      | 0             | 2
        [1, 2]                   | 0      | -1            | 2
        [1, 2, 3]                | 3      | 2             | 2
        [1, 2, 3, 4, 5, 6]       | 3      | 2             | 3
        [1, 2, 3, 4, 5, 6, 7, 8] | 6      | 5             | 3
        BIG_LIST                 | 6      | 12            | 3
    }
}