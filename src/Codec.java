import java.io.*;
import java.util.HashMap;

/**
 * Created by I852780 on 08/09/2016.
 */
public class Codec {

    private File fileToBeManaged;
    private BufferedReader bufferedReader;

    public Codec(){
    }

    private void setBufferedReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileToBeManaged);
        bufferedReader = new BufferedReader(fileReader);
    }

    public String codeFile(CodeMethod codemethod) throws IOException {
        putStringsInMap(codemethod);
        codemethod.generateSymbolNumberHashmap();
        String filepath = codeAndWriteNewFile(codemethod);
        return filepath;
    }

    private void writeHashmap(CodeMethod codemethod, DataOutputStream os, File outputFile) throws IOException {
        HashMap<Character, Integer> hashmap = codemethod.getHashMap();
        os.writeByte(hashmap.size());
        printHashmapToFile(os, hashmap);
    }

    private void printHashmapToFile(DataOutputStream os, HashMap<Character, Integer> originalHashmap) throws IOException {
        HashMap<Character, Integer> hashmap = (HashMap<Character, Integer>) originalHashmap.clone();
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
        decodeWriteFile(codemethod, is, charsToBeRead);
        return "decoded_" + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".txt";
    }

    private void decodeWriteFile(CodeMethod codemethod, DataInputStream is, int charsToBeRead) throws IOException {
        codemethod.cleanCode();
        byte bytes[] = new byte[(int) fileToBeManaged.length() - (1 + charsToBeRead)];
        is.read(bytes);
        String codeInBinary = "";
        String brokenBinary;
        FileWriter fw = new FileWriter("decoded_" + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".txt");
        for (byte partialCode : bytes) {
            brokenBinary = Integer.toBinaryString(partialCode);
            codeInBinary = reforgeBinary(codeInBinary, brokenBinary);
        }
        String decodedText = codemethod.decodeBytes(codeInBinary);
        fw.write(decodedText);
        fw.close();
    }

    private String reforgeBinary(String codeInBinary, String brokenBinary) {
        if (brokenBinary.length() == 8)
            codeInBinary += brokenBinary;
        else if (brokenBinary.length() < 8) {
            int initialLength = brokenBinary.length();
            brokenBinary = forEachMissingNumberAddZero(brokenBinary, initialLength);
            codeInBinary += brokenBinary;
        } else
            codeInBinary += brokenBinary.substring(24, 32);
        return codeInBinary;
    }

    private String forEachMissingNumberAddZero(String brokenBinary, int initialLength) {
        for (int i = 0; i < 8 - initialLength; i++)
            brokenBinary = "0" + brokenBinary;
        return brokenBinary;
    }

    private void recoverHashmap(DataInputStream is, int charsToBeRead, CodeMethod codeMethod) throws IOException {
        String recoveredHashmap = "";
        for (int i = 0; i < charsToBeRead; i++) {
            recoveredHashmap += (char) is.readByte();
        }
        codeMethod.recoverHashmap(recoveredHashmap);

    }

    private String codeAndWriteNewFile(CodeMethod codemethod) throws IOException {
        String nextLine;
        bufferedReader = new BufferedReader(new FileReader(fileToBeManaged));
        nextLine = bufferedReader.readLine();
        File outputFile = createFile(codemethod.toString());
        DataOutputStream os = getDataOutputStream(outputFile);
        writeHashmap(codemethod, os, outputFile);
        readWriteWholeFile(codemethod, nextLine, os);
        os.close();
        return outputFile.getName();
    }

    private void readWriteWholeFile(CodeMethod codemethod, String nextLine, DataOutputStream os) throws IOException {
        while (nextLine != null) {
            String actualLine = nextLine;
            nextLine = bufferedReader.readLine();
            codemethod.codeAString(actualLine + '\n');
            decideWriteFile(codemethod, nextLine, os);
        }
    }

    private void decideWriteFile(CodeMethod codemethod, String nextLine, DataOutputStream os) throws IOException {
        if(nextLine != null)
            writeToFile(codemethod, os, false);
        else
            writeToFile(codemethod, os, true);
    }

    private DataOutputStream getDataOutputStream(File outputFile) throws FileNotFoundException {
        return new DataOutputStream(new FileOutputStream(
                    outputFile));
    }

    private File createFile(String codemethodName) {
        String filePath = codemethodName + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".dat";
        return new File(filePath);
    }

    private String writeToFile(CodeMethod codemethod, DataOutputStream os, boolean isLastLine) throws IOException {
        byte[] bytes = codemethod.getCodeLikeByteArray(isLastLine);
        os.write(bytes);
        return codemethod.toString() + fileToBeManaged.getName().substring(0, fileToBeManaged.getName().length() - 4) + ".dat";
    }


    private void putStringsInMap(CodeMethod codemethod) throws IOException {
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            codemethod.setStringToMap(nextLine + '\n');
            nextLine = bufferedReader.readLine();
        }
    }

    public void setFile(File fileToBeCompressed) throws FileNotFoundException {
        this.fileToBeManaged = fileToBeCompressed;
        setBufferedReader();
    }
}
