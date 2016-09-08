/**
 * Created by I852780 on 06/09/2016.
 */

import java.util.BitSet;
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

    private String createStringWithNZeros(int n) {
        String filledWithZeros = "";
        for (int i = 0; i < n; i++) {
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
}
