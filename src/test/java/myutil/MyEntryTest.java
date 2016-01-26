package myutil;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

// PLAN
// ====
// [ok] 0. does not allow repetitions
// [ok] 1. node with basic operations:
//      [ok] - insert
//      [ok] - remove
//      [ok] - find
//      [ok] - getSmallest
//      [ok] - getBiggest
//      [ok] - getRoot
// [ok] 2. iterable/iterator: traversing
// 3. balancing
@RunWith(HierarchicalContextRunner.class)
public class MyEntryTest {
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

    protected static <K extends Comparable<K>, V> void assertEntryHasLeft(
            MyEntry<K, V> entry, MyEntry<K, V> left) //
    {
        assertSame(left, entry.getLeft());
        assertSame(entry, left.getParent());
    }

    protected static <K extends Comparable<K>, V> void assertEntryHasRight(
            MyEntry<K, V> entry, MyEntry<K, V> right) //
    {
        assertSame(right, entry.getRight());
        assertSame(entry, right.getParent());
    }

    private static <K extends Comparable<K>, V> void assertDeleteSingleChild(
            MyEntry<K, V> entry, MyEntry<K, V> expectedReplacement) //
    {
        assertSame(expectedReplacement, entry.removeWithoutRebalancing());
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
            entry = new MyEntry<>("2", "two");
        }

        @Test
        public void canInsertLeft() {
            MyEntry<String, String> left = new MyEntry<>("1", "one");
            entry.insertLeft(left);
            assertEntryHasLeft(entry, left);
        }

        @Test
        public void canInsertRight() {
            MyEntry<String, String> right = new MyEntry<>("3", "three");
            entry.insertRight(right);
            assertEntryHasRight(entry, right);
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
            entry = new MyEntry<>("2", "center");
        }

        public class GivenEntryWithoutChildren {
            @Test
            public void insertNullIgnoresResult() {
                entry.insertWithoutRebalancing(null);
                assertEntryHasProperties(entry, "2", "center", NULL_PARENT);
                assertEntryIsAlone(entry);
            }

            @Test
            public void insertingEntryWithSameKeyUpdatesValue() {
                MyEntry<String, String> shouldBeSame = new MyEntry<>("2", "should be same");

                entry.insertWithoutRebalancing(shouldBeSame);

                assertEquals("should be same", entry.getValue());
                assertEntryIsAlone(entry);
                assertEntryIsAlone(shouldBeSame);
            }

            @Test
            public void canInsertLeftSmallerKeyEntries() {
                MyEntry<String, String> shouldBeLeft = new MyEntry<>("1", "left");
                entry.insertWithoutRebalancing(shouldBeLeft);
                assertEntryHasLeft(entry, shouldBeLeft);
            }

            @Test
            public void canInsertRightBiggerKeyEntries() {
                MyEntry<String, String> shouldBeRight = new MyEntry<>("3", "right");
                entry.insertWithoutRebalancing(shouldBeRight);
                assertEntryHasRight(entry, shouldBeRight);
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
                entry.insertWithoutRebalancing(shouldBeLeft);
                assertEntryHasLeft(entry.getLeft(), shouldBeLeft);
            }

            @Test
            public void canInsertInRightBiggerKeyEntries() {
                MyEntry<String, String> shouldBeRight = new MyEntry<>("3.1", "right-right");
                entry.insertWithoutRebalancing(shouldBeRight);
                assertEntryHasRight(entry.getRight(), shouldBeRight);
            }

            @Test
            public void insertionsAreRecursivelySorted() {
                MyEntry<String, String> leftRight = new MyEntry<>("1.1", "left-right");
                MyEntry<String, String> rightLeft = new MyEntry<>("2.1", "right-left");

                entry.insertWithoutRebalancing(leftRight);
                entry.insertWithoutRebalancing(rightLeft);

                assertEntryHasRight(entry.getLeft(), leftRight);
                assertEntryHasLeft(entry.getRight(), rightLeft);
            }
        }

