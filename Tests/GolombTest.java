import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by I852780 on 06/09/2016.
 */
public class GolombTest {
    private Golomb golomb;

    @Before
    public void setUp() throws Exception {
        golomb = new Golomb();
        golomb.setStringToMap("ebbbbbbaaaaaaaaaaaaaaaacccdd");
        golomb.generateSymbolNumberHashmap();
    }

    @Test
    public void codeALetter() {
        golomb.codeAString("a");
        assertEquals("000", golomb.getCode());
    }

    @Test
    public void codeTwoLetters() {
        golomb.codeAString("ab");
        assertEquals("000001", golomb.getCode());
    }

    @Test
    public void codeBACDE(){
        golomb.codeAString("bacde");
        assertEquals("0010000100111000", golomb.getCode());
    }
}