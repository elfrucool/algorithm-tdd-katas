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
    // [ok] 1. implement basic methods with none/single key-value:
    //          isEmpty(), put(), size(), containsKey(), get(), remove()
    // [ok] 2. support more than one key-value
    // [ok] 3. support hashCode() collision : FB <=> Ea : will use LinkedList internally
    // 4. support resize

    private static void assertEntry(MyMap<String, Integer> map, String key, Integer value) {
        assertTrue(map.containsKey(key));
        assertEquals(value, map.get(key));
    }

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

        @Test
        public void itsSizeIsZero() {
            assertEquals(0, map.size());
        }
    }

    public class SingleEntryMap {
        @Before
        public void setUp() {
            map.put("key", 1);
        }

        @Test
        public void mapIsNotEmpty() {
            assertFalse(map.isEmpty());
        }

        @Test
        public void itsSizeIsOne() {
            assertEquals(1, map.size());
        }

        @Test
        public void containsTheAlreadyPutKey() {
            assertTrue(map.containsKey("key"));
        }

        @Test
        public void canGetTheValueUsingKey() {
            assertEquals((Integer) 1, map.get("key"));
        }

        @Test
        public void notContainsAnotherKeys() {
            assertFalse(map.containsKey("another"));
        }

        @Test
        public void ifKeyNotFoundReturnsNull() {
            assertNull(map.get("another"));
        }

        @Test
        public void canUpdateValueUsingSameKey() {
            map.put("key", 123);
            assertEquals((Integer) 123, map.get("key"));
            assertEquals(1, map.size());
        }

        public class RemoveSupport {
            @Test
            public void removeReturnsTheValue() {
                assertEquals((Integer) 1, map.remove("key"));
            }

            @Test
            public void removeMakesMapEmpty() {
                map.remove("key");
                assertTrue(map.isEmpty());
            }

            @Test
            public void removeMakesKeyUnreachable() {
                map.remove("key");
                assertFalse(map.containsKey("key"));
            }

            @Test
            public void removeMakesSizeZero() {
                map.remove("key");
                assertEquals(0, map.size());
            }

            @Test
            public void removeNonExistentKeyHasNoSideEffects() {
                assertNull(map.remove("another"));
                assertFalse(map.isEmpty());
                assertEntry(map, "key", 1);
                assertEquals(1, map.size());
            }
        }
    }

    public class NullSupport {
        @Test
        public void emptyMapDoesNotContainNullKey() {
            assertFalse(map.containsKey(null));
        }

        @Test
        public void nullValuesAreSupported() {
            map.put("key", null);
            assertFalse(map.isEmpty());
            assertTrue(map.containsKey("key"));
            assertNull(map.get("key"));
        }

        @Test
        public void nullKeysAreSupported() {
            map.put(null, 1);
            assertEntry(map, null, 1);
        }
    }

    public class MoreThanOneEntry {
        public class WithoutCollision {
            @Before
            public void setUp() {
                map.put("key1", 10);
                map.put("key2", 20);
            }

            @Test
            public void itsSizeIsTheNumberOfStoredKeys() {
                assertEquals(2, map.size());
            }

            @Test
            public void canCheckExistenceOfBothKeys() {
                assertTrue(map.containsKey("key1"));
                assertTrue(map.containsKey("key2"));
            }

            @Test
            public void removingSingleEntryDecreasesSizeAndRemovesOnlyThatEntry() {
                assertEquals((Integer) 10, map.remove("key1"));
                assertEquals(1, map.size());
                assertFalse(map.containsKey("key1"));
                assertNull(map.get("key1"));
                assertEntry(map, "key2", 20);
            }
        }

        public class CollisionSupport {
            @Test
            public void containsKeyIsCollisionAware() {
                map.put("FB", 1);
                assertFalse(map.containsKey("Ea"));
                assertTrue(map.containsKey("FB"));
            }

            @Test
            public void getIsCollisionAware() {
                map.put("FB", 1);
                assertEquals((Integer) 1, map.get("FB"));
                assertNull(map.get("Ea"));
            }

            @Test
            public void canStoreTwoEntriesWithCollision() {
                map.put("FB", 1);
                map.put("Ea", 2);
                assertEntry(map, "FB", 1);
                assertEntry(map, "Ea", 2);
            }
        }
    }
}
