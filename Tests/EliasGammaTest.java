import org.junit.Before;
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
    }

    @Test
    public void getNumberForA(){
        assertEquals( 1 , NumericRepresentation.getSymbolNumber('a'));
    }

    @Test
    public void getNumberForB(){
        assertEquals( 18 , NumericRepresentation.getSymbolNumber('b'));
    }


    @Test
    public void codeLetterA(){
        assertEquals( "1" , eliasGamma.code('a'));
    }

    //@Test
    //public void codeLetterE(){
    //    assertEquals( "010" , eliasGamma.code('e'));
    //}

}
