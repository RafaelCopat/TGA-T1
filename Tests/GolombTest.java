import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by I852780 on 06/09/2016.
 */
public class GolombTest {
    //With M = 4
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

    @Test
    public void decode000(){
        assertEquals("a", golomb.decodeBytes("000"));
    }

    @Test
    public void decode000001(){
        assertEquals("ab", golomb.decodeBytes("000001"));
    }

    @Test
    public void decode0010000100111000(){
        assertEquals("bacde", golomb.decodeBytes("0010000100111000"));
    }
}