        public class GivenEntryWithThreeLevels {
            private MyEntry<String, String> makeUpdatedEntryWithChildren() {
                MyEntry<String, String> updatedEntry = new MyEntry<>("2", "updated value");
                MyEntry<String, String> updatedLeft = new MyEntry<>("0.0", "new smallest is updated left");
                MyEntry<String, String> updatedRight = new MyEntry<>("4", "new biggest is updated right");

                updatedEntry.insertWithoutRebalancing(updatedLeft);
                updatedEntry.insertWithoutRebalancing(updatedRight);
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
                    entry.insertWithoutRebalancing(e);
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
            public void findShouldStartAtRoot() {
                assertSame(entry.find("2.1"), leftLeft.find("2.1"));
            }

            @Test
            public void insertEntyWithSameKeyUpdatesValueAndInsertsChildren() {
                MyEntry<String, String> updatedEntry = makeUpdatedEntryWithChildren();
                MyEntry<String, String> updatedLeft = updatedEntry.getLeft();

                entry.insertWithoutRebalancing(updatedEntry);

                assertEquals("updated value", entry.getValue());
                assertEquals("0.0", entry.getSmallest().getKey());
                assertEquals("4", entry.getBiggest().getKey());
                assertSame(entry, updatedLeft.getRoot());
            }
        }

        public class RemoveScenarios {
            @Test
            public void removeAloneEntryReturnsNull() {
                assertNull(entry.removeWithoutRebalancing());
                assertEntryIsAlone(entry);
            }

            public class GivenEntryWithSingleChild {
                @Test
                public void ifOnlyLeftIsSetWhenRemovingEntryLeftBecomesNewInPlaceEntry() {
                    entry.insertWithoutRebalancing(left);
                    assertDeleteSingleChild(entry, left);
                }

                @Test
                public void ifOnlyRightIsSetWhenRemovingEntryLeftBecomesNewInPlaceEntry() {
                    entry.insertWithoutRebalancing(right);
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
                    assertSame(entry, left.removeWithoutRebalancing());
                    assertEntryIsAlone(left);
                    assertNull(entry.getLeft());
                }

                @Test
                public void removingRightLeafMakesItAloneRemovesFromParentAndReturnsNewInPlaceEntry() {
                    assertSame(entry, right.removeWithoutRebalancing());
                    assertEntryIsAlone(right);
                    assertNull(entry.getRight());
                }

                @Test
                public void removingRootMakesLeftNewRootAndRightChildOfLeft() {
                    assertSame(left, entry.removeWithoutRebalancing());
                    assertEntryIsAlone(entry);
                    assertSame(right, left.getRight());
                }
            }

            public class RemovingInThreeOrMoreLevels {
                // ORIGINAL STRUCTURE:
                //      __2__
                //     /     \
                //    1       3
                //   / \     / \
                // 0.1 1.1 2.1 3.1
                // variable names will have original position and key
                private MyEntry<String, String> k01LeftLeft = new MyEntry<>("0.1", "leftLeft");
                private MyEntry<String, String> k11LeftRight = new MyEntry<>("1.1", "leftRight");
                private MyEntry<String, String> k21RightLeft = new MyEntry<>("2.1", "rightLeft");
                private MyEntry<String, String> k31RightRight = new MyEntry<>("3.1", "rightRight");

