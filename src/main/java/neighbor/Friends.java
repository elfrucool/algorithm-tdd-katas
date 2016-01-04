package neighbor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class Friends {
    public static List<Neighbor> lookForFriends(List<Neighbor> neighborhood, Neighbor neighbor) {
        if (neighborhood == null)
            return emptyList();

        List<Neighbor> friends = makeFriendsList(neighborhood);

        int indexOfNeighbor = binarySearch(friends, neighbor);

        int max = indexOfNeighbor + 3 + 1;
        if (max > friends.size())
            max = friends.size();

        return friendsWithoutNeighbor(neighbor, friends.subList(getMinIndex(indexOfNeighbor), max));
    }

    protected static int getMinIndex(int indexOfNeighbor) {
        int min = indexOfNeighbor - 3;
        if (min < 0)
            min = 0;
        return min;
    }

    protected static List<Neighbor> friendsWithoutNeighbor(Neighbor neighbor, List<Neighbor> friends) {
        friends.remove(neighbor);
        return friends;
    }

    protected static List<Neighbor> makeFriendsList(List<Neighbor> neighborhood) {
        List<Neighbor> friends = new ArrayList<>();
        friends.addAll(neighborhood);
        sort(friends);
        return friends;
    }
}
