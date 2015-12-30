package neighbor;

import java.util.Collections;
import java.util.List;

public class Friends {
    public static List<Person> findFriends(List<Person> neighborhood, Person person) {
        if (neighborhood == null || neighborhood.isEmpty())
            return Collections.emptyList();

        Collections.sort(neighborhood);
        int personIndex = Collections.binarySearch(neighborhood, person);

        int minIndex = 0 <= personIndex - 3 ? personIndex - 3 : 0;
        int maxIndex = personIndex + 4 < neighborhood.size() ? personIndex + 4 : neighborhood.size();

        List<Person> friends = neighborhood.subList(minIndex, maxIndex);
        friends.remove(Collections.binarySearch(friends, person));
        return friends;
    }
}