                @Before
                public void setUp() {
                    addTwoChildrenToRootEntry();

                    for (MyEntry<String, String> e : Arrays.asList(k01LeftLeft, k11LeftRight, k21RightLeft, k31RightRight))
                        entry.insertWithoutRebalancing(e);
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
                    assertSame(k11LeftRight, entry.removeWithoutRebalancing());

                    assertEntryIsAlone(entry);
                    assertNull(k11LeftRight.getParent());
                    assertSame(k11LeftRight, right.getParent());
                    assertSame(k11LeftRight, left.getParent());
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
                    assertSame(k01LeftLeft, left.removeWithoutRebalancing());

                    assertEntryIsAlone(left);
                    assertSame(entry, k01LeftLeft.getParent());
                    assertSame(k01LeftLeft, entry.getLeft());
                    assertSame(k11LeftRight, k01LeftLeft.getRight());
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
                    assertSame(k21RightLeft, right.removeWithoutRebalancing());

                    assertEntryIsAlone(right);
                    assertSame(entry, k21RightLeft.getParent());
                    assertSame(k21RightLeft, entry.getRight());
                    assertSame(k31RightRight, k21RightLeft.getRight());
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
                    k11LeftRight.removeWithoutRebalancing();

                    assertSame(k01LeftLeft, left.removeWithoutRebalancing());

                    assertEntryIsAlone(left);
                    assertSame(entry, k01LeftLeft.getParent());
                    assertSame(k01LeftLeft, entry.getLeft());
                    MyEntry<String, String> entry = this.k01LeftLeft;
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
                    // variable names will have original position and key
                    private MyEntry<String, String> k001LeftLeftLeft = new MyEntry<>("0.0.1", "leftLeftLeft");
                    private MyEntry<String, String> k011LeftLeftRight = new MyEntry<>("0.1.1", "leftLeftRight");
                    private MyEntry<String, String> k101LeftRightLeft = new MyEntry<>("1.0.1", "leftRightLeft");
                    private MyEntry<String, String> k111LeftRightRight = new MyEntry<>("1.1.1", "leftRightRight");
                    private MyEntry<String, String> k201RightLeftLeft = new MyEntry<>("2.0.1", "rightLeftLeft");
                    private MyEntry<String, String> k211RightLeftRight = new MyEntry<>("2.1.1", "rightLeftRight");
                    private MyEntry<String, String> k301ightRightLeft = new MyEntry<>("3.0.1", "rightRightLeft");
                    private MyEntry<String, String> k311RightRightRight = new MyEntry<>("3.1.1", "rightRightRight");

                    @Before
                    public void setUp() {
                        List<MyEntry<String, String>> children =
                                Arrays.asList(
                                        k001LeftLeftLeft, k011LeftLeftRight, k101LeftRightLeft, k111LeftRightRight,
                                        k201RightLeftLeft, k211RightLeftRight, k301ightRightLeft, k311RightRightRight);

                        for (MyEntry<String, String> child : children)
                            entry.insertWithoutRebalancing(child);
                    }

                    @Test
                    public void removeRight() {
                        // FROM:
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     _____3_____
                        //          /           \                   /           \
                        //       0.1             1.1             2.1             3.1
                        //      /   \           /   \           /   \           /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1   2.1.1   3.0.1   3.1.1
                        //
                        //                              |   |
                        //                              |   |
                        //                             \|   |/
                        //                              \   /
                        //                               \ /
                        //                                v
                        // TO:
                        // REMOVE "3": expected structore
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ___2.1.1___
                        //          /           \                   /           \
                        //       0.1             1.1             2.1             3.1
                        //      /   \           /   \           /               /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1           3.0.1   3.1.1
                        assertSame(k211RightLeftRight, right.removeWithoutRebalancing());

                        assertEntryIsAlone(right);
                        assertSame(entry, k211RightLeftRight.getParent());
                        assertSame(k211RightLeftRight, entry.getRight());
                        assertEntryHasLeft(k211RightLeftRight, k21RightLeft);
                        assertSame(k211RightLeftRight, k31RightRight.getParent());
                        assertSame(k31RightRight, k211RightLeftRight.getRight());
                        assertNull(k21RightLeft.getRight());
                    }

                    @Test
                    public void removeRightLeftAfterRemoveRight() {
                        // FROM:
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ___2.1.1___
                        //          /           \                   /           \
                        //       0.1             1.1             2.1             3.1
                        //      /   \           /   \           /               /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1           3.0.1   3.1.1
                        //
                        //                              |   |
                        //                              |   |
                        //                             \|   |/
                        //                              \   /
                        //                               \ /
                        //                                v
                        // TO:
                        // REMOVE "3"=then=>"2.1.1":expected structure
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ____2.1____
                        //          /           \                   /           \
                        //       0.1             1.1            2.0.1            3.1
                        //      /   \           /   \                           /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1                   3.0.1   3.1.1
                        right.removeWithoutRebalancing();

                        assertSame(k21RightLeft, k211RightLeftRight.removeWithoutRebalancing());

                        assertEntryIsAlone(k211RightLeftRight);
                        assertHasNoChildren(k201RightLeftLeft);

                        assertEntryHasRight(entry, k21RightLeft);

                        assertSame(k201RightLeftLeft, k21RightLeft.getLeft());
                        assertSame(k31RightRight, k21RightLeft.getRight());
                    }

                    @Test
                    public void whenReceivingKeyRemoveFirstSearchForKeyAndThenRemoves() {
                        // FROM:
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ___2.1.1___
                        //          /           \                   /           \
                        //       0.1             1.1             2.1             3.1
                        //      /   \           /   \           /               /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1   2.0.1           3.0.1   3.1.1
                        //
                        //                              |   |
                        //                              |   |
                        //                             \|   |/
                        //                              \   /
                        //                               \ /
                        //                                v
                        // TO:
                        // REMOVE "3"=then=>"2.1.1" (using key): expected structure
                        //                  ______________2______________
                        //                 /                             \
                        //           _____1_____                     ____2.1____
                        //          /           \                   /           \
                        //       0.1             1.1            2.0.1            3.1
                        //      /   \           /   \                           /   \
                        //  0.0.1   0.1.1   1.0.1   1.1.1                   3.0.1   3.1.1
                        right.removeWithoutRebalancing();

                        assertSame("2.1", entry.remove("2.1.1").getKey());

                        assertEntryIsAlone(k211RightLeftRight);
                        assertHasNoChildren(entry.find("2.0.1"));
                        assertEquals("2", entry.find("2.1").getParent().getKey());
                        assertEquals("2.1", entry.getRight().getKey());
                        assertEquals("2.0.1", entry.find("2.1").getLeft().getKey());
                        assertEquals("3.1", entry.find("2.1").getRight().getKey());
                    }
                }
            }
        }
    }

