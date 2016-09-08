/**
 * Created by I852780 on 06/09/2016.
 */
public class FakeLogarithm {
    public static int logBase2(int n) {
        int result;
        for(result = 0; n >= 2; result++)
            n = n/2;
        return result;
    }
}
