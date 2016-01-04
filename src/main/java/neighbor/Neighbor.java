package neighbor;

import java.util.Objects;

public class Neighbor implements Comparable<Neighbor> {
    private int position;
    private String name;

    public Neighbor() {
    }

    public Neighbor(int position, String name) {
        this.position = position;
        this.name = name;
    }

    @Override
    public int compareTo(Neighbor other) {
        int compare = 1;
        if (other != null) {
            compare = Integer.compare(position, other.position);
            if (compare == 0) {
                compare = (name != null
                        ? other.name != null ? name.compareTo(other.name) : 1
                        : other.name == null ? 0 : -1);
            }
        }
        return Integer.signum(compare);
    }

    @Override
    public String toString() {
        return "Neighbor{" + "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Neighbor)) return false;
        Neighbor neighbor = (Neighbor) o;
        return position == neighbor.position &&
                Objects.equals(name, neighbor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
