import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by I852780 on 08/09/2016.
 */
public class CodecTester {

    private Codec codec;
    private File file;
    private EliasGamma eliasgamma;

    @Before
    public void setUp() throws Exception {
        eliasgamma = new EliasGamma();
        file = new File("texto.txt");
        codec = new Codec(file);
    }

    @Test
    public void codeAWriteFile() throws IOException {
        file = new File(codec.codeFile(eliasgamma));
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        byte[] bytesInFile = new byte[(int)file.length()];
        is.read(bytesInFile);
        int sum = 0;
        for(int i = 0; i < bytesInFile.length ; i++)
            sum += bytesInFile[i];
         assertEquals(-30 , sum);
    }

    @Test
    public void codeBACDEWriteFile() throws IOException {
        file = new File("bacde.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(eliasgamma));
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        byte[] bytesInFile = new byte[(int) file.length()];
        is.read(bytesInFile);
        int sum = 0;
        for (int i = 0; i < bytesInFile.length; i++)
            sum += bytesInFile[i];
        assertEquals(524, sum);
    }

    @Test
    public void decodeAWriteFile() throws IOException {
        file = new File("eliasgamma_texto.dat");
        codec.setFile(file);
        file = new File(codec.decodeFile(eliasgamma));
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("a", br.readLine());
    }

    @Test
    public void decodeBACDEWriteFile() throws IOException {
        file = new File("eliasgamma_bacde.dat");
        codec.setFile(file);
        file = new File(codec.decodeFile(eliasgamma));
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("bacde", br.readLine());
    }

    @Test
    public void codeDecodeLoremIpsum() throws IOException {
        file = new File("loremipsum.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(eliasgamma));
        codec.setFile(file);
        file = new File(codec.decodeFile(eliasgamma));
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce bibendum, mi gravida viverra varius, purus eros dictum sem, in maximus lorem leo id nisi."
            , br.readLine());
    }

    @Ignore
    @Test
    public void name() throws Exception {
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(127));
        System.out.println(Integer.toBinaryString(128).length());
        System.out.println(Integer.toBinaryString(-127).substring(24,32));
        System.out.println(Integer.toBinaryString(-128).substring(24,32));

    }

    @Ignore
    @Test
    public void a() throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream("binout.dat"));
        byte a = 20;
        os.write(a);
        os.write(a);
        DataInputStream is = new DataInputStream(new FileInputStream("binout.dat"));
        System.out.println( Integer.toBinaryString(is.readByte()));
        System.out.println( Integer.toBinaryString(is.readByte()));
    }
}
