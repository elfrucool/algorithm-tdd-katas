package mergesort

import spock.lang.Specification
import spock.lang.Unroll

import static mergesort.MergeSort.sort


class MergeSortSpec extends Specification {
    @Unroll
    def "given a list of numbers it returns a new list with the sorted numbers [#numbers -> #expected]"() {
        expect:
        sort(numbers) == expected

        where:
        numbers      | expected
        null         | []
        [3]          | [3]
        [0]          | [0]
        []           | []
        [1, 0]       | [0, 1]
        [2, 1]       | [1, 2]
        [0, 1]       | [0, 1]
        [2, 1, 3]    | [1, 2, 3]
        [3, 2, 1]    | [1, 2, 3]
        [4, 3, 1, 2] | [1, 2, 3, 4]
    }

    def "sorted list always have its next element bigger or equal to the current"() {
        given: "a big list of random integers"
        def random = new Random(new Date().getTime())
        def bigList = (0..1000000).collect { random.nextInt() }

        when: "sort() is invoked"
        def sorted = sort(bigList)

        then: "for each number in the returned list, the next number is greater or equal to the current number"
        for (int i = 0; i < sorted.size() - 1; i++) {
            assert sorted[i] <= sorted[i + 1]
        }
    }
}