package neighbor

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class FindFriendsSpec extends Specification {
    def "looks for friends of a person"() {
        given:
        def person = new Person(position: 10, name: "I am")

        expect:
        Friends.findFriends(neighborhood, person).containsAll(expected)

        where:
        expected << [
                [],

                [],

                [new Person(position: 10, name: "you are my friend")],

                [
                        new Person(position: 7, name: "you are my friend"),
                        new Person(position: 8, name: "you are my friend"),
                        new Person(position: 9, name: "you are my friend"),
                        new Person(position: 10, name: "you are my friend"),
                        new Person(position: 11, name: "you are my friend"),
                ],

                [
                        new Person(position: 7, name: "you are my friend"),
                        new Person(position: 8, name: "you are my friend"),
                        new Person(position: 9, name: "you are my friend"),
                        new Person(position: 11, name: "you are my friend"),
                        new Person(position: 12, name: "you are my friend"),
                        new Person(position: 13, name: "you are my friend"),
                ],
        ]

        neighborhood << [
                null,

                [],

                [new Person(position: 10, name: "I am"),
                 new Person(position: 10, name: "you are my friend")],

                [new Person(position: 10, name: "I am"),
                 new Person(position: 10, name: "you are my friend"),
                 new Person(position: 11, name: "you are my friend"),
                 new Person(position: 12, name: "you are my friend"),
                 new Person(position: 8, name: "you are my friend"),
                 new Person(position: 7, name: "you are my friend"),
                 new Person(position: 9, name: "you are my friend"),
                 new Person(position: 6, name: "you are NOT my friend"),
                 new Person(position: 13, name: "you are NOT my friend"),
                ],

                [new Person(position: 10, name: "I am"),
                 new Person(position: 11, name: "you are my friend"),
                 new Person(position: 12, name: "you are my friend"),
                 new Person(position: 8, name: "you are my friend"),
                 new Person(position: 7, name: "you are my friend"),
                 new Person(position: 9, name: "you are my friend"),
                 new Person(position: 13, name: "you are my friend"),
                 new Person(position: 6, name: "you are NOT my friend"),
                 new Person(position: 14, name: "you are NOT my friend"),
                ],
        ]
    }
}