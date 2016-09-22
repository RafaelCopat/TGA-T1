
/**
 * Created by I852780 on 06/09/2016.
 */

import java.util.HashMap;

public class EliasGamma extends AbstractCode {

    public EliasGamma() {
        super();
    }

    private String codeAChar(char symbol) {
        int symbolNumber = symbolNumberHashmap.get(symbol);
        String codeAux = createStringWithNZeros(FakeLogarithm.logBase2(symbolNumber));
        codeAux = codeAux + Integer.toBinaryString(symbolNumber);
        return codeAux;
    }

    public void codeAString(String ab) {
        for (int i = 0; i < ab.length(); i++) {
            code += codeAChar(ab.charAt(i));
        }
    }

    public String decodeBytes(String byteString) {
        code += byteString;
        int zerosCount = 0;
        String textResult = "";
        String binaryResult = "";
        char key;
        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) == '0')
                zerosCount++;
            else {
                binaryResult += '1';
                for (int j = 0; j < zerosCount; j++) {
                    i++;
                    binaryResult += code.charAt(i);
                }
                key = getKeyInHashmap(binaryResult);
                textResult += key;
                binaryResult = "";
                zerosCount = 0;
            }
        return textResult; //never reached?
    }
}
