/**
 * Created by I852780 on 06/09/2016.
 */

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class EliasGamma {


    private BitSet bitset;
    private HashMap<Character, Integer> frequencyHashmap;
    private HashMap<Character, Integer> symbolNumberHashmap;

    public EliasGamma() {
        bitset = new BitSet();
        frequencyHashmap = new HashMap<>();
        symbolNumberHashmap = new HashMap<>();
    }

    public String code(char symbol) {
        int symbolNumber = symbolNumberHashmap.get(symbol);
        String code = createStringWithNZeros(Logarithm.logBase2(symbolNumber));
        return code + Integer.toBinaryString(symbolNumber);
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
}
