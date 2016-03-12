package subarraysum

import spock.lang.Specification


class SubArraySumSpec extends Specification {
    def "can pass hard test"() {
        given:
        System.setIn(getClass().getResourceAsStream("input.txt"))

        def out = new ByteArrayOutputStream()
        System.setOut(new PrintStream(out))

        def expectedOutput = getClass().getResourceAsStream("expected-output.txt").getText()

        when:
        SubArraySum.main()

        then:
        expectedOutput == out.toString()
    }

}