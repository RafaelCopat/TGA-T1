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
        file = new File("C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\texto.txt");
        codec = new Codec(file);
    }

    @Test
    public void codeAWriteFile(){
        try {
            //#TODO save hashmap values to file to help in decoding
            //#TODO must verify how to get numericvalue to transform into char
            DataInputStream is = new DataInputStream(new FileInputStream(codec.codeFile(eliasgamma)));
            char c = 10;
            System.out.println(Character.getNumericValue('a'));
            System.out.println(c);
            System.out.println(is.readByte());
            System.out.println(is.readByte());
            System.out.println(is.readByte());
            assertEquals(-128 , is.readByte());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void codeBACDEWriteFile(){
        try {
            file = new File("C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\bacde.txt");
            codec.setFile(file);
            DataInputStream is = new DataInputStream(new FileInputStream(codec.codeFile(eliasgamma)));
            byte[] bytesInFile = new byte[(int)file.length()];
            is.read(bytesInFile);
            int sum = 0;
            for(int i = 0; i < bytesInFile.length ; i++)
                sum += bytesInFile[i];
            assertEquals(24, sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
