package myutil;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

// PLAN
// ====
// 0. does not allow repetitions
// 1. node with basic operations:
//      [ok] - insert
//      [ok] - remove
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
        assertHasNoChildren(entry);
        assertNull(entry.getParent());
    }

    private static <K extends Comparable<K>, V> void assertHasNoChildren(MyEntry<K, V> entry) {
        assertNull(entry.getLeft());
        assertNull(entry.getRight());
    }

    private static <K extends Comparable<K>, V> void assertDeleteSingleChild(
            MyEntry<K, V> entry, MyEntry<K, V> expectedReplacement) //
    {
        assertSame(expectedReplacement, entry.remove());
        assertEntryIsAlone(entry);
        assertNull(expectedReplacement.getParent());
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

    public class SingleOperationsWithoutSorting {
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

        @Test
        public void canIdentifyTheTypeOfRelationWithParent() {
            MyEntry<String, String> left = new MyEntry<>("1", "left");
            MyEntry<String, String> right = new MyEntry<>("3", "right");

            entry.insertLeft(left);
            entry.insertRight(right);

            assertEquals(MyEntry.ChildType.NONE, entry.getChildType());
            assertEquals(MyEntry.ChildType.LEFT, left.getChildType());
            assertEquals(MyEntry.ChildType.RIGHT, right.getChildType());
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
                    assertDeleteSingleChild(entry, left);
                }

                @Test
                public void ifOnlyRightIsSetWhenRemovingEntryLeftBecomesNewInPlaceEntry() {
                    entry.insert(right);
                    assertDeleteSingleChild(entry, right);
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

            public class RemovingInThreeOrMoreLevels {

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
                    // remove "2"
                    //
                    //      __2__                    _1.1_
                    //     /     \                  /     \
                    //    1       3    ======>     1       3
                    //   / \     / \              /       / \
                    // 0.1 1.1 2.1 3.1          0.1     2.1 3.1
                    assertSame(leftRight, entry.remove());

                    assertEntryIsAlone(entry);
                    assertNull(leftRight.getParent());
                    assertSame(leftRight, right.getParent());
                    assertSame(leftRight, left.getParent());
                }

                @Test
                public void removingLeft() {
                    // remove "1"
                    //
                    //      __2__                     __2__
                    //     /     \                   /     \
                    //    1       3    ======>    0.1       3
                    //   / \     / \                 \     / \
                    // 0.1 1.1 2.1 3.1              1.1  2.1 3.1
                    assertSame(leftLeft, left.remove());

                    assertEntryIsAlone(left);
                    assertSame(entry, leftLeft.getParent());
                    assertSame(leftLeft, entry.getLeft());
                    assertSame(leftRight, leftLeft.getRight());
                }

                @Test
                public void removingRight() {
                    // remove "3"
                    //
                    //      __2__                   __2__
                    //     /     \                 /     \
                    //    1       3    ======>    1      2.1
                    //   / \     / \             / \       \
                    // 0.1 1.1 2.1 3.1         0.1 1.1     3.1
                    assertSame(rightLeft, right.remove());

                    assertEntryIsAlone(right);
                    assertSame(entry, rightLeft.getParent());
                    assertSame(rightLeft, entry.getRight());
                    assertSame(rightRight, rightLeft.getRight());
                }

                @Test
                public void removingLeftWhenHasSingleChild() {
                    // remove "1"
                    //
                    //      __2__                   __2__
                    //     /     \                 /     \
                    //    1       3    ======>   0.1     2.1
                    //   /       / \                       \
                    // 0.1     2.1 3.1                     3.1
                    leftRight.remove();

                    assertSame(leftLeft, left.remove());

                    assertEntryIsAlone(left);
                    assertSame(entry, leftLeft.getParent());
                    assertSame(leftLeft, entry.getLeft());
                    MyEntry<String, String> entry = this.leftLeft;
                    assertHasNoChildren(entry);
                }

                public class RemovingInFourOrMoreLevels {
                    // STRUCTURE
                    //                  ______________2______________
                    //                 /                             \
                    //           _____1_____                     _____3_____
                    //          /           \                   /           \
                    //       0.1             1.1             2.1             3.1
                    //      /   \           /   \           /   \           /   \
                    //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1   2.1.1   3.0.1   3.1.1
                    private MyEntry<String, String> leftLeftLeft = new MyEntry<>("0.0.1", "leftLeftLeft");
                    private MyEntry<String, String> leftLeftRight = new MyEntry<>("0.1.1", "leftLeftRight");
                    private MyEntry<String, String> leftRightLeft = new MyEntry<>("1.0.1", "leftRightLeft");
                    private MyEntry<String, String> leftRightRight = new MyEntry<>("1.1.1", "leftRightRight");
                    private MyEntry<String, String> rightLeftLeft = new MyEntry<>("2.0.1", "rightLeftLeft");
                    private MyEntry<String, String> rightLeftRight = new MyEntry<>("2.1.1", "rightLeftRight");
                    private MyEntry<String, String> rightRightLeft = new MyEntry<>("3.0.1", "rightRightLeft");
                    private MyEntry<String, String> rightRightRight = new MyEntry<>("3.1.1", "rightRightRight");

                    @Before
                    public void setUp() {
                        List<MyEntry<String, String>> children =
                                Arrays.asList(
                                        leftLeftLeft, leftLeftRight, leftRightLeft, leftRightRight,
                                        rightLeftLeft, rightLeftRight, rightRightLeft, rightRightRight);

                        for (MyEntry<String, String> child : children)
                            entry.insert(child);
                    }

                    @Test
                    public void removeRight() {
                        // REMOVE "3": expected
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ___2.1.1___
                        //          /           \                   /           \
                        //       0.1             1.1             2.1             3.1
                        //      /   \           /   \           /               /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1           3.0.1   3.1.1
                        assertSame(rightLeftRight, right.remove());

                        assertEntryIsAlone(right);
                        assertSame(entry, rightLeftRight.getParent());
                        assertSame(rightLeftRight, entry.getRight());
                        assertSame(rightLeft, rightLeftRight.getLeft());
                        assertSame(rightLeftRight, rightLeft.getParent());
                        assertSame(rightLeftRight, rightRight.getParent());
                        assertSame(rightRight, rightLeftRight.getRight());
                        assertNull(rightLeft.getRight());
                    }

                    @Test
                    public void removeRightLeftAfterRemoveRight() {
                        // REMOVE "3"=then=>"2.1.1":expected
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ____2.1____
                        //          /           \                   /           \
                        //       0.1             1.1            2.0.1            3.1
                        //      /   \           /   \                           /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1                   3.0.1   3.1.1
                        right.remove();

                        assertSame(rightLeft, rightLeftRight.remove());

                        assertEntryIsAlone(rightLeftRight);
                        assertHasNoChildren(rightLeftLeft);
                        assertSame(entry, rightLeft.getParent());
                        assertSame(rightLeft, entry.getRight());
                        assertSame(rightLeftLeft, rightLeft.getLeft());
                        assertSame(rightRight, rightLeft.getRight());
                    }
                }
            }
        }
    }
}
