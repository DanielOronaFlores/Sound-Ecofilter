package RootMeanSquare;

import java.util.Arrays;

public class RootMeanSquare {

    public RootMeanSquare() {

    }

    public double calculateRMS(double[] samples) {
        return Arrays.stream(samples).map(x -> x * x).sum();
    }
}