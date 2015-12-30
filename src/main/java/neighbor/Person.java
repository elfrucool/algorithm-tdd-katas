package neighbor;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private int position;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return position == person.position &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name);
    }

    @Override
    public String toString() {
        return "Person{" + "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        int compare = Integer.compare(position, other.position);
        if (compare == 0)
            compare = name.compareTo(other.name);
        return compare;
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
