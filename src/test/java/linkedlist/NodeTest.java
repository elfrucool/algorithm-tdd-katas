package linkedlist;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class NodeTest {
    private Node<Integer> node;
    private static final Object NULL_VALUE = null;
    private static final Node<?> NULL_NODE = null;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Node<Integer> circular(Node<Integer> node, int targetIndex) {
        Node<Integer> lastNode = node.get(node.size() - 1);
        lastNode.setNext(node.get(targetIndex));
        return node;
    }

    protected Node<Integer> fillListWithValues(Node<Integer> node, Integer actual, Integer... nextValues) {
        node.setValue(actual);
        Node<Integer> addToMe = node;

        for (Integer nextValue : nextValues) {
            addToMe.setNext(new Node<>(nextValue));
            addToMe = addToMe.getNext();
        }

        return node;
    }

    @Before
    public void setUp() throws Exception {
        node = new Node<>();
    }

    @Test
    public void hasValue() {
        node.setValue(123);
        assertEquals(123, (int) node.getValue());
    }

    @Test
    public void hasNext() {
        Node<Integer> next = new Node<>();
        node.setNext(next);
        assertSame(next, node.getNext());
    }

    @Test
    public void hasEquals() {
        assertEquals(new Node<>("abc"), new Node<>("abc"));
    }

    @SuppressWarnings("unused")
    protected Object parametersForConstructorVariants() {
        return new Object[]{//
                $(new Node(), NULL_VALUE, NULL_NODE), //
                $(new Node<>(1), 1, NULL_NODE), //
                $(new Node<>(1, new Node<>(2)), 1, new Node<>(2)), //
        };
    }

    @Test
    @Parameters
    public void constructorVariants(Node<Object> node, Object expectedValue, Node<Object> expectedNext) {
        assertEquals(expectedValue, node.getValue());
        assertEquals(expectedNext, node.getNext());
    }

    @Test
    public void isIterable() {
        fillListWithValues(node, 11, 12, 13, 14);

        List<Integer> values = new ArrayList<>();
        List<Integer> expectedValues = Arrays.asList(11, 12, 13, 14);

        for (Node<Integer> actual : node) {
            values.add(actual.getValue());
        }

        assertEquals(expectedValues, values);
    }

    @Test
    public void canGetTheNextNthElement() {
        fillListWithValues(node, 11, 12, 13, 14);

        assertEquals(11, ((int) node.get(0).getValue()));
//        assertEquals(12, (int) node.get(1));
//        assertEquals(13, (int) node.get(2));
//        assertEquals(14, (int) node.get(3));
    }

    @Test
    public void hasSize() {
        fillListWithValues(node, 11, 12, 13, 14);
        assertEquals(4, node.size());
    }

    @SuppressWarnings("unused")
    protected Object parametersForGetVerifiesIndex() {
        return new Object[]{//
                $(-1), //
                $(4), //
        };
    }

    @Test
    @Parameters
    public void getVerifiesIndex(int index) {
        expectedException.expect(IndexOutOfBoundsException.class);
        fillListWithValues(node, 11, 12, 13, 14);
        node.get(index);
    }


    @SuppressWarnings("unused")
    protected Object parametersForCircularListHasNextNodePointingToSelf() {
        return new Object[]{//
                $(circular(new Node<>(11), 0), true), //
                $(new Node<>(11), false), //
                $(circular(fillListWithValues(new Node<>(), 11, 22, 33, 44), 0), true), //
                $(circular(fillListWithValues(new Node<>(), 11, 22, 33, 44), 1), true), //
                $(circular(fillListWithValues(new Node<>(), 11, 22, 33, 44), 2), true), //
                $(circular(fillListWithValues(new Node<>(), 11, 22, 33, 44), 3), true), //
                $(fillListWithValues(new Node<>(), 11, 22, 33, 44), false), //
        };
    }

    @Test
    @Parameters
    public void circularListHasNextNodePointingToSelf(Node<Integer> node, boolean expected) {
        assertEquals(expected, node.isCircular());
    }
}
