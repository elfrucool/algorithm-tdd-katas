package cycledlinkedlist;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.function.Supplier;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class NodeTest {
    private static  <T> Node<T> supplierInvoker(Supplier<Node<T>> nodeSupplier) {
        return nodeSupplier.get();
    }

    @SafeVarargs
    private static <T> void assertValues(Node<T> node, T... values) {
        Node<T> currentNode = node;
        for (T value : values) {
            assertEquals(value, currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        assertNull(currentNode);
    }

    private static final Node<String> NEXT_NODE = new Node<>("good bye");

    @Test
    public void hasValue() {
        Node<Integer> integerNode = new Node<>();
        integerNode.setValue(10);
        assertEquals(10, (int) integerNode.getValue());

        Node<String> stringNode = new Node<>();
        stringNode.setValue("hello");
        assertEquals("hello", stringNode.getValue());
    }

    @Test
    public void hasNext() {
        Node<String> nodeA = new Node<>("a");
        Node<String> nodeB = new Node<>("b");

        nodeA.setNext(nodeB);

        assertSame(nodeB, nodeA.getNext());
    }

    @SuppressWarnings("unused")
    protected Object parametersForConstructorVariants() {
        return new Object[]{//
                $(supplierInvoker(() -> {
                    Node<String> noArgumentsInConstructorNode = new Node<>();
                    noArgumentsInConstructorNode.setValue("hello");
                    noArgumentsInConstructorNode.setNext(NEXT_NODE);
                    return noArgumentsInConstructorNode;
                })), //

                $(supplierInvoker(() -> {
                    Node<String> valueInConstructorNode = new Node<>("hello");
                    valueInConstructorNode.setNext(NEXT_NODE);
                    return valueInConstructorNode;
                })),//

                $(supplierInvoker(() -> new Node<>("hello", NEXT_NODE))), // value & next in constructor
        };
    }

    @Test
    @Parameters
    public void constructorVariants(Node<String> node) {
        assertEquals("hello", node.getValue());
        assertEquals("good bye", node.getNext().getValue());
    }

    @Test
    public void chainNodes() {
        assertValues(new Node<>("a", new Node<>("b", new Node<>("c"))), "a", "b", "c");
        assertValues(Node.fromValues("a", "b", "c", "d"), "a", "b", "c", "d");
    }

    @Test
    public void iteratorHasNextReturnTrueOnCurrentNode() {
        Iterator<Node<String>> nodeIterator = new Node<>("a").iterator();
        assertTrue(nodeIterator.hasNext());
    }

    @Test
    public void iteratorReturnNode() {
        Node<String> node = new Node<>("a");
        Iterator<Node<String>> nodeIterator = node.iterator();
        assertSame(node, nodeIterator.next());
    }

    @Test
    public void iteratorNextPointsToNextElement() {
        Node<String> nextNode = new Node<>();
        Node<String> firstNode = new Node<>("a", nextNode);
        Iterator<Node<String>> iterator = firstNode.iterator();
        iterator.next();
        assertSame(nextNode, iterator.next());
    }

    @Test
    public void iteratorHasNextReturnsFalseWhenThereAreNoMoreNodes() {
        Iterator<Node<String>> nodeIterator = new Node<>("a").iterator();
        nodeIterator.next();
        assertFalse(nodeIterator.hasNext());
    }

    @Test
    public void canIterateWithForEach() {
        List<String> expectedValues = Arrays.asList("a", "b", "c", "d");
        List<String> collectedValues = new ArrayList<>();

        Node<String> node = Node.fromValues("a", "b", "c", "d");

        assert node != null;
        for (Node<String> current : node)
            collectedValues.add(current.getValue());

        assertEquals(expectedValues, collectedValues);
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextAfterFalseHasNextFails() {
        Iterator<Node<String>> nodeIterator = new Node<>("a").iterator();
        nodeIterator.next();
        nodeIterator.next();
    }

    @Test
    public void canGetItemAtIndex() {
        Node<String> node = Node.fromValues("a", "b", "c");
        assert node != null;
        assertEquals("a", node.get(0).getValue());
        assertEquals("b", node.get(1).getValue());
        assertEquals("c", node.get(2).getValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @Parameters({"-1", "1"})
    public void getWithInvalidIndexFail(int index) {
        Node<String> node = new Node<>("a");
        node.get(index);
    }

    @Test
    public void canGetLastNode() {
        Node<String> node = Node.fromValues("a", "b", "c", "d");
        assert node != null;
        assertEquals("d", node.last().getValue());
    }

    @SuppressWarnings("unused")
    protected Object parametersForTestIsCyclic() {
        return new Object[]{//
                $(new Node<>("a"), false), //
                $(cycle(new Node<>("a")), true), //
                $(cycle(Node.fromValues("a", "b", "c")), true), //
                $(cycle(Node.fromValues("a", "b", "c"), 1), true), //
                $(Node.fromValues("a", "b"), false), //
                $(Node.fromValues("a", "b", "c"), false), //
        };
    }

    @Test(timeout = 1000)
    @Parameters
    public void testIsCyclic(Node<String> node, boolean expected) {
        assertEquals(expected, Node.isCyclic(node));
    }

    private static <T> Node<T> cycle(Node<T> node) {
        return cycle(node, 0);
    }

    private static <T> Node<T> cycle(Node<T> node, int index) {
        node.last().setNext(node.get(index));
        return node;
    }
}
