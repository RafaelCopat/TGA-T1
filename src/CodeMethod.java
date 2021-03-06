import java.util.HashMap;

/**
 * Created by I852780 on 08/09/2016.
 */
public interface CodeMethod {

    void setStringToMap(String string);

    void generateSymbolNumberHashmap();

    void codeAString(String ab);

    byte[] getCodeLikeByteArray(boolean flagIsLastLine);

    void recoverHashmap(String string);

    HashMap<Character, Integer> getHashMap();

    String decodeBytes(String string);

    void cleanCode();
}
