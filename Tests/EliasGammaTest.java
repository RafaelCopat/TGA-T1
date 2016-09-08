import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by I852780 on 06/09/2016.
 */
public class EliasGammaTest {
    private EliasGamma eliasGamma;

    @Before
    public void setUp() throws Exception {
        eliasGamma = new EliasGamma();
        eliasGamma.setStringToMap("ebbbbbbaaaaaaaaaaaaaaaacccdd");
        eliasGamma.generateSymbolNumberHashmap();
    }

    @Test
    public void codeLetterA(){
        assertEquals( "1" , eliasGamma.code('a'));
    }

    @Test
    public void codeLetterB(){
        assertEquals( "010" , eliasGamma.code('b'));
    }

    @Test
    public void codeLetterE(){
        assertEquals( "00101" , eliasGamma.code('e'));
    }

    @Ignore
    @Test
    public void codeLetterS(){
        assertEquals( "00000111011" , eliasGamma.code('S'));
    }


}
