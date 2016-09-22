/**
 * Created by I852780 on 22/09/2016.
 */
public class Golomb extends AbstractCode {

    private int m;

    public Golomb(){
        super();
        m = 4;
    }

    @Override
    public String decodeBytes(String string) {
        return null;
    }

    private String codeAChar(char symbol) {
        int symbolNumber = symbolNumberHashmap.get(symbol)-1;
        int quotient = symbolNumber/m;
        int remainder = symbolNumber % m;
        String codeAux = codeQuotient(quotient);
        codeAux = codeRemainder(remainder, codeAux);
        return codeAux;
    }

    private String codeRemainder(int remainder, String codeAux) {
        String binaryAux = Integer.toBinaryString(remainder);
        if(binaryAux.length() == m/2)
            codeAux += binaryAux;
        else
            codeAux += createStringWithNZeros(m/2 - binaryAux.length()) + binaryAux;
        return codeAux;
    }

    private String codeQuotient(int quotient) {
        String codeAux = "";
        //Write a q-length string of 1 bits
        for(int i = quotient; i > 0; i--){
            codeAux += "1";
        }
        //Write a 0 bit
        codeAux += "0";
        //If M is power of 2, code remainder as binary format.
        return codeAux;
    }

    @Override
    public void codeAString(String ab) {
        for (int i = 0; i < ab.length(); i++) {
            code += codeAChar(ab.charAt(i));
        }
    }

    public String toString(){
        return "golomb_";
    }
}
