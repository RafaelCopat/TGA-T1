
/**
 * Created by I852780 on 06/09/2016.
 */

import java.util.HashMap;

public class EliasGamma {

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

        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (frequencyHashmap.containsKey(chars[i])) {
                int numberOfTimesAppearingInText = frequencyHashmap.get(chars[i]);
                numberOfTimesAppearingInText = numberOfTimesAppearingInText + 1;
                frequencyHashmap.put(chars[i], numberOfTimesAppearingInText);

            } else {
                frequencyHashmap.put(chars[i], 1);
            }
        }
    }

    public void generateSymbolNumberHashmap() {
        char frequentKey = 'a';
        int highestFrequency = 0;
        int initialSize = frequencyHashmap.size();
        for(int i = 0; i < initialSize; i++) {
            for (char key : frequencyHashmap.keySet()) {
                if (frequencyHashmap.get(key) > highestFrequency) {
                    highestFrequency = frequencyHashmap.get(key);
                    frequentKey = key;
                }
            }
            symbolNumberHashmap.put(frequentKey, i+1);
            frequencyHashmap.remove(frequentKey);
            highestFrequency = 0;
        }
    }

    public void codeAString(String ab) {
        for(int i = 0; i < ab.length(); i++){
            code += codeAChar(ab.charAt(i));
        }
    }


    public String getCode() {
        return code;
    }

    public byte[] getCodeInByteArray() {
        double codeLength = code.length()/8.0;
        byte[] bytesInCode = new byte[(int)Math.ceil(codeLength)];//Size of Code/8 (bits of byte)
        for(int nextByte = 0; nextByte < bytesInCode.length; nextByte++){
            if(!(0 == (code.length() % 8)) && nextByte == bytesInCode.length-1){
                bytesInCode[nextByte] = getNextNBitsOfCodeThenAddZeros(nextByte, code.length()-nextByte*8);
            }
            else {
                bytesInCode[nextByte] = getNext8BitsOfCode(nextByte);
            }
        }
        return bytesInCode;
    }

    private byte getNextNBitsOfCodeThenAddZeros(int nextByte, int numberOfCharsLeft) {
        String partialCode = code.substring(nextByte*8, nextByte*8+numberOfCharsLeft);
        partialCode += createStringWithNZeros(8-numberOfCharsLeft);
        return (byte) Integer.parseInt(partialCode,2);
    }

    private byte getNext8BitsOfCode(int nextByte) {
        return (byte) Integer.parseInt(code.substring(nextByte*8, nextByte*8+8),2);
    }
}
