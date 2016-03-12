package subarraysum

import spock.lang.Specification


class SubArraySumSpec extends Specification {
    def "can handle hard scenarios"() {
        given: "hard input"
            System.setIn(getClass().getResourceAsStream("input.txt"))

        and: "we redirect output"
            def out = new ByteArrayOutputStream()
            System.setOut(new PrintStream(out))

        when: "main method is invoked"
            SumArraySum.main()

        then: "output is as expected"
            getClass().getResourceAsStream("expected-output.txt").text == out as String
    }
}