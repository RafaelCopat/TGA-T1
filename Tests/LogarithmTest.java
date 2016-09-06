import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by I852780 on 06/09/2016.
 */
public class LogarithmTest {
    @Test
    public void twoLogOfTwo() {
        assertEquals(1, Logarithm.logBase2(2));
    }

    @Test
    public void fourLogOfTwo() {
        assertEquals(2, Logarithm.logBase2(4));
    }

    @Test
    public void fiveLogOfTwo() {
        assertEquals(2, Logarithm.logBase2(5));
    }

    @Test
    public void nineLogOfTwo() {
        assertEquals(3, Logarithm.logBase2(9));
    }

    @Test
    public void fifteenLogOfTwo() {
        assertEquals(3, Logarithm.logBase2(15));
    }

    @Test
    public void fortynineLogOfTwo() {
        assertEquals(5, Logarithm.logBase2(32));
    }
}
