package neighbor

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class PersonSpec extends Specification {
    def "can instantiate"() {
        expect:
        new Person() != null
    }

    def "has name and position"() {
        given:
        def person = new Person()

        when:
        person.name = "my name"
        person.position = 10

        then:
        person.name == "my name"
        person.position == 10
    }

    def "has equals/hashCode"() {
        given:
        def person1 = new Person(position: 10, name: "my name")
        def person1HashCode = person1.hashCode()

        when:
        def actualEquals = person1.equals(person2)
        def person2HashCode = person2.hashCode()

        then:
        actualEquals == expectedEquals
        mustHaveSameHashCode &&
                person1HashCode == person2HashCode

        where:
        person2                                         | expectedEquals | mustHaveSameHashCode
        new Person(position: 10, name: "my name")       | true           | true
        new Person(position: 11, name: "my name")       | false          | false
        new Person(position: 10, name: "my other name") | false          | false
        new Person(position: 11, name: "my other name") | false          | false
    }

    def "has toString"() {
        expect:
        new Person(position: 10, name: "my name").toString() == "Person{position=10, name='my name'}"
    }

    def "implements comparable, based on position #person.name"() {
        expect:
        Integer.signum(person.compareTo(new Person(position: 10, name: "equal"))) == expected

        where:
        person                                            | expected
        new Person(position: 10, name: "equal")           | 0
        new Person(position: 10, name: "a smaller name")  | -1
        new Person(position: 10, name: "the bigger name") | 1
        new Person(position: 9, name: "smaller")          | -1
        new Person(position: 11, name: "bigger")          | 1
    }
}