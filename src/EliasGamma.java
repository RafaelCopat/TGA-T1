
/**
 * Created by I852780 on 06/09/2016.
 */

import java.util.HashMap;

public class EliasGamma implements CodeMethod {

    private String code;
    private HashMap<Character, Integer> frequencyHashmap;
    private HashMap<Character, Integer> symbolNumberHashmap;


    public EliasGamma() {
        frequencyHashmap = new HashMap<>();
        symbolNumberHashmap = new HashMap<>();
        code = "";
    }

    private String codeAChar(char symbol) {
        int symbolNumber = symbolNumberHashmap.get(symbol);
        String codeAux = createStringWithNZeros(FakeLogarithm.logBase2(symbolNumber));
        codeAux = codeAux + Integer.toBinaryString(symbolNumber);
        return codeAux;
    }

    private String createStringWithNZeros(int numberOfZeros) {
        String filledWithZeros = "";
        for (int i = 0; i < numberOfZeros; i++) {
            filledWithZeros = filledWithZeros + "0";
        }
        return filledWithZeros;
    }

    public void setStringToMap(String string) {
        for (char aChar : string.toCharArray()) putCharToMap(aChar);
    }

    private void putCharToMap(char aChar) {
        if (frequencyHashmap.containsKey(aChar))
            updateExistingChar(aChar);
        else
            frequencyHashmap.put(aChar, 1);
    }

    private void updateExistingChar(char aChar) {
        int numberOfTimesAppearingInText = frequencyHashmap.get(aChar);
        numberOfTimesAppearingInText = numberOfTimesAppearingInText + 1;
        frequencyHashmap.put(aChar, numberOfTimesAppearingInText);
    }

    public void generateSymbolNumberHashmap() {
        char frequentKey = 'a';
        int highestFrequency = 0;
        int initialSize = frequencyHashmap.size();
        for (int i = 0; i < initialSize; i++) {
            for (char key : frequencyHashmap.keySet()) {
                if (frequencyHashmap.get(key) > highestFrequency) {
                    highestFrequency = frequencyHashmap.get(key);
                    frequentKey = key;
                }
            }
            symbolNumberHashmap.put(frequentKey, i + 1);
            frequencyHashmap.remove(frequentKey);
            highestFrequency = 0;
        }
    }

    public void codeAString(String ab) {
        for (int i = 0; i < ab.length(); i++) {
            code += codeAChar(ab.charAt(i));
        }
    }

    public String getCode() {
        return code;
    }

    public byte[] getCodeLikeByteArray() {
        double codeLength = code.length() / 8.0;
        byte[] bytesInCode = new byte[(int) Math.ceil(codeLength)];//Size of Code/8 (bits of byte)
        for (int nextByte = 0; nextByte < bytesInCode.length; nextByte++)
            putBytesInArray(bytesInCode, nextByte);
        return bytesInCode;
    }

    @Override
    public void recoverHashmap(String string) {
        int fakeFrequency = string.length();
        for (int i = 0; i < string.length(); i++) {
            frequencyHashmap.put(string.charAt(i), fakeFrequency);
            fakeFrequency--;
        }
        generateSymbolNumberHashmap();
    }

    @Override
    public HashMap<Character, Integer> getHashMap() {
        return symbolNumberHashmap;
    }

    private void putBytesInArray(byte[] bytesInCode, int nextByte) {
        if (!(0 == (code.length() % 8)) && nextByte == bytesInCode.length - 1)
            bytesInCode[nextByte] = getNextNBitsOfCodeThenAddZeros(nextByte, code.length() - nextByte * 8);
        else
            bytesInCode[nextByte] = getNext8BitsOfCode(nextByte);
    }

    private byte getNextNBitsOfCodeThenAddZeros(int nextByte, int numberOfCharsLeft) {
        String partialCode = code.substring(nextByte * 8, nextByte * 8 + numberOfCharsLeft);
        partialCode += createStringWithNZeros(8 - numberOfCharsLeft);
        return (byte) Integer.parseInt(partialCode, 2);
    }

    private byte getNext8BitsOfCode(int nextByte) {
        return (byte) Integer.parseInt(code.substring(nextByte * 8, nextByte * 8 + 8), 2);
    }

    public String decodeBytes(String byteString) {
        int zerosCount = 0;
        String textResult = "";
        String binaryResult = "";
        for (int i = 0; i < byteString.length(); i++)
            if (byteString.charAt(i) == '0')
                zerosCount++;
            else {
                binaryResult += '1';
                for (int j = 0; j < zerosCount; j++) {
                    i++;
                    binaryResult += byteString.charAt(i);
                }
                textResult = getKeyInHashmap(textResult, binaryResult);
                binaryResult = "";
                zerosCount = 0;
            }
        return textResult;
    }

    private String getKeyInHashmap(String textResult, String binaryResult) {
        for (char key : symbolNumberHashmap.keySet())
            if (symbolNumberHashmap.get(key) == Integer.parseInt(binaryResult, 2)) {
                textResult += key;
                break;
            }
        return textResult;
    }
}
