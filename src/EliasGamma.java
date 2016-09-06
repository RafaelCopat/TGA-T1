/**
 * Created by I852780 on 06/09/2016.
 */
public class EliasGamma {
    public String code(char a) {
        int symbolNumber = NumericRepresentation.getSymbolNumber(a);
        int symbolNumberSizeInBinary = Integer.toString(symbolNumber,2).length();
        String code = createStringWithNZeros(symbolNumberSizeInBinary);
        return "1";
    }

    private String createStringWithNZeros(int n) {
        String filledWithZeros = "";
        for (int i = 0; i<n; i++){
            filledWithZeros = filledWithZeros + "0";
        }
        return filledWithZeros;
    }

}
