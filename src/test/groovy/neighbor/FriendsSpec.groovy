package neighbor

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class FriendsSpec extends Specification {
    def "find friends close to given"() {
        given:
        def neighbor = new Neighbor(position: 10, name: "me")

        when:
        def friends = Friends.lookForFriends(neighborhood, neighbor)

        then:
        friends.size() == expected.size()
        friends.containsAll(expected)

        where:
        neighborhood << [
                null,
                [
                        new Neighbor(position: 2, name: "hello!"),
                ],
                [
                        new Neighbor(position: 10, name: "me"),
                        new Neighbor(position: 2, name: "hello!"),
                ],
                [
                        new Neighbor(position: 10, name: "me"),
                        new Neighbor(position: 9, name: "friend"),
                        new Neighbor(position: 8, name: "friend"),
                        new Neighbor(position: 7, name: "friend"),
                        new Neighbor(position: 6, name: "NOT A FRIEND"),
                ],
                [
                        new Neighbor(position: 10, name: "me"),
                        new Neighbor(position: 11, name: "friend"),
                        new Neighbor(position: 12, name: "friend"),
                        new Neighbor(position: 13, name: "friend"),
                        new Neighbor(position: 14, name: "NOT A FRIEND"),
                ],
                [
                        new Neighbor(position: 10, name: "me"),
                        new Neighbor(position: 9, name: "friend"),
                        new Neighbor(position: 8, name: "friend"),
                        new Neighbor(position: 7, name: "friend"),
                        new Neighbor(position: 6, name: "NOT A FRIEND"),
                        new Neighbor(position: 11, name: "friend"),
                        new Neighbor(position: 12, name: "friend"),
                        new Neighbor(position: 13, name: "friend"),
                        new Neighbor(position: 14, name: "NOT A FRIEND"),
                ],
        ]
        expected << [
                [],
                [
                        new Neighbor(position: 2, name: "hello!"),
                ],
                [
                        new Neighbor(position: 2, name: "hello!"),
                ],
                [
                        new Neighbor(position: 9, name: "friend"),
                        new Neighbor(position: 8, name: "friend"),
                        new Neighbor(position: 7, name: "friend"),
                ],
                [
                        new Neighbor(position: 11, name: "friend"),
                        new Neighbor(position: 12, name: "friend"),
                        new Neighbor(position: 13, name: "friend"),
                ],
                [
                        new Neighbor(position: 9, name: "friend"),
                        new Neighbor(position: 8, name: "friend"),
                        new Neighbor(position: 7, name: "friend"),
                        new Neighbor(position: 11, name: "friend"),
                        new Neighbor(position: 12, name: "friend"),
                        new Neighbor(position: 13, name: "friend"),
                ],
        ]
    }
}