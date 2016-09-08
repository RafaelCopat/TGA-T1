import java.io.*;
import java.util.HashMap;

/**
 * Created by I852780 on 08/09/2016.
 */
public class Codec {

    private File fileToBeCompressed;
    private BufferedReader bufferedReader;

    public Codec(File file) throws FileNotFoundException {
        this.fileToBeCompressed = file;
        setBufferedReader();
    }

    private void setBufferedReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileToBeCompressed);
        bufferedReader = new BufferedReader(fileReader);
    }

    public String codeFile(CodeMethod codemethod) throws IOException {
        putStringsInMap(codemethod);
        codemethod.generateSymbolNumberHashmap();
        codeStrings(codemethod);
        return writeToFile(codemethod);
    }

    private String writeToFile(CodeMethod codemethod) throws IOException {
        byte[] bytes = codemethod.getCodeLikeByteArray();
        DataOutputStream os = new DataOutputStream(new FileOutputStream("C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\binout.dat"));
        HashMap <Character, Integer> hashmap = codemethod.getHashMap();
        os.writeByte(hashmap.size());
        for(char key : hashmap.keySet()){
            os.writeByte(Character.getNumericValue(key));
        }
        os.write(bytes);
        os.close();
        return "C:\\Users\\I852780\\IdeaProjects\\TeoriaInformacao\\binout.dat";
    }


    private void codeStrings(CodeMethod codemethod) throws IOException {
        String nextLine;
        bufferedReader = new BufferedReader(new FileReader(fileToBeCompressed));
        nextLine = bufferedReader.readLine();
        while(nextLine != null) {
            codemethod.codeAString(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    private void putStringsInMap(CodeMethod codemethod) throws IOException {
        String nextLine = bufferedReader.readLine();
        while(nextLine != null) {
            codemethod.setStringToMap(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    public void setFile(File fileToBeCompressed) throws FileNotFoundException {
        this.fileToBeCompressed = fileToBeCompressed;
        setBufferedReader();
    }
}
