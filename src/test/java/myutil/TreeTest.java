package myutil;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

// PLAN
// ====
// 0. does not allow repetitions
// 1. node with basic operations:
//      [ok] - insert
//      - remove
//      [ok] - find
//      [ok] - getSmallest
//      [ok] - getBiggest
//      [ok] - getRoot
// 2. iterable/iterator: traversing
// 3. balancing
@RunWith(HierarchicalContextRunner.class)
public class TreeTest {
    private <K extends Comparable<K>, V> void assignPropertiesUsingSetters(
            MyEntry<K, V> entry, K key, V value, MyEntry<K, V> parent) //
    {
        entry.setKey(key);
        entry.setValue(value);
        entry.setParent(parent);
    }

    private static <K extends Comparable<K>, V> void assertEntryHasProperties(
            MyEntry<K, V> entry, K expectedKey, V expectedValue, MyEntry<K, V> expectedParent) //
    {
        assertEquals(expectedKey, entry.getKey());
        assertEquals(expectedValue, entry.getValue());
        assertSame(expectedParent, entry.getParent());
    }

    private static <K extends Comparable<K>, V> void assertEntryIsAlone(MyEntry<K, V> entry) {
        assertNull(entry.getLeft());
        assertNull(entry.getRight());
        assertNull(entry.getParent());
    }

    private static <K extends Comparable<K>, V> void assertDelete(MyEntry<K, V> entry, MyEntry<K, V> expectedRoot) {
        assertSame(expectedRoot, entry.remove());
        assertEntryIsAlone(entry);
        assertNull(expectedRoot.getParent());
    }

    protected static final MyEntry<String, String> NULL_PARENT = null;

    private MyEntry<String, String> entry;
    @Before
    public void setUp() throws Exception {
        entry = new MyEntry<>();
    }

    public class PropertiesAndInstantiation {
        @Test
        public void hasKeyValueAndParent() {
            MyEntry<String, String> parent = new MyEntry<>();
            assignPropertiesUsingSetters(entry, "key", "value", parent);
            assertEntryHasProperties(entry, "key", "value", parent);
        }

        @Test
        public void canSetKeyAndValueUsingConstructor() {
            assertEntryHasProperties( //
                    new MyEntry<>("key", "value"), //
                    "key", "value", NULL_PARENT);
        }

        @Test
        public void canSetKeyValueUsingConstructor() {
            assertEntryHasProperties( //
                    new MyEntry<>("key", "value"), //
                    "key", "value", NULL_PARENT);
        }

        @Test
        public void hasLeftAndRight() {
            MyEntry<String, String> left = new MyEntry<>();
            MyEntry<String, String> right = new MyEntry<>();

            entry.setLeft(left);
            entry.setRight(right);

            assertSame(left, entry.getLeft());
            assertSame(right, entry.getRight());
        }
    }

    public class SingleTransformationsWithoutSorting {
        @Before
        public void setUp() {
            assignPropertiesUsingSetters(entry, "2", "two", NULL_PARENT);
        }

        @Test
        public void canInsertLeft() {
            MyEntry<String, String> left = new MyEntry<>("1", "one");
            entry.insertLeft(left);

            assertSame(left, entry.getLeft());
            assertSame(entry, left.getParent());
        }

        @Test
        public void canInsertRight() {
            MyEntry<String, String> right = new MyEntry<>("3", "three");
            entry.insertRight(right);

            assertSame(right, entry.getRight());
            assertSame(entry, right.getParent());
        }

        @Test
        public void canRemoveLeft() {
            MyEntry<String, String> left = new MyEntry<>("1", "one");
            entry.insertLeft(left);
            assertSame(left, entry.removeLeft());

            assertNull(entry.getLeft());
            assertNull(left.getParent());
        }

        @Test
        public void canRemoveRight() {
            MyEntry<String, String> right = new MyEntry<>("3", "three");
            entry.insertRight(right);
            assertSame(right, entry.removeRight());

            assertNull(entry.getRight());
            assertNull(right.getParent());
        }
    }

