package angryprofessor;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AngryProfessorTest {

    public static final String EMPTY_INPUT = "";
    public static final String EMPTY_OUTPUT = "";

    @Test
    public void testAngry() {
        assertAngry(EMPTY_INPUT, EMPTY_OUTPUT);
        assertAngry("1\n1 1\n0", "NO");
        assertAngry("0", EMPTY_OUTPUT);
        assertAngry("1\n1 1\n1", "YES");
        assertAngry("1\n2 1\n1 0", "NO");
        assertAngry("1\n2 1\n1 1", "YES");
        assertAngry("2\n1 1\n1\n1 1\n0", "YES\nNO");
        assertAngry("2\n4 3\n-1 -3 4 2\n4 2\n0 -1 2 1", "YES\nNO");
    }

    protected void assertAngry(String input, String expected) {
        assertEquals("input:\n" + input, expected, AngryProfessor.angry(new Scanner(new StringReader(input))));
    }
}
