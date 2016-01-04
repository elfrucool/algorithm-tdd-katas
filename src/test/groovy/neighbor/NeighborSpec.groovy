package neighbor

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class NeighborSpec extends Specification {
    def "Neighbor has position & name"() {
        when:
        def neighbor = new Neighbor(position: 10, name: "the name")

        then:
        neighbor.position == 10
        neighbor.name == "the name"
    }

    def "Neighbor has string representation"() {
        expect:
        new Neighbor(position: 1, name: "my name").toString() == "Neighbor{position=1, name='my name'}"
    }

    def "has equals()/hashCode()"() {
        when:
        def neighbor = new Neighbor(position: 5, name: "this is my name")

        then:
        neighbor.equals(neighbor2) == expectedEquals
        !expectedEquals || neighbor.hashCode() == neighbor2.hashCode()

        where:
        neighbor2                                          | expectedEquals
        new Neighbor(position: 5, name: "this is my name") | true
        new Neighbor(position: 2, name: "this is my name") | false
        new Neighbor(position: 5, name: "not my name")     | false
    }

    def "is comparable first by position, then by name"() {
        when:
        def neighbor = new Neighbor(position: 5, name: "me")

        then:
        neighbor.compareTo(neighbor2) == expected
        !neighbor2 || neighbor2?.compareTo(neighbor) == -expected

        where:
        neighbor2                                          | expected
        new Neighbor(position: 0, name: "ya, i'm smaller") | 1
        new Neighbor(position: 5, name: "me")              | 0
        new Neighbor(position: 5, name: "before me")       | 1
        new Neighbor(position: 10, name: "bigger")         | -1
        new Neighbor(position: 5, name: "subtle bigger")   | -1
        null                                               | 1
        new Neighbor(position: 5, name: null)              | 1
    }
}