    public class Iteration {
        @Before
        public void setUp() {
            entry = new MyEntry<>("1.0.0.0", "middle-ie-root");
        }

        public class IteratingAloneEntries {
            private Iterator<MyEntry<String, String>> iterator;

            @Before
            public void setUp() {
                iterator = entry.iterator();
            }

            @Test
            public void canIterateOnce() {
                assertTrue(iterator.hasNext());
                assertSame(entry, iterator.next());
                assertFalse(iterator.hasNext());
            }

            @Test(expected = NoSuchElementException.class)
            public void cannotIterateMoreThanOnce() {
                iterator.next();
                iterator.next();
            }

            @Test(timeout = 200)
            public void canUseInForEachLoops() {
                for (MyEntry<String, String> current : entry)
                    assertSame(entry, current);
            }
        }

        public class SimpleOperations {
            @Test
            public void iterationBeginsWithSmallestElement() {
                //  STRUCTURE:
                //             ___1.0.0.0___
                //            /             \
                //        0.1.0.0          1.1.0.0
                //       /       \
                //  0.0.1.0    0.1.1.0
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "left"));
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.0.0", "right"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.0.1.0", "leftLeft"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.1.0", "leftRight"));

                assertEquals("0.0.1.0", entry.iterator().next().getKey());
            }
        }

        public class IterationCases {
            private void assertIteration(String... expectedKeys) {
                List<String> actualKeys = new ArrayList<>();

                for (MyEntry<String, String> current : entry)
                    actualKeys.add(current.getKey());

                assertEquals(Arrays.asList(expectedKeys), actualKeys);
            }

            @Test(timeout = 200)
            public void entryHasOnlyLeftChild() {
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "left"));
                assertIteration("0.1.0.0", "1.0.0.0");
            }

            @Test(timeout = 200)
            public void treeIsComposedByOnlyLeftChildEntries() {
                // creating not balanced tree using low level methods
                //           ___1.0.0.0
                //          /
                //      0.1.0.0
                //      /
                //  0.0.1.0
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "child"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.0.1.0", "grand child"));

                assertIteration("0.0.1.0", "0.1.0.0", "1.0.0.0");
            }

            @Test(timeout = 200)
            public void entryHasOnlyRightChild() {
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.0.0", "right"));

                assertIteration("1.0.0.0", "1.1.0.0");
            }

            @Test(timeout = 200)
            public void entryHasLeftAndRightChildren() {
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "left"));
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.0.0", "right"));

                assertIteration("0.1.0.0", "1.0.0.0", "1.1.0.0");
            }

            @Test(timeout = 200)
            public void treeIsComposedByOnlyRightChildEntries() {
                // creating not balanced tree using low level methods
                //  1.0.0.0___
                //            \
                //          1.1.0.0
                //                \
                //              1.1.1.0
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.0.0", "child"));
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.1.0", "grand child"));

                assertIteration("1.0.0.0", "1.1.0.0", "1.1.1.0");
            }

            @Test(timeout = 200)
            public void rootWithRightNodeWithLeftNode() {
                // creating not balanced tree using low level methods
                //  1.0.0.0___
                //            \
                //          1.1.0.0
                //          /
                //      1.0.1.0
                entry.insertWithoutRebalancing(new MyEntry<>("1.1.0.0", "child"));
                entry.insertWithoutRebalancing(new MyEntry<>("1.0.1.0", "grand child"));

                assertIteration("1.0.0.0", "1.0.1.0", "1.1.0.0");
            }

            @Test(timeout = 200)
            public void rootWithLeftNodeWithRightNode() {
                // creating not balanced tree using low level methods
                //           ___1.0.0.0
                //          /
                //      0.1.0.0
                //            \
                //          0.1.1.0
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "child"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.1.0", "grand child"));

                assertIteration("0.1.0.0", "0.1.1.0", "1.0.0.0");
            }

            @Test(timeout = 200)
            public void rootWithLeftNodeWithTwoCainedRightNodes() {
                // creating not balanced tree using low level methods
                //           ___1.0.0.0
                //          /
                //      0.1.0.0
                //            \
                //          0.1.1.0
                //              \
                //            0.1.1.1
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.0.0", "child"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.1.0", "grand child"));
                entry.insertWithoutRebalancing(new MyEntry<>("0.1.1.1", "grand grand child"));

                assertIteration("0.1.0.0", "0.1.1.0", "0.1.1.1", "1.0.0.0");
            }

            @Test
            public void completeTree() {
                // A FULL TREE:
                //
                //                       __________1.0.0.0__________
                //                      /                           \
                //              _0.1.0.0_                           _1.1.0.0_
                //             /         \                         /         \
                //      0.0.1.0           0.1.1.0           1.0.1.0           1.1.1.0
                //       /   \             /   \             /   \             /   \
                //  0.0.0.1 0.0.1.1   0.1.0.1 0.1.1.1   1.0.0.1 1.0.1.1   1.1.0.1 1.1.1.1
                String[] keysToInsert = { // insertion order reflects the desired structure
                        "0.1.0.0", "1.1.0.0", "0.0.1.0", "0.1.1.0", "1.0.1.0", "1.1.1.0", //
                        "0.0.0.1", "0.0.1.1", "0.1.0.1", "0.1.1.1", "1.0.0.1", "1.0.1.1", "1.1.0.1", "1.1.1.1"};

                for (String key : keysToInsert)
                    entry.insertWithoutRebalancing(new MyEntry<>(key, "whatever"));

                assertIteration( //
                        "0.0.0.1", "0.0.1.0", "0.0.1.1", "0.1.0.0", "0.1.0.1", "0.1.1.0", "0.1.1.1", //
                        "1.0.0.0", "1.0.0.1", "1.0.1.0", "1.0.1.1", "1.1.0.0", "1.1.0.1", "1.1.1.0", "1.1.1.1");
            }
        }
    }

    public class Rotation {
        @Before
        public void setUp() {
            entry = new MyEntry<>("1.0.0.0", "root");
        }

        public class RotatingLeft {
            @Test
            public void singleEntry() {
                entry.rotateLeft();
                assertEntryIsAlone(entry);
            }

            @Test
            public void rightEntry() {
                MyEntry<String, String> right = new MyEntry<>("1.1.0.0", "right");
                entry.insertWithoutRebalancing(right);

                right.rotateLeft();

                assertNull(right.getParent());
                assertEntryHasLeft(right, entry);
                assertHasNoChildren(entry);
            }

            @Test
            public void leftEntry() {
                MyEntry<String, String> left = new MyEntry<>("0.1.0.0", "left");
                entry.insertWithoutRebalancing(left);

                left.rotateLeft();
                assertNull(entry.getParent());
                assertEntryHasLeft(entry, left);
                assertHasNoChildren(left);
            }

            @Test
            public void rightEntryWithLeftChild() {
                MyEntry<String, String> right = new MyEntry<>("1.1.0.0", "right");
                MyEntry<String, String> rightLeft = new MyEntry<>("1.0.1.0", "rightLeft");

                entry.insertWithoutRebalancing(right);
                entry.insertWithoutRebalancing(rightLeft);

                right.rotateLeft();

                assertNull(right.getParent());
                assertEntryHasLeft(right, entry);
                assertEntryHasRight(entry, rightLeft);
            }

            @Test
            public void rightEntryWithGrandParent() {
                MyEntry<String, String> right = new MyEntry<>("1.1.0.0", "right");
                MyEntry<String, String> rightRight = new MyEntry<>("1.1.1.0", "rightRight");

                entry.insertWithoutRebalancing(right);
                entry.insertWithoutRebalancing(rightRight);

                rightRight.rotateLeft();

                assertEntryHasRight(entry, rightRight);
                assertEntryHasLeft(rightRight, right);
                assertHasNoChildren(right);
            }
        }

        public class RotatingRight {
            @Test
            public void singleEntry() {
                entry.rotateRight();
                assertEntryIsAlone(entry);
            }

            @Test
            public void leftEntry() {
                MyEntry<String, String> left = new MyEntry<>("0.1.0.0", "left");
                entry.insertWithoutRebalancing(left);

                left.rotateRight();

                assertNull(left.getParent());
                assertEntryHasRight(left, entry);
                assertHasNoChildren(entry);
            }

            @Test
            public void rightEntry() {
                MyEntry<String, String> right = new MyEntry<>("1.1.0.0", "right");
                entry.insertWithoutRebalancing(right);

                right.rotateRight();

                assertNull(entry.getParent());
                assertEntryHasRight(entry, right);
                assertHasNoChildren(right);
            }

            @Test
            public void leftEntryWithRightChild() {
                MyEntry<String, String> left = new MyEntry<>("0.1.0.0", "left");
                MyEntry<String, String> leftRight = new MyEntry<>("0.1.1.0", "leftRight");

                entry.insertWithoutRebalancing(left);
                entry.insertWithoutRebalancing(leftRight);

                left.rotateRight();

                assertNull(left.getParent());
                assertEntryHasRight(left, entry);
                assertEntryHasLeft(entry, leftRight);
            }

            @Test
            public void leftEntryWithGrandParent() {
                MyEntry<String, String> left = new MyEntry<>("0.1.0.0", "left");
                MyEntry<String, String> leftLeft = new MyEntry<>("0.0.1.0", "leftLeft");

                entry.insertWithoutRebalancing(left);
                entry.insertWithoutRebalancing(leftLeft);

                leftLeft.rotateRight();

                assertEntryHasLeft(entry, leftLeft);
                assertEntryHasRight(leftLeft, left);
                assertHasNoChildren(left);
            }
        }
    }
}
