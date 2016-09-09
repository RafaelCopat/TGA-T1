import java.io.*;
import java.util.HashMap;

/**
 * Created by I852780 on 08/09/2016.
 */
public class Codec {

    private File fileToBeManaged;
    private BufferedReader bufferedReader;

    public Codec(File file) throws FileNotFoundException {
        this.fileToBeManaged = file;
        setBufferedReader();
    }

    private void setBufferedReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileToBeManaged);
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
        DataOutputStream os = new DataOutputStream(new FileOutputStream(
                "eliasgamma_" + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".dat"));
        HashMap<Character, Integer> hashmap = codemethod.getHashMap();
        os.writeByte(hashmap.size());
        printHashmapToFile(os, hashmap);
        os.write(bytes);
        os.close();
        return "eliasgamma_" + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".dat";
    }

    private void printHashmapToFile(DataOutputStream os, HashMap<Character, Integer> hashmap) throws IOException {
        int initialSize = hashmap.size();
        for (int i = 0; i < initialSize; i++) {
            char charToBePrinted = 'a';
            Integer lowestNumber = 128; //max in a byte
            for (char key : hashmap.keySet()) {
                if (hashmap.get(key) < lowestNumber) {
                    lowestNumber = hashmap.get(key);
                    charToBePrinted = key;
                }
            }
            os.writeByte(charToBePrinted);
            hashmap.remove(charToBePrinted);
        }
    }

    public String decodeFile(CodeMethod codemethod) throws IOException {
        DataInputStream is = new DataInputStream(new FileInputStream(fileToBeManaged));
        int charsToBeRead = is.readByte();
        recoverHashmap(is, charsToBeRead, codemethod);
        byte bytes[] = new byte[(int) fileToBeManaged.length() - (1 + charsToBeRead)];
        is.read(bytes);
        String codeInBinary = "";
        String brokenBinary = "";
        for (byte partialCode : bytes) {
            brokenBinary = Integer.toBinaryString(partialCode);
            codeInBinary = reforgeBinary(codeInBinary, brokenBinary);
        }

        String decodedText = codemethod.decodeBytes(codeInBinary);
        FileWriter fw = new FileWriter("decoded_" + fileToBeManaged.getName() + ".txt");
        fw.write(decodedText);
        fw.close();
        return "decoded_" + fileToBeManaged.getName() + ".txt";
    }

    private String reforgeBinary(String codeInBinary, String brokenBinary) {
        if (brokenBinary.length() == 8)
            codeInBinary += brokenBinary;
        else if (brokenBinary.length() < 8) {
            int initialLength = brokenBinary.length();
            for (int i = 0; i < 8 - initialLength; i++)
                brokenBinary = "0" + brokenBinary;
            codeInBinary += brokenBinary;
        } else
            codeInBinary += brokenBinary.substring(24, 32);
        return codeInBinary;
    }

    private void recoverHashmap(DataInputStream is, int charsToBeRead, CodeMethod codeMethod) throws IOException {
        String recoveredHashmap = "";
        for (int i = 0; i < charsToBeRead; i++) {
            recoveredHashmap += (char) is.readByte();
        }
        codeMethod.recoverHashmap(recoveredHashmap);

    }

    private void codeStrings(CodeMethod codemethod) throws IOException {
        String nextLine;
        bufferedReader = new BufferedReader(new FileReader(fileToBeManaged));
        nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            codemethod.codeAString(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    private void putStringsInMap(CodeMethod codemethod) throws IOException {
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            codemethod.setStringToMap(nextLine);
            nextLine = bufferedReader.readLine();
        }
    }

    public void setFile(File fileToBeCompressed) throws FileNotFoundException {
        this.fileToBeManaged = fileToBeCompressed;
        setBufferedReader();
    }
}
