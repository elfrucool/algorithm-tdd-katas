package subarraysum

import spock.lang.Specification


class SubArraySumSpec extends Specification {
    def "passes all"() {
        given: "input/output redirected"
        System.setIn(getClass().getResourceAsStream("input.txt"))

        def out = new ByteArrayOutputStream()
        System.setOut(new PrintStream(out))

        when: "main is invoked"
        SubArraySumX.main()

        then: "output should be equal to expected"
        getClass().getResourceAsStream("expected-output.txt").getText() == out.toString()
    }
}