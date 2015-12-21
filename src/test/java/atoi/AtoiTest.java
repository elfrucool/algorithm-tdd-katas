package atoi;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AtoiTest {
    @SuppressWarnings("unused")
    protected Object parametersForAtoiConvertsStringToInteger() {
        return new Object[]{//
                $(null, 0), //
                $("1", 1), //
                $("0", 0), //
                $("", 0), //
                $(" ", 0), //
                $("-1", -1), //
                $("12", 12), //
                $(" 2", 2), //
        };
    }

    @Test
    @Parameters
    public void atoiConvertsStringToInteger(String s, int expected) {
        assertThat(Atoi.atoi(s), is(expected));
    }
}
