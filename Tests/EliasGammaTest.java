import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.io.*;

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
    public void codeALetter() {
        eliasGamma.codeAString("e");
        assertEquals( "00101", eliasGamma.getCode());
    }

    @Test
    public void codeTwoLetters(){
        eliasGamma.codeAString("ab");
        assertEquals( "1010", eliasGamma.getCode());
    }

    @Test(expected = NullPointerException.class)
    public void codeTwoLettersError() {
        eliasGamma.codeAString("dD");
    }


    @Test
    public void codeThreeLetters(){
        eliasGamma.codeAString("abb");
        assertEquals("1010010", eliasGamma.getCode());
    }

    @Test
    public void codeAll(){
        eliasGamma.codeAString("ebbbbbbaaaaaaaaaaaaaaaacccdd");
        assertEquals("0010101001001001001001011111111111111110110110110010000100", eliasGamma.getCode());
    }

    @Test
    public void codeABConvertToByte(){
        eliasGamma.codeAString("ab");
        byte bytes[] = eliasGamma.getCodeLikeByteArray();
        assertEquals(-96, bytes[0]);

    }

    @Test
    public void codeBACDEConvertToByte(){
        eliasGamma.codeAString("bacde");
        byte bytes[] = eliasGamma.getCodeLikeByteArray();
        int sum = 0;
        for(int i = 0; i < bytes.length; i++){
            sum += bytes[i];
        }
        assertEquals(24, sum);
    }

    @Test
    public void codeAllBytes(){
        eliasGamma.codeAString("ebbbbbbaaaaaaaaaaaaaaaacccdd");
        byte bytes[] = eliasGamma.getCodeLikeByteArray();
        int sum = 0;
        for(int i = 0; i < bytes.length; i++){
            sum += bytes[i];
        }
        assertEquals(145, sum);
    }



}
