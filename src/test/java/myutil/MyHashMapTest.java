package myutil;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class MyHashMapTest {
    // PLAN
    // ====
    // 4. support resize
    // 3. support hashCode() collision : FB <=> Ea : will use LinkedList internally
    // 2. support more than one key-value
    // 1. implement basic methods with none/single key-value: isEmpty(), put(), containsKey(), get(), remove()

    private MyMap<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new MyHashMap<>();
    }

    public class EmptyMap {
        @Test
        public void newMapIsEmpty() {
            assertTrue(map.isEmpty());
        }

        @Test
        public void notContainKeys() {
            assertFalse(map.containsKey("key"));
        }

        @Test
        public void whenGetReturnNull() {
            assertNull(map.get("key"));
        }
    }

    public class SingleEntryMap {
        @Before
        public void setUp() {
            map.put("key", 1);
        }

        @Test
        public void afterPutMapIsNotEmpty() {
            assertFalse(map.isEmpty());
        }

        @Test
        public void afterPutContainsThePutKey() {
            assertTrue(map.containsKey("key"));
        }
    }
}
