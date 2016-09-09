import org.junit.After;
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
         assertEquals(13 , sum);
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
        assertEquals(559, sum);
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

    @Test
    public void codeLoremWithNewLines() throws IOException {
        file = new File("loremipsumnewline.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(eliasgamma));
        codec.setFile(file);
        file = new File(codec.decodeFile(eliasgamma));
        BufferedReader br = new BufferedReader(new FileReader(file));
        String nextLine = br.readLine();
        String text = "";
        while (nextLine != null) {
            text += nextLine+'\n';
            nextLine = br.readLine();
        }
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce bibendum, mi gravida viverra varius, purus eros dictum sem, in maximus lorem leo id nisi. Pellentesque dictum tincidunt ex vitae aliquet. In lobortis hendrerit elit et varius. Sed sed libero eget nunc rutrum mattis eget sed nunc. Morbi cursus pretium libero, id vehicula nibh convallis vel. Vestibulum et sapien quis orci aliquet maximus. Donec in tempor tortor. Proin lacinia arcu at erat convallis pretium. Mauris at efficitur dolor. Curabitur ultricies ipsum in bibendum egestas.\n" +
                        "\n" +
                        "Sed pretium, nisl eu interdum cursus, risus mi dictum tellus, vel mollis elit velit pulvinar purus. Ut imperdiet magna ex, sed fringilla lectus eleifend eget. Integer vitae hendrerit lectus, vitae cursus odio. Aenean et libero eu sem tempus bibendum vitae ac augue. Nam sollicitudin vel nulla nec viverra. Phasellus eget metus eu felis pulvinar auctor. Sed sed eros faucibus, vulputate ante ac, sagittis purus. Integer sed iaculis odio, convallis dapibus odio.\n" +
                        "\n" +
                        "Nam eget maximus arcu. Proin faucibus, odio quis porttitor ornare, nisl enim aliquam enim, in fringilla odio odio mattis ex. Nulla sem ipsum, sagittis non lorem nec, ullamcorper rhoncus nibh. Nam orci velit, interdum sed ipsum vel, imperdiet pulvinar felis. Etiam eget suscipit mi. Sed in sem aliquet, porttitor eros ut, venenatis eros. Vivamus condimentum sodales cursus. Maecenas sed eros eu metus viverra vehicula varius eu orci. Nunc in accumsan felis. Aliquam erat volutpat. Nulla sit amet sem neque. Donec accumsan purus eu eros elementum, quis molestie enim congue. In malesuada condimentum magna id tincidunt.\n" +
                        "\n" +
                        "Vivamus ullamcorper viverra metus, sed volutpat mi luctus in. Integer nulla erat, efficitur id ligula eget, varius mattis ligula. Proin sit amet turpis tempor, consequat tellus non, aliquet dolor. Nam aliquam vestibulum risus. Praesent vel dolor dolor. Nam laoreet nulla a neque eleifend interdum. Vestibulum eu tortor vitae ipsum eleifend interdum. Pellentesque sollicitudin tellus id tellus porttitor lobortis. Cras vulputate dolor ac imperdiet auctor. Proin accumsan felis mauris, in aliquet augue scelerisque non. Nam accumsan pulvinar orci, et rhoncus dolor vulputate quis. Pellentesque sodales purus vitae erat volutpat tristique. Nunc quis sapien vel massa tincidunt sagittis. Cras ultricies nisl mauris, eu blandit enim blandit ac. Maecenas ornare elit in pellentesque pharetra.\n" +
                        "\n" +
                        "Donec leo elit, accumsan nec posuere eget, auctor at ex. Suspendisse potenti. Sed magna odio, suscipit non volutpat et, consequat ac quam. Duis tellus nisi, hendrerit ac molestie at, lacinia sit amet lectus. Sed ultrices, urna sed luctus efficitur, nisl orci rutrum ante, et facilisis lectus felis id massa. Integer eget accumsan augue, non tincidunt justo. Duis erat nulla, consequat a purus quis, tincidunt ornare velit. Etiam lobortis at ex ut bibendum. Curabitur a augue venenatis augue tincidunt hendrerit. Maecenas tincidunt dignissim massa, tempus efficitur lectus porttitor sed. Aenean eros diam, egestas et elit a, mattis rutrum nulla. Morbi vel dolor ex. Proin mollis nibh lorem, sit amet blandit metus vehicula ac. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.\n"
                , text);
    }

    @Ignore
    @Test
    public void codeDecodeHugeFile() throws IOException{
        file = new File("hugefile.txt");
        codec.setFile(file);
        file = new File(codec.codeFile(eliasgamma));
        codec.setFile(file);
        file = new File(codec.decodeFile(eliasgamma));
        BufferedReader br = new BufferedReader(new FileReader(file));
        String nextLine = br.readLine();
        String text = "";
        while (nextLine != null) {
            text += nextLine+'\n';
            nextLine = br.readLine();
        }
        System.out.println(text);
    }
}
