package $1_parameterized;

public class Calculator {

    public int sum(int... factors) {
        int sum = 0;
        for (int factor : factors) {
            sum += factor;
        }
        return sum;
    }

    public long multiply(int... factors) {
        int result = 1;
        for (int factor : factors) {
            result *= factor;
        }
        return result;
    }

}
