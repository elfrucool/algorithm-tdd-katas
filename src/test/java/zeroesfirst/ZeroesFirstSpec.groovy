package zeroesfirst

import spock.lang.Specification
import spock.lang.Unroll

class ZeroesFirstSpec extends Specification {
    @Unroll
    def "should place zeroes first #scenario. (#numbersList)"() {
        given: "a modifiable array"
        def numbers = numbersList as int[]

        when: "sort method is invoked"
        ZeroesFirst.sort(numbers)

        then: "zeroes inside array should be first"
        expected as int[] == numbers

        where:
        numbersList                            | expected                               | scenario
        []                                     | []                                     | "empty array"
        [1, 0]                                 | [0, 1]                                 | "basic sorting -> fixed values"
        [2, 0]                                 | [0, 2]                                 | "with other values -> copying values"
        [2, 1]                                 | [2, 1]                                 | "no zeroes -> test if zero"
        [2, 1, 0]                              | [0, 2, 1]                              | "more than two elements -> iterating"
        [1]                                    | [1]                                    | "single element -> removing if"
        [1, 2, 0, 0]                           | [0, 0, 1, 2]                           | "more than one zero at the begining"
        [1, 0, 0, 2, 3, 0, 0, -1, 0, -2, 0, 0] | [0, 0, 0, 0, 0, 0, 0, 1, 2, 3, -1, -2] | "big test"
    }

}