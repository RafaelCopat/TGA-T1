import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by I852780 on 22/09/2016.
 */
public class CodecTesterGolomb {

    private Codec codec;
    private File file;
    private Golomb golomb;


    @Before
    public void setUp() throws Exception {
        golomb = new Golomb();
        codec = new Codec();
    }

    @Test
    public void codeAWriteFile() throws IOException {
        file = new File("a.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(golomb));
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        byte[] bytesInFile = new byte[(int) file.length()];
        is.read(bytesInFile);
        int sum = 0;
        for (int i = 0; i < bytesInFile.length; i++)
            sum += bytesInFile[i];
        assertEquals(113, sum);
    }

    @Test
    public void codeLoremIpsum() throws IOException {
        file = new File("loremipsum.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(golomb));
        codec.setFile(file);
        file = new File(codec.decodeFile(golomb));
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce bibendum, mi gravida viverra varius, purus eros dictum sem, in maximus lorem leo id nisi."
                , br.readLine());
    }
}