    public class BasicOperationsWithSorting {
        private MyEntry<String, String> left = new MyEntry<>("1", "left");
        private MyEntry<String, String> right = new MyEntry<>("3", "right");

        private void addTwoChildrenToRootEntry() {
            entry.insertLeft(left);
            entry.insertRight(right);
        }

        @Before
        public void setUp() {
            assignPropertiesUsingSetters(entry, "2", "two", NULL_PARENT);
        }

        public class GivenEntryWithoutChildren {
            @Test
            public void insertNullIgnoresResult() {
                entry.insert(null);
                assertEntryHasProperties(entry, "2", "two", NULL_PARENT);
                assertEntryIsAlone(entry);
            }

            @Test
            public void insertingEntryWithSameKeyUpdatesValue() {
                MyEntry<String, String> shouldBeSame = new MyEntry<>("2", "should be same");

                entry.insert(shouldBeSame);

                assertEquals("should be same", entry.getValue());
                assertEntryIsAlone(entry);
                assertEntryIsAlone(shouldBeSame);
            }

            @Test
            public void canInsertLeftSmallerKeyEntries() {
                MyEntry<String, String> shouldBeLeft = new MyEntry<>("1", "left");
                entry.insert(shouldBeLeft);

                assertSame(shouldBeLeft, entry.getLeft());
                assertSame(entry, shouldBeLeft.getParent());
            }

            @Test
            public void canInsertRightBiggerKeyEntries() {
                MyEntry<String, String> shouldBeRight = new MyEntry<>("3", "right");
                entry.insert(shouldBeRight);

                assertSame(shouldBeRight, entry.getRight());
                assertSame(entry, shouldBeRight.getParent());
            }
        }

        public class GivenEntryWithTwoChildren {
            @Before
            public void setUp() {
                addTwoChildrenToRootEntry();
            }

            @Test
            public void canInsertInLeftSmallerKeyEntries() {
                MyEntry<String, String> shouldBeLeft = new MyEntry<>("0.1", "left-left");
                entry.insert(shouldBeLeft);

                assertSame(shouldBeLeft, entry.getLeft().getLeft());
                assertSame(entry.getLeft(), shouldBeLeft.getParent());
            }

            @Test
            public void canInsertInRightBiggerKeyEntries() {
                MyEntry<String, String> shouldBeRight = new MyEntry<>("3.1", "right-right");
                entry.insert(shouldBeRight);

                assertSame(shouldBeRight, entry.getRight().getRight());
                assertSame(entry.getRight(), shouldBeRight.getParent());
            }

            @Test
            public void insertionsAreRecursivelySorted() {
                MyEntry<String, String> leftRight = new MyEntry<>("1.1", "left-right");
                MyEntry<String, String> rightLeft = new MyEntry<>("2.1", "right-left");

                entry.insert(leftRight);
                entry.insert(rightLeft);

                assertSame(leftRight, entry.getLeft().getRight());
                assertSame(entry.getLeft(), leftRight.getParent());

                assertSame(rightLeft, entry.getRight().getLeft());
                assertSame(entry.getRight(), rightLeft.getParent());
            }
        }

        public class GivenEntryWithThreeLevels {
            private MyEntry<String, String> makeUpdatedEntryWithChildren() {
                MyEntry<String, String> updatedEntry = new MyEntry<>("2", "updated value");
                MyEntry<String, String> updatedLeft = new MyEntry<>("0.0", "new smallest is updated left");
                MyEntry<String, String> updatedRight = new MyEntry<>("4", "new biggest is updated right");

                updatedEntry.insert(updatedLeft);
                updatedEntry.insert(updatedRight);
                return updatedEntry;
            }

            private MyEntry<String, String> leftLeft;
            private MyEntry<String, String> leftRight;
            private MyEntry<String, String> rightLeft;
            private MyEntry<String, String> rightRight;

            @Before
            public void setUp() {
                addTwoChildrenToRootEntry();

                leftLeft = new MyEntry<>("0.1", "left-left");  // "0" works but for just following the same convention
                leftRight = new MyEntry<>("1.1", "left-right");
                rightLeft = new MyEntry<>("2.1", "right-left");
                rightRight = new MyEntry<>("3.1", "right-right");

                for (MyEntry<String, String> e : Arrays.asList(leftLeft, leftRight, rightLeft, rightRight))
                    entry.insert(e);
            }

