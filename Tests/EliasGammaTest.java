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
        byte a[] = eliasGamma.getCodeInByteArray();
        assertEquals(-96, a[0]);

    }

    @Test
    public void codeBACDEConvertToByte(){
        eliasGamma.codeAString("bacde");
        byte a[] = eliasGamma.getCodeInByteArray();
        int sum = a[0] + a[1] + a[2];
        assertEquals(24, sum);
    }

    @Ignore
    @Test
    public void a() throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream("C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\binout.dat"));
        byte a = 20;
        os.write(a);
        os.write(a);
        DataInputStream is = new DataInputStream(new FileInputStream("C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\binout.dat"));
       System.out.println( Integer.toBinaryString(is.readByte()));
        System.out.println( Integer.toBinaryString(is.readByte()));
    }




}