            @Test
            public void canGetRoot() {
                assertSame(entry, entry.getRoot());
                assertSame(entry, left.getRoot());
                assertSame(entry, leftLeft.getRoot());
            }

            @Test
            public void canGetSmallest() {
                assertSame(leftLeft, entry.getSmallest());
            }

            @Test
            public void canGetBiggest() {
                assertSame(rightRight, entry.getBiggest());
            }

            @Test
            public void canFindNode() {
                assertSame(entry, entry.find("2"));

                assertSame(left, entry.find("1"));
                assertSame(right, entry.find("3"));

                assertSame(leftLeft, entry.find("0.1"));
                assertSame(rightRight, entry.find("3.1"));
                assertSame(leftRight, entry.find("1.1"));
                assertSame(rightLeft, entry.find("2.1"));

                assertNull(entry.find("1.1.1"));
                assertNull(entry.find("foobar"));
            }

            @Test
            public void insertEntyWithSameKeyUpdatesValueAndInsertsChildren() {
                MyEntry<String, String> updatedEntry = makeUpdatedEntryWithChildren();
                MyEntry<String, String> updatedLeft = updatedEntry.getLeft();

                entry.insert(updatedEntry);

                assertEquals("updated value", entry.getValue());
                assertEquals("0.0", entry.getSmallest().getKey());
                assertEquals("4", entry.getBiggest().getKey());
                assertSame(entry, updatedLeft.getRoot());
            }
        }

        public class RemoveScenarios {
            @Test
            public void removeAloneEntryReturnsNull() {
                assertNull(entry.remove());
                assertEntryIsAlone(entry);
            }

            public class GivenEntryWithSingleChild {
                @Test
                public void ifOnlyLeftIsSetWhenRemovingEntryLeftBecomesNewInPlaceEntry() {
                    entry.insert(left);
                    assertDelete(entry, left);
                }

                @Test
                public void ifOnlyRightIsSetWhenRemovingEntryLeftBecomesNewInPlaceEntry() {
                    entry.insert(right);
                    assertDelete(entry, right);
                }
            }

            public class GivenEntryWithTwoChildrenForRemove {
                @Before
                public void setUp() {
                    addTwoChildrenToRootEntry();
                }

                @Test
                public void removingLeftLeafMakesItAloneRemovesFromParentAndReturnsNewInPlaceEntry() {
                    assertSame(entry, left.remove());
                    assertEntryIsAlone(left);
                    assertNull(entry.getLeft());
                }

                @Test
                public void removingRightLeafMakesItAloneRemovesFromParentAndReturnsNewInPlaceEntry() {
                    assertSame(entry, right.remove());
                    assertEntryIsAlone(right);
                    assertNull(entry.getRight());
                }

                @Test
                public void removingRootMakesLeftNewRootAndRightChildOfLeft() {
                    assertSame(left, entry.remove());
                    assertEntryIsAlone(entry);
                    assertSame(right, left.getRight());
                }

            }

            public class RemovingInThreeLevels {

                private MyEntry<String, String> leftLeft = new MyEntry<>("0.1", "leftLeft");
                private MyEntry<String, String> leftRight = new MyEntry<>("1.1", "leftRight");
                private MyEntry<String, String> rightLeft = new MyEntry<>("2.1", "rightLeft");
                private MyEntry<String, String> rightRight = new MyEntry<>("3.1", "rightRight");

                @Before
                public void setUp() {
                    addTwoChildrenToRootEntry();

                    for (MyEntry<String, String> e : Arrays.asList(leftLeft, leftRight, rightLeft, rightRight))
                        entry.insert(e);
                }

                @Test
                public void removingRoot() {
                    assertSame(left, entry.remove());
                    assertEntryIsAlone(entry);
                    assertNull(left.getParent());
                    assertSame(left.getRight(), right.getParent());
                }
            }
        }
    }
